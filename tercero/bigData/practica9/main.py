import pandas as pd


# EJERCICIO 1: CARGA DE DATOS Y EXPLORACIÓN INICIAL

# 1. Cargar clientes.csv y pedidos.csv en DataFrames
pedidos = pd.read_csv('pedidos.csv')
pedidos['fecha_pedido'] = pd.to_datetime(pedidos['fecha_pedido'])
clientes = pd.read_csv('clientes.csv')

# 2. Mostrar las primeras 5 filas de cada DataFrame
print(f"5 primeras filas de pedidos {pedidos.head(5)}")
print(f"5 primeras filas de clientes {clientes.head(5)}")

# 3. Obtener información de tipos de datos y formas (shape)
print(f"Tipos de datos de pedidos:\n {pedidos.dtypes}")
print(f"Forma de pedidos: {pedidos.shape}")
print(f"Tipos de datos de clientes:\n {clientes.dtypes}")
print(f"Forma de clientes: {clientes.shape}")

# 4. Mostrar estadísticas descriptivas básicas
print(f"Estadísticas descriptivas de pedidos:\n {pedidos.describe()}")
print(f"Estadísticas descriptivas de clientes:\n {clientes.describe()}")


# EJERCICIO 2: CONSULTAS BÁSICAS
# 1. Clientes de Madrid mayores de 30 años
clientes_madrid_mayores_30 = clientes[(clientes['ciudad'] == 'Madrid') & (clientes['edad'] > 30)]
print(f"Clientes de Madrid mayores de 30 años:\n {clientes_madrid_mayores_30}")

# 2. Clientes Premium o que tengan más de 3000 puntos de fidelidad
clientes_premium_o_fidelidad = clientes[(clientes['categoria'] == 'Premium') | (clientes['puntos_fidelidad'] > 3000)]
print(f"Clientes Premium o con más de 3000 puntos de fidelidad:\n {clientes_premium_o_fidelidad}")

# 3. Pedidos con total mayor a 500 euros
pedidos_mayores_500 = pedidos[pedidos['total'] > 500]
print(f"Pedidos con total mayor a 500 euros:\n {pedidos_mayores_500}")

# 4. Pedidos pendientes del año 2023
pedidos_pendientes_2023 = pedidos[(pedidos['estado'] == 'pendiente') & (pedidos['fecha_pedido'].dt.year == 2023)]
print(f"Pedidos pendientes del año 2023:\n {pedidos_pendientes_2023}")

# EJERCICIO 3: OPERACIONES DE AGREGACIÓN
# 1. Calcula el número de clientes por ciudad
clientes_por_ciudad = clientes.groupby('ciudad').size()
print(f"Número de clientes por ciudad:\n {clientes_por_ciudad}")

# 2. Obtén las ventas totales por estado de pedido
ventas_por_estado = pedidos.groupby('estado')['total'].sum()
print(f"Ventas totales por estado de pedido:\n {ventas_por_estado}")

# 3. Calcula el promedio de edad de clientes por categoría
promedio_edad_por_categoria = clientes.groupby('categoria')['edad'].mean()
print(f"Promedio de edad de clientes por categoría:\n {promedio_edad_por_categoria}")

# 4. Obtén el top 3 ciudades con más pedidos
top_ciudades_pedidos = pedidos.merge(clientes, on='cliente_id').groupby('ciudad').size().nlargest(3)
print(f"Top 3 ciudades con más pedidos:\n {top_ciudades_pedidos}")

# EJERCICIO 4: JOINS ENTRE TABLAS
# 1. Mostrar nombre del cliente junto con sus pedidos
pedidos_con_clientes = pedidos.merge(clientes, on='cliente_id', how='inner')[['nombre', 'pedido_id', 'total', 'estado']]
print(f"Pedidos con nombres de clientes:\n {pedidos_con_clientes}")

# 2. Mostrar todos los clientes con sus pedidos, deben aparecer también los que no tienen pedidos
clientes_con_pedidos = clientes.merge(pedidos, on='cliente_id', how='left')[['nombre', 'pedido_id', 'total', 'estado']]
print(f"Clientes con sus pedidos (incluyendo los que no tienen pedidos):\n {clientes_con_pedidos}")

# 3. Muestra los pedidos que no tienen cliente asignado
pedidos_sin_cliente = pedidos[~pedidos['cliente_id'].isin(clientes['cliente_id'])]
print(f"Pedidos sin cliente asignado:\n {pedidos_sin_cliente}")

# 4. Muestra los clientes que nunca han hecho un pedido
clientes_sin_pedidos = clientes[~clientes['cliente_id'].isin(pedidos['cliente_id'])]
print(f"Clientes que nunca han hecho un pedido:\n {clientes_sin_pedidos}")

# EJERCICIO 5: LIMPIEZA DE DATOS
# 1. Identificar columnas con valores nulos en ambos DataFrames
nulos_pedidos = pedidos.isnull().sum()
nulos_clientes = clientes.isnull().sum()
print(f"Valores nulos en pedidos:\n {nulos_pedidos}")
print(f"Valores nulos en clientes:\n {nulos_clientes}")

# 2. Rellenar las edades nulas con la media de edad de los clientes
media_edad = clientes['edad'].mean()
clientes['edad'].fillna(media_edad, inplace=True)
print(f"Clientes después de rellenar edades nulas:\n {clientes}")

# 3. Rellenar puntos_fidelidad nulos con 0
clientes['puntos_fidelidad'].fillna(0, inplace=True)
print(f"Clientes después de rellenar puntos_fidelidad nulos:\n {clientes}")

