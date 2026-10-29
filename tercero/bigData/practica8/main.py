import pandas as pd
import numpy as np 

# Ejercicio 1
print("Ejercicio 1")
datos = {
    'Producto': ['Laptop', 'Mouse', 'Teclado', 'Monitor', 'Tablet'],
    'Precio': [1200, 25, 80, 300, 450],
    'Stock': [15, 100, 50, 30, 25],
    'Categoria': ['Electrónica', 'Accesorio', 'Accesorio', 'Electrónica', 'Electrónica']
}

df1 = pd.DataFrame(datos)
print(df1)

# Ejercicio 2
print("Ejercicio 2")
datos = [
    [1, 'Laptop', 1200, 15],
    [2, 'Mouse', 25, 100],
    [3, 'Teclado', 80, 50],
    [4, 'Monitor', 300, 30],
    [5, 'Tablet', 450, 25]
]

df2 = pd.DataFrame(datos)
print(df2)

# Ejercicio 3
print("Ejercicio 3")
print(df1.head(3))
print(df2.head(3))

# Ejercicio 4
print("Ejercicio 4")
print("Forma del DataFrame df1:")
print(df1.shape)

print("Columnas del DataFrame df1:")
print(df1.columns.tolist())

print("Tipos de datos del DataFrame df1:")
print(df1.dtypes.tolist())

print("Estadistica descriptiva del DataFrame df1:")
print(df1.describe())

print("Últimas 2 filas del DataFrame df1:")
print(df1.tail(2))

print("Muestra aleatoria de 2 filas del DataFrame df1:")
print(df1.sample(2))

# Ejercicio 5
print("Ejercicio 5")
print("Columna 'Producto':")
print(df1["Producto"])
print("Columnas 'Producto' y 'Precio':")
print(df1[["Producto", "Precio"]])
print("Primera fila:")
print(df1.iloc[0])
print("Selecciona el elemento en la posicion (1,2):")
print(df1.iloc[1, 2])
print("Usa loc para seleccionar las primeras 2 filas")
print(df1.loc[0:1])

# Ejercicio 6
print("Ejercicio 6")
np.random.seed(42)
datos_ventas = {
'Fecha': pd.date_range('2024-01-01', periods=100, freq='D'),
'Producto': np.random.choice(['Laptop', 'Mouse', 'Teclado', 'Monitor',
'Tablet'], 100),
'Cantidad': np.random.randint(1, 10, 100),
'Precio_Unitario': np.random.choice([1200, 25, 80, 300, 450], 100),
'Region': np.random.choice(['Norte', 'Sur', 'Este', 'Oeste'], 100),
'Vendedor': np.random.choice(['Ana', 'Carlos', 'Maria', 'Pedro'], 100)
}
df_ventas = pd.DataFrame(datos_ventas)
df_ventas['Venta_Total'] = df_ventas['Cantidad'] * df_ventas['Precio_Unitario']

print("Todas las ventas de 'Laptop':")
print(df_ventas[df_ventas['Producto'] == 'Laptop'])
print("Ventas con monto total mayor a $1000:")
print(df_ventas[df_ventas['Venta_Total'] > 1000])
print("Ventas realizadas por 'Maria' en la región 'Norte':")
print(df_ventas[(df_ventas['Vendedor'] == 'Maria') & (df_ventas['Region'] == 'Norte')])

# Ejercicio 7
print("Ejercicio 7")
print("Crea una copia del DataFrame del ejercicio 6 e introduce valores nulos en algunas celdas")
np.random.seed(42)
datos_ventas2 = {
    "Fecha": pd.date_range("2024-01-01", periods=100, freq="D"),
    "Producto": np.random.choice(["Laptop", "Mouse", "Teclado", np.nan, "Tablet"], 100),
    "Cantidad": np.random.randint(1, 10, 100),
    "Precio_Unitario": np.random.choice([1200, 25, np.nan, 300, 450], 100),
    "Region": np.random.choice(["Norte", np.nan, "Este", "Oeste"], 100),
    "Vendedor": np.random.choice(["Ana", "Carlos", "Maria", "Pedro"], 100),
}
df_ventas_copy = pd.DataFrame(datos_ventas2)
df_ventas_copy = df_ventas_copy.replace("nan", np.nan)
df_ventas_copy["Venta_Total"] = (    df_ventas_copy["Cantidad"] * df_ventas_copy["Precio_Unitario"]
)

print("Identifica y cuenta los valores nulos por columna")
print(df_ventas_copy.isnull().sum())

print("Elimina las filas con valores nulos y compara el tamaño del DataFrame con el inicial")
df_ventas_sin_nulos = df_ventas_copy.dropna()
print(f"Tamaño inicial: {df_ventas_copy.shape}")
print(f"Tamaño después de eliminar filas con valores nulos: {df_ventas_sin_nulos.shape}")

