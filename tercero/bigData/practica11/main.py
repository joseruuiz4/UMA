from pyspark.sql import SparkSession
from pyspark.sql.functions import *
from pyspark.sql.types import *

spark = SparkSession.builder.appName("Practica11").getOrCreate()

# EJERCICIO 1: LECTURA CON ESQUEMAS EXPLÍCITOS
# Carga los archivos CSV ventas.csv y clientes.csv definiendo esquemas explícitos en lugar de inferirlos automáticamente. Muestra:
# El esquema de cada DataFrame
# Los primeros 5 registros de cada uno
# El número total de filas en cada DataFrame

schema_clientes = StructType(
    [
        StructField("cliente_id", IntegerType(), True),
        StructField("nombre", StringType(), True),
        StructField("ciudad", StringType(), True),
        StructField("pais", StringType(), True),
        StructField("fecha_registro", DateType(), True),
        StructField("vip", BooleanType(), True),
        StructField("categoria_cliente", StringType(), True),
    ]
)

schema_ventas = StructType(
    [
        StructField("venta_id", IntegerType(), True),
        StructField("fecha", DateType(), True),
        StructField("cliente_id", IntegerType(), True),
        StructField("producto", StringType(), True),
        StructField("categoria", StringType(), True),
        StructField("cantidad", IntegerType(), True),
        StructField("precio", DoubleType(), True),
        StructField("region", StringType(), True),
        StructField("descuento", DoubleType(), True),
        StructField("vendedor_id", IntegerType(), True),
        StructField("total", DoubleType(), True),
    ]
)

df_clientes = spark.read.csv("clientes.csv", header=True, schema=schema_clientes)
df_ventas = spark.read.csv("ventas.csv", header=True, schema=schema_ventas)

df_clientes.printSchema()
df_clientes.show(5)
print(df_clientes.count())

df_ventas.printSchema()
df_ventas.show(5)
print(df_ventas.count())

# EJERCICIO 2: SELECCIÓN Y TRANSFORMACIÓN
# Selecciona solo las columnas: venta_id, producto, cantidad, precio, total
df_ventas_seleccion = df_ventas.select("venta_id", "producto", "cantidad", "precio", "total")
df_ventas_seleccion.show(5)

# Crea una nueva columna precio_con_iva que calcule el precio total con IVA (21%)
df_ventas_con_iva = df_ventas_seleccion.withColumn("precio_con_iva", col("precio") * col("cantidad") * 1.21)

df_ventas_con_iva.show(5)

# Renombra las columnas precio → precio_unitario y total → precio_final
df_ventas_renombrado = df_ventas_con_iva.withColumnRenamed("precio", "precio_unitario").withColumnRenamed("total", "precio_final")
df_ventas_renombrado.show(5)

# Clasifica las ventas en: "Grande" (total > 800), "Mediana" (300-800), "Pequeña" (<300)
df_ventas_clasificado = df_ventas_renombrado.withColumn(
    "clasificacion_venta",
    when(col("precio_final") > 800, "Grande")
    .when((col("precio_final") >= 300) & (col("precio_final") <= 800), "Mediana")
    .otherwise("Pequeña")
)
df_ventas_clasificado.show(5)


# EJERCICIO 3: FILTRADO CON CONDICIONES
# Aplica diferentes filtros al DataFrame de ventas:
# Ventas con total mayor a 500€
df_ventas_mayor_500 = df_ventas.filter(col("total") > 500)
df_ventas_mayor_500.show(5)

# Ventas de la categoría "Electrónicos" O "Computación"
df_ventas_categoria = df_ventas.filter((col("categoria") == "Electrónicos") | (col("categoria") == "Computación"))
df_ventas_categoria.show(5)

# Ventas donde la cantidad sea mayor a 2 y el descuento mayor al 10% (0,1)
df_ventas_cantidad_descuento = df_ventas.filter((col("cantidad") > 2) & (col("descuento") > 0.1))
df_ventas_cantidad_descuento.show(5)

# EJERCICIO 4: AGRUPACIONES Y CÁLCULOS
# Agrupa por categoría y muestra: Número de ventas, total vendido y promedio por venta
df_ventas_agrupado = df_ventas.groupBy("categoria").agg(
    count("venta_id").alias("numero_ventas"),
    sum("total").alias("total_vendido"),
    avg("total").alias("promedio_por_venta")
)
df_ventas_agrupado.show()