# 4. Eliminar pedidos donde cantidad es nula
pedidos.dropna(subset=['cantidad'], inplace=True)
print(f"Pedidos después de eliminar filas con cantidad nula:\n {pedidos}")

# EJERCICIO 6: CREACIÓN DE NUEVAS COLUMNAS
# 1. Añadir columna 'rango_edad' (Joven: <30, Adulto: 30-60, Senior: >60)
def asignar_rango_edad(edad):
    if edad < 30:
        return 'Joven'
    elif 30 <= edad <= 60:
        return 'Adulto'
    else:
        return 'Senior'
clientes['rango_edad'] = clientes['edad'].apply(asignar_rango_edad)
print(f"Clientes con nueva columna 'rango_edad':\n {clientes}")

# 2. Crear columna 'año_mes_pedido' en la que aparezca el año y el mes con el formato YYYY-MM a partir de fecha_pedido
pedidos['año_mes_pedido'] = pedidos['fecha_pedido'].dt.to_period('M').astype(str)
print(f"Pedidos con nueva columna 'año_mes_pedido':\n {pedidos}")

# 3. Añadir columna 'tipo_producto' basada en el nombre del producto
def clasificar_producto(producto):
    nombre = str(producto).lower()
    if any(keyword in nombre for keyword in ['laptop', 'smartphone', 'tablet', 'smartwatch']):
        return 'Dispositivo'
    elif any(keyword in nombre for keyword in ['monitor', 'teclado', 'ratón', 'web']):
        return 'Perifericos'
    elif any(keyword in nombre for keyword in ['auriculares', 'altavoz']):
        return 'Audio'
    else:
        return 'Otros'
    
pedidos['tipo_producto'] = pedidos['producto'].apply(clasificar_producto)
print(f"Pedidos con nueva columna 'tipo_producto':\n {pedidos}")

# 4. Crear columna 'cliente_activo' (sí/no basado en si tiene pedidos)
clientes['cliente_activo'] = clientes['cliente_id'].apply(lambda x: 'sí' if x in pedidos['cliente_id'].values else 'no')
print(f"Clientes con nueva columna 'cliente_activo':\n {clientes}")

# EJERCICIO 7: CONSULTAS COMPLEJAS
# 1. Obtén los clientes de Barcelona que han hecho pedidos > 1000€
clientes_barcelona_pedidos_altos = clientes.merge(pedidos, on='cliente_id')
clientes_barcelona_pedidos_altos = clientes_barcelona_pedidos_altos[(clientes_barcelona_pedidos_altos['ciudad'] == 'Barcelona') & (clientes_barcelona_pedidos_altos['total'] > 1000)]
print(f"Clientes de Barcelona con pedidos > 1000€:\n {clientes_barcelona_pedidos_altos}")

# 2. Muestra los productos más vendidos por ciudad
productos_mas_vendidos_por_ciudad = pedidos.merge(clientes, on='cliente_id').groupby(['ciudad', 'producto']).size().reset_index(name='cantidad_pedidos')
productos_mas_vendidos_por_ciudad = productos_mas_vendidos_por_ciudad.loc[productos_mas_vendidos_por_ciudad.groupby('ciudad')['cantidad_pedidos'].idxmax()]
print(f"Productos más vendidos por ciudad:\n {productos_mas_vendidos_por_ciudad}")

# 3. Obtén los clientes Premium con mayor gasto total
clientes_premium_gasto_total = clientes[clientes['categoria'] == 'Premium'].merge(pedidos, on='cliente_id')
clientes_premium_gasto_total = clientes_premium_gasto_total.groupby('cliente_id')['total'].sum().reset_index().sort_values(by='total', ascending=False)
print(f"Clientes Premium con mayor gasto total:\n {clientes_premium_gasto_total}")

# 4. Muestra la evolución mensual de ventas en 2023
ventas_mensuales_2023 = pedidos[pedidos['fecha_pedido'].dt.year == 2023]
ventas_mensuales_2023 = ventas_mensuales_2023.groupby(ventas_mensuales_2023['fecha_pedido'].dt.to_period('M'))['total'].sum().reset_index()
ventas_mensuales_2023['fecha_pedido'] = ventas_mensuales_2023['fecha_pedido'].astype(str)
print(f"Evolución mensual de ventas en 2023:\n {ventas_mensuales_2023}")

# 5. Obtén la media de total de ventas y cuenta los pedidos por clientes
media_total_ventas_por_cliente = pedidos.groupby('cliente_id')['total'].agg(['mean', 'count']).reset_index()
media_total_ventas_por_cliente.rename(columns={'mean': 'media_total_ventas', 'count': 'numero_pedidos'}, inplace=True)
print(f"Media de total de ventas y cuenta de pedidos por cliente:\n {media_total_ventas_por_cliente}")

# 6. Muestre los dos productos con mejor desempeño por categoría de cliente, un producto tiene mejor desempeño que otro si tanto la suma total de ventas como la cantidad de ventas son mayores
pedidos_clientes = clientes.merge(pedidos, on='cliente_id')
desempeno_productos = pedidos_clientes.groupby(['categoria', 'producto']).agg({'total': 'sum', 'pedido_id': 'count'})
desempeno_productos.columns = ['suma_total_ventas', 'cantidad_ventas']
desempeno_productos = desempeno_productos.reset_index()
resultado = desempeno_productos.sort_values(by=['suma_total_ventas', 'cantidad_ventas'], ascending=[ False, False]).groupby('categoria').head(2)
print(f"Dos productos con mejor desempeño por categoría de cliente:\n {resultado}")