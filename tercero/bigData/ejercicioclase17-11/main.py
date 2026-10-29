import numpy as np
import pandas as pd

clientes_data = {

    'cliente_id': [1, 2, 3, 4, 5, 6, 7],

    'nombre': ['Ana García', 'Luis Martínez', 'Carlos Rodríguez', 'María López', 

               'Pedro Sánchez', 'Laura Fernández', 'Sofia Ramirez'],

    'email': ['ana@gmail.com', 'luis@empresa.com', 'carlos@hotmail.com', 

              'maria@gmail.com', 'pedro@yahoo.com', 'laura@gmail.com', 'sofia@empresa.com'],

    'ciudad': ['Madrid', 'Barcelona', 'Madrid', 'Valencia', 'Sevilla', 'Barcelona', 'Bilbao'],

    'saldo': ['1500.50', '800.75', '2200.00', '950.25', '3000.80', '1200.40', '750.90'],

    'fecha_registro': ['2023-01-15', '2022-03-22', '2023-05-10', '2021-11-30', 

                       '2023-08-14', '2022-01-05', '2023-12-01'],

    'categoria': ['Premium', 'Standard', 'Premium', 'Standard', 'Premium', 'Standard', 'Standard']

}


pedidos_data = {

    'pedido_id': [101, 102, 103, 104, 105, 106, 107, 108, 109],

    'cliente_id': [1, 2, 1, 3, 4, 2, 1, 8, 3],  # cliente_id 8 no existe en clientes

    'producto': ['Laptop', 'Tablet', 'Smartphone', 'Monitor', 'Teclado', 

                 'Mouse', 'Tablet', 'Monitor', 'Laptop'],

    'cantidad': [1, 2, 1, 1, 3, 2, 1, 1, 1],

    'precio': [800, 300, 500, 250, 50, 25, 300, 250, 800],

    'fecha_pedido': ['2023-02-20', '2023-03-15', '2023-04-10', '2023-05-25', 

                     '2023-06-05', '2023-07-18', '2023-08-20', '2023-09-01', '2023-10-15'],

    'estado': ['Entregado', 'Entregado', 'Pendiente', 'Entregado', 'Cancelado', 

               'Entregado', 'Entregado', 'Pendiente', 'Pendiente']

}


productos_data = {

    'producto_id': ['P001', 'P002', 'P003', 'P004', 'P005'],

    'nombre': ['Laptop', 'Tablet', 'Smartphone', 'Monitor', 'Teclado'],

    'precio': ['1200.75', '450.50', '799.99', '299.00', '89.95'],

    'categoria': ['Tecnología', 'Tecnología', 'Tecnología', 'Oficina', 'Oficina'],

    'stock': [15, 30, 25, 40, 100]

}

clientes = pd.DataFrame(clientes_data)
pedidos = pd.DataFrame(pedidos_data)
productos = pd.DataFrame(productos_data)

clientes['saldo'] = clientes['saldo'].astype(float)
clientes['fecha_registro'] = pd.to_datetime(clientes['fecha_registro'])
pedidos["total"] = pedidos["cantidad"] * pedidos["precio"]


# 1.   SELECT nombre, ciudad FROM clientes
print(clientes[['nombre', 'ciudad']])

# 2. SELECT * FROM clientes WHERE ciudad = 'Madrid'
print(clientes[clientes['ciudad'] == 'Madrid'])

# 3. SELECT * FROM clientes WHERE ciudad = 'Madrid' AND categoria = 'Premium'
print(clientes[(clientes['ciudad'] == 'Madrid') & (clientes['categoria'] == 'Premium')])

# 4.  SELECT * FROM clientes WHERE ciudad = 'Madrid' OR ciudad = 'Barcelona'
print(clientes[(clientes['ciudad'] == 'Madrid') | (clientes['ciudad'] == 'Barcelona')])

# 5. SELECT nombre, ciudad FROM clientes ORDER BY ciudad DESC, nombre ASC
print(clientes[['nombre', 'ciudad']].sort_values(by=['ciudad', 'nombre'], ascending=[False, True]))

# 6. SELECT * FROM clientes LIMIT 3
print(clientes.head(3))