# Agrupa por región y muestra: Número de ventas, total vendido y descuento promedio aplicado
df_ventas_region = df_ventas.groupBy("region").agg(
    count("venta_id").alias("numero_ventas"),
    sum("total").alias("total_vendido"),
    avg("descuento").alias("descuento_promedio")w
)
df_ventas_region.show()

# Top 3 productos más vendidos: Por cantidad total de unidades vendidas. Ordena los resultados de mayor a menor según el total vendido.
df_top_productos = df_ventas.groupBy("producto").agg(
    sum("total").alias("total_unidades_vendidas")
).orderBy(col("total_unidades_vendidas").desc()).limit(3)
df_top_productos.show()

# EJERCICIO 5: RELLENAR VALORES NA CON MEDIAS
# Muestra cuántos valores NA hay en las columnas críticas de ventas (precio, descuento, cantidad, región)
df_ventas.select(
    sum(when(col("precio").isNull(), 1).otherwise(0)).alias("precio_na"),
    sum(when(col("descuento").isNull(), 1).otherwise(0)).alias("descuento_na"),
    sum(when(col("cantidad").isNull(), 1).otherwise(0)).alias("cantidad_na"),
    sum(when(col("region").isNull(), 1).otherwise(0)).alias("region_na")
).show()


# Rellena los NA con los siguientes valores (utiliza un diccionario en la función fillna):
#  precio → 500,0
#  descuento → 0,0
#  cantidad → 2
#  region → "Desconocida"
# Usa el DataFrame limpio en los siguientes ejercicios
df_ventas_limpio = df_ventas.fillna({
    "precio": 500.0,
    "descuento": 0.0,
    "cantidad": 2,
    "region": "Desconocida"
})
df_ventas_limpio.show(5)

# EJERCICIO 6: LEFT JOIN SIMPLE
# Realiza un LEFT JOIN entre ventas y clientes usando cliente_id. Del resultado, selecciona: venta_id, producto, total, nombre_cliente, ciudad
df_join = df_ventas_limpio.join(df_clientes, on="cliente_id", how="left").select(
    "venta_id", "producto", "total", col("nombre").alias("nombre_cliente"), "ciudad"
)
df_join.show(5)

# Calcula las ventas totales por ciudad de cliente. Ordena las ciudades de mayor a menor según el total vendido
df_ventas_por_ciudad = df_join.groupBy("ciudad").agg(
    sum("total").alias("total_vendido")
).orderBy(col("total_vendido").desc())
df_ventas_por_ciudad.show()

# EJERCICIO 7: CONSULTAS SQL EN SPARK
# Utiliza SQL directo para consultar los datos:
# Crea vistas temporales para ventas y clientes
df_ventas_limpio.createOrReplaceTempView("ventas")
df_clientes.createOrReplaceTempView("clientes")

# Consulta 1: Muestra las primeras 10 ventas con sus detalles
df_sql_ventas = spark.sql("SELECT * FROM ventas LIMIT 10")
df_sql_ventas.show()

# Consulta 2: Calcula ventas por categoría (número y total)
df_sql_ventas_categoria = spark.sql("""
    SELECT categoria, COUNT(venta_id) AS numero_ventas, SUM(total) AS total_vendido
    FROM ventas
    GROUP BY categoria
""")
df_sql_ventas_categoria.show()

# Consulta 3: Combina ventas con información de cliente usando JOIN y muestra venta_id, producto, total, nombre del cliente y ciudad del cliente
df_sql_join = spark.sql("""
    SELECT v.venta_id, v.producto, v.total, c.nombre AS nombre_cliente, c.ciudad
    FROM ventas v
    LEFT JOIN clientes c ON v.cliente_id = c.cliente_id
""")
df_sql_join.show(5)

# Consulta 4: Calcula estadísticas generales (total ventas, ingresos (suma de los totales), promedio (media de los totales), unidades vendidas (suma de las cantidades))
df_sql_estadisticas = spark.sql("""
    SELECT 
        COUNT(venta_id) AS total_ventas,
        SUM(total) AS ingresos,
        AVG(total) AS promedio,
        SUM(cantidad) AS unidades_vendidas
    FROM ventas
""")
df_sql_estadisticas.show()