print("Rellena los valores nulos con estrategias apropiadas")
df_ventas['Producto']=df_ventas['Producto'].fillna("Producto")
df_ventas['Precio_Unitario']=df_ventas['Precio_Unitario'].fillna(df_ventas[
'Precio_Unitario'].mean().round(1))
df_ventas['Region']=df_ventas['Region'].fillna("Norte")
df_ventas['Vendedor']=df_ventas['Vendedor'].fillna("Juan")
df_ventas['Venta_Total'] = df_ventas['Cantidad'] * df_ventas['Precio_Unitario']
print(df_ventas)

print("Ordena el DataFrame por 'Venta_Total' de forma descendente \n")
df_ventas_ordenado = df_ventas.sort_values(by='Venta_Total',ascending=False)
print(df_ventas_ordenado)

# Ejercicio 8
print("Ejercicio 8")
print("Calcula la venta total, promedio, máxima y número de ventas del total del DataFrame del ejercicio 6")
df = df_ventas.copy()
print(df["Venta_Total"].agg(["sum", "mean", "max", "count"]))
print("Agrupa por 'Producto' y calcula la suma de ventas por producto")
print(df.groupby("Producto")["Venta_Total"].sum())
print("Agrupa por 'Region' y calcula la venta total, promedio, máxima y número de ventas")
print(df.groupby("Region")["Venta_Total"].agg(["sum", "mean", "max", "count"]))
print("Identifica los top 3 vendedores por ventas totales")
print(df.groupby("Vendedor")["Venta_Total"].sum().sort_values(ascending=False).head(3))

# Ejercicio 9
print("Ejercicio 9")
print("Crea en el DataFrame del ejercicio 6 una nueva columna 'Venta_Con_IVA' aplicando 21% de IVA")
df_ventas["Venta_Con_IVA"] = df_ventas["Venta_Total"] * 1.21
print(df_ventas)

print("Crea una columna 'Categoria_Precio' que clasifique como 'Alto' ( mayor que 500) o 'Bajo' (menor o igual que 500) según el precio")
df_ventas["Categoria_Precio"] = np.where(df_ventas["Precio_Unitario"] > 500, "Alto", "Bajo")
print(df_ventas)

print("Usa apply() para crear una columna 'Tipo_Venta' que clasifique las ventas como 'Pequeña' (menos de 500), 'Mediana' (menos de 2000) o 'Grande' (en otro caso)")
def clasificar_venta(monto):
    if monto < 500:
        return "Pequeña"
    elif monto < 2000:
        return "Mediana"
    else:
        return "Grande"

df_ventas["Tipo_Venta"] = df_ventas["Venta_Total"].apply(clasificar_venta)
print(df_ventas)

# Ejercicio 10
print("Ejercicio 10")
nfo_producto= {
'Producto': ['Laptop', 'Mouse', 'Teclado', 'Monitor', 'Tablet', 'Auriculares'],
'Categoria': ['Electrónica', 'Accesorio', 'Accesorio', 'Electrónica',
'Electrónica', 'Accesorio'],
'Costo': [800, 15, 40, 200, 300, 20]
}
info_vendedores = {
'Vendedor': ['Ana', 'Carlos', 'Maria', 'Pedro', 'Laura'],
'Departamento': ['Ventas', 'Tecnología', 'Ventas', 'Marketing', 'Ventas'],
'Salario_Base': [30000, 35000, 32000, 28000, 31000]
}

print("Crea dos DataFrames adicionales: info_productos y info_vendedores")
info_productos = pd.DataFrame(nfo_producto)
info_vendedores = pd.DataFrame(info_vendedores)

print("Realiza un INNER JOIN entre df_ventas y info_productos")
print(pd.merge(df_ventas, info_productos, on="Producto", how="inner"))

print("Realiza un LEFT JOIN entre df_ventas y info_vendedores")
print(pd.merge(df_ventas, info_vendedores, on="Vendedor", how="left"))

print("Concatena al DataFrame del ejercicio 6")
datos_nuevos= {
'Fecha': ['2024-04-01'],
'Producto': ['Auriculares'],
'Cantidad': [2],
'Precio_Unitario': [50],
'Region': ['Norte'],
'Vendedor': ['Laura'],
'Venta_Total': [100],
'Venta_Con_IVA': [121],
'Categoria_Precio': ['Bajo']
}
df_nuevos = pd.DataFrame(datos_nuevos)
df_ventas_concatenado = pd.concat([df_ventas, df_nuevos], ignore_index=True)
print(df_ventas_concatenado)

# Ejercicio 11
print("Ejercicio 11")
print("Calcula las ventas entre el 2024-01-15 y el 2024-01-31")
filtro_fecha = (df_ventas['Fecha'] >= '2024-01-15') & (df_ventas['Fecha'] <= '2024-01-31')
print(df_ventas[filtro_fecha])