# 7. SELECT COUNT(*) FROM clientes
print(len(clientes))

# 8. SELECT ciudad, COUNT(*) FROM clientes GROUP BY ciudad
print(clientes.groupby('ciudad').size().reset_index(name='count'))

# 9.  SELECT cliente_id, COUNT(*), SUM(total), AVG(total) FROM pedidos GROUP BY cliente_id
print(pedidos.groupby('cliente_id').agg({'pedido_id': 'count', 'total': ['sum', 'mean']}).reset_index())

# 10. SELECT ciudad, COUNT(*) as cnt FROM clientes GROUP BY ciudad HAVING cnt > 1
ciudad_counts = clientes.groupby('ciudad').size().reset_index(name='cnt')
print(ciudad_counts[ciudad_counts['cnt'] > 1])

# 11. SELECT c.nombre, p.producto, p.total FROM clientes c JOIN pedidos p ON c.cliente_id = p.cliente_id
merged = pd.merge(clientes, pedidos, on='cliente_id', how='inner')
print(merged[['nombre', 'producto', 'total']])

# 12. SELECT c.nombre, p.producto FROM clientes c LEFT JOIN pedidos p ON c.cliente_id = p.cliente_id
left_merged = pd.merge(clientes, pedidos, on='cliente_id', how='left')
print(left_merged[['nombre', 'producto']])

# 13.  SELECT c.nombre, p.pedido_id FROM clientes c RIGHT JOIN pedidos p ON c.cliente_id = p.cliente_id
right_merged = pd.merge(clientes, pedidos, on='cliente_id', how='right')
print(right_merged[['nombre', 'pedido_id']])

# 14. SELECT nombre, email FROM clientes WHERE UPPER(nombre) LIKE '%A%' OR UPPER(email) LIKE '%GMAIL%'
print(clientes[(clientes['nombre'].str.upper().str.contains('A')) |
               (clientes['email'].str.upper().str.contains('GMAIL'))][['nombre', 'email']])

# 15. SELECT UPPER(nombre), LOWER(ciudad) FROM clientes
print(clientes[['nombre', 'ciudad']].assign(
    nombre_upper=clientes['nombre'].str.upper(),
    ciudad_lower=clientes['ciudad'].str.lower()
)[['nombre_upper', 'ciudad_lower']])

# 16. SELECT TO_CHAR(fecha_registro, 'YYYY-MM') as año_mes, TO_CHAR(fecha_registro, 'DD/MM/YYYY') as fecha FROM clientes
clientes['fecha_registro'] = pd.to_datetime(clientes['fecha_registro'])
print(clientes.assign(
    año_mes=clientes['fecha_registro'].dt.strftime('%Y-%m'),
    fecha=clientes['fecha_registro'].dt.strftime('%d/%m/%Y')
)[['año_mes', 'fecha']])

# 17. SELECT ROUND(saldo, 0), FLOOR(saldo) FROM clientes;
clientes['saldo'] = clientes['saldo'].astype(float)
print(clientes.assign(
    saldo_rounded=np.round(clientes['saldo'], 0),
    saldo_floored=np.floor(clientes['saldo'])
)[['saldo_rounded', 'saldo_floored']])

# 18.SELECT * FROM clientes WHERE ciudad IN ('Madrid', 'Barcelona')
print(clientes[clientes['ciudad'].isin(['Madrid', 'Barcelona'])])

# 19. SELECT nombre FROM clientes c WHERE EXISTS (SELECT 1 FROM pedidos p WHERE p.cliente_id = c.cliente_id AND p.total > 400
def has_large_order(cliente_id):
    return not pedidos[(pedidos['cliente_id'] == cliente_id) & (pedidos['total'] > 400)].empty
print(clientes[clientes['cliente_id'].apply(has_large_order)][['nombre']])

# 20. SELECT nombre FROM clientes WHERE cliente_id NOT IN (SELECT cliente_id FROM pedidos)
clientes_with_orders = pedidos['cliente_id'].unique()
print(clientes[~clientes['cliente_id'].isin(clientes_with_orders)][['nombre']])

