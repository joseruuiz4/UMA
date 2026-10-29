from pyspark.sql import SparkSession
from pyspark.sql.functions import *
from pyspark.sql.types import *

# Ejercicio 1: CONFIGURACIÓN Y LECTURA DE DATOS 

spark = SparkSession.builder.appName("Practica10ISID").getOrCreate()

schema = StructType([
    StructField("id_empleado", IntegerType(), True),
    StructField("nombre", StringType(), True),
    StructField("apellido", StringType(), True),
    StructField("edad", IntegerType(), True),
    StructField("departamento", StringType(), True),
    StructField("puesto", StringType(), True),
    StructField("salario", DoubleType(), True),
    StructField("fecha_contratacion", DateType(), True),
    StructField("ciudad", StringType(), True),
    StructField("activo", BooleanType(), True)
])

df_empleados = spark.read.csv("empleados.csv", header=True, schema=schema)

# EJERCICIO 2: INSPECCIÓN DE DATOS
# Muestra el esquema
df_empleados.printSchema()

# Visualiza las primeras 10 filas
df_empleados.show(10)

# Obten estadisticas basicas
df_empleados.describe().show()

# EJERCICIO 3: SELECCIÓN Y PROYECCIÓN
# Selecciona las las siguientes columnas de empleados: nombre, apellido y
# departamento. Luego crea una selección que incluya solo empleados del
# departamento de "Ventas".
df_seleccion = df_empleados.select("nombre", "apellido", "departamento")
df_seleccion.show(10)

df_ventas = df_seleccion.filter(col("departamento") == "Ventas")
df_ventas.show(10)

# EJERCICIO 4: TRANSFORMACIÓN DE COLUMNAS
# Crea una nueva columna que combine nombre y apellido, por ejempl: “Pepe
# García”, y otra columna que calcule un bono del 5% sobre el salario. Renombra la
# columna "puesto" a "cargo".

df_transformado = df_empleados.withColumn("nombre_completo", concat_ws(" ", col("nombre"), col("apellido"))) \
                              .withColumn("bono", col("salario") * 0.05) \
                              .withColumnRenamed("puesto", "cargo")

df_transformado.show(10)

# EJERCICIO 5: FILTRADO BÁSICO
# Filtra los empleados que tienen un salario mayor a 45000 y muestra su nombre, apellido, departamento y salario.
df_filtrado = df_empleados.filter(col("salario") > 45000) \
                          .select("nombre", "apellido", "departamento", "salario")

df_filtrado.show(10)

# Filtra aquellos empleados que trabajan en "Madrid" y muestra su nombre, apellido, ciudad y puesto.
df_madrid = df_empleados.filter(col("ciudad") == "Madrid") \
                        .select("nombre", "apellido", "ciudad", "puesto")

df_madrid.show(10)

# Filtra los empleados que están activos en la empresa y muestra su nombre, apellido y activo.
df_activos = df_empleados.filter(col("activo") == True) \
                         .select("nombre", "apellido", "activo")

df_activos.show(10)

# EJERCICIO 6: AGRUPACIONES SIMPLES
# Agrupa los empleados por departamento y calcula: el número de empleados, el salario promedio y el salario máximo por departamento.
df_agrupado = df_empleados.groupBy("departamento") \
                          .agg(count("id_empleado").alias("num_empleados"),
                               avg("salario").alias("salario_promedio"),
                               max("salario").alias("salario_maximo"))

df_agrupado.show(10)

# EJERCICIO 7: ORDENAMIENTO
# Ordena los empleados por salario de forma descendente y luego por nombre de forma ascendente. Muestra solo los top 10 empleados con mayor salario.
df_ordenado = df_empleados.orderBy(col("salario").desc(), col("nombre").asc())
df_ordenado.show(10)

# EJERCICIO 8: FUNCIONES DE STRING
# Utiliza funciones de string para añadir al dataframe las siguientes columnas:
# convertir los nombres a mayúsculas
# crear emails corporativos con el formato:
# nombre.apellido@empresa.com
# extraer las primeras tres letras del apellido.
df_string = df_empleados.withColumn("nombre_mayusculas", upper(col("nombre"))) \
                        .withColumn("email", concat(lower(col("nombre")), lit("."), lower(col("apellido")), lit("@empresa.com")) ) \
                        .withColumn("apellido_tres_letras", substring(col("apellido"), 1, 3))
df_string.show(10)

# EJERCICIO 9: FUNCIONES DE FECHAS
# Calcula la antigüedad de cada empleado en días y añádela como una columna nueva.
df_fechas = df_empleados.withColumn("antiguedad_dias", datediff(current_date(), col("fecha_contratacion")))
df_fechas.show(10)

# EJERCICIO 10: CONSULTAS SQL
# Crea una vista temporal y ejecuta una consulta SQL que muestre nombre, departamento y salario de empleados con salario mayor a 40000, ordenados por salario descendente.
df_empleados.createOrReplaceTempView("empleados_view")
df_sql = spark.sql("SELECT nombre, departamento, salario FROM empleados_view WHERE salario > 40000 ORDER BY salario DESC")
df_sql.show(10)

# EJERCICIO 11: ESCRITURA
# Guarda en un CSV el DataFrame obtenido en el ejercicio 8
df_string.write.csv("empleados_transformados.csv")