from pymongo import MongoClient
from datetime import datetime
from datos import clientes, productos, ventas


client = MongoClient('mongodb://admin:admin@localhost:27017/')
db = client['pymongo']

clientes_col = db["clientes"]
productos_col = db["productos"]
ventas_col = db["ventas"]


if __name__ == "__main__":
    clientes_col.delete_many({})
    productos_col.delete_many({})
    ventas_col.delete_many({})
    productos_col.drop_indexes()
    clientes_col.drop_indexes()



    # Tarea 1
    print("Tarea 1: Poblado Inicial de los catálogos\n")
    clientes_col.insert_many(clientes)
    productos_col.insert_many(productos)
    ventas_col.insert_many(ventas)


    # Tarea 2
    print("Tarea 2: Consultas")
    print("Ejercicio 1: Obtener todos los productos")
    for p in productos_col.find({}):
        print(p)

    print("Ejercicio 2: Obtener los productos activos")
    for p in productos_col.find({"activo": True}):
        print(p)
    
    print("Ejercicio 3: Obtener los productos de categoría “computadoras”")
    for p in productos_col.find({"categoria": "computadoras"}):
        print(p)

    print("Ejercicio 4: Obtener los productos premium (precio >500€)")
    for p in productos_col.find({"precio": {"$gt": 500}}):
        print(p)
    
    print("Ejercicio 5: Obtener los clientes premium \n")
    for c in clientes_col.find({"premium": True}):
        print(c)

    # Tarea 3
    print("Tarea 3: Actualizaciones")
    print("Ejercicio 6: Actualizar precio de \"Auriculares Bluetooth\" a 249")

    resultado = productos_col.update_one(
        {"nombre": "Auriculares Bluetooth"},
        {"$set": {"precio": 249}}
    ).modified_count
    print(f"Documentos modificados: {resultado}")

    print("Ejercicio 7: Activar productos inactivos y ponerle un stock de 10")
    resultado = productos_col.update_many(
        {"activo": False},
        {"$set": {"activo": True, "stock": 10}}
    ).modified_count
    print(f"Documentos modificados: {resultado}")

    print("Ejercicio 8: Actualizar o añadir un descuento de 10 % para productos Apple")
    resultado = productos_col.update_many(
        {"marca": "Apple"},
        {"$set": {"descuento": 10}}
    ).modified_count
    print(f"Documentos modificados: {resultado}")

    print("Ejercicio 9: Aquellos productos que tengan un stock de menos de 10 unidades, incrementar el stock en 5")
    resultado = productos_col.update_many(
        {"stock": {"$lt": 10}},
        {"$inc": {"stock": 5}}
    ).modified_count
    print(f"Documentos modificados: {resultado}\n")

    # Tarea 4
    print("Tarea 4: Índices")
    print("Ejercicio 10: Crear índice en campo nombre")
    resultado = productos_col.create_index("nombre")
    print(f"Índice creado: {resultado}")

    print("Ejercicio 11: Crear índice compuesto con categoría ascendente y precio descendente")
    resultado = productos_col.create_index(
        [("categoria", 1), ("precio", -1)]
    )
    print(f"Índice creado: {resultado}")

    print("Ejercicio 12: Crear índice único en email")
    resultado = clientes_col.create_index(
        "email",
        unique=True
    )
    print(f"Índice creado: {resultado}")

    print("Ejercicio 13: Ver índices de productos")
    print(productos_col.index_information())

    # Tarea 5 
    print("\nTarea 5: Agregaciones")
    print("Ejercicio 14: ...")
    pipeline = [
    {
        "$group": {
            "_id": "$categoria",
            "cantidad_productos": {"$sum": 1},
            "precio_promedio": {"$avg": "$precio"},
            "stock_total": {"$sum": "$stock"},
            "precio_maximo": {"$max": "$precio"},
            "productos": {"$push": "$nombre"}
        }
    },
    {"$sort": {"cantidad_productos": -1}}
    ]

    for resultado in productos_col.aggregate(pipeline):
        print(resultado)

    print("Ejercicio 15: ...")
    pipeline = [
    {
        "$lookup": {
            "from": "productos",
            "localField": "producto_id",
            "foreignField": "_id",
            "as": "producto_info"
        }
    },
    {"$unwind": "$producto_info"},
    {
        "$project": {
            "_id": 0,
            "cliente": "$cliente_email",
            "producto": "$producto_info.nombre",
            "categoria": "$producto_info.categoria",
            "marca": "$producto_info.marca",
            "cantidad": 1,
            "total": 1,
            "ciudad": 1
        }
    }
    ]
    for resultado in ventas_col.aggregate(pipeline):
        print(resultado)

    print("Ejercicio 16: ...")
    pipeline = [
    {
        "$group": {
            "_id": "$ciudad",
            "total_ventas": {"$sum": "$total"},
            "total_transacciones": {"$sum": 1},
            "promedio_venta": {"$avg": "$total"}
        }
    },
    {"$sort": {"total_ventas": -1}}
 ]

    for doc in ventas_col.aggregate(pipeline):
        print(doc)
    
    # Tarea 6
    print("\nTarea 6: Borrados")
    print("Ejercicio 17: Inserta un nuevo producto con insert_one y bórralo. Los datos del producto son irrelevantes, puedes poner los que quieras")

    
    # Ejercicio 17: Insertar un nuevo producto y luego borrarlo
    nuevo_producto = {
        "nombre": "Altavoces Inalámbricos",
        "categoria": "audio",
        "precio": 79,
        "stock": 20,
        "marca": "Marshall",
        "tags": ["audio", "inalambrico", "oferta"],
        "fecha_ingreso": datetime(2025, 11, 11),
        "activo": True
    }

    insertado = productos_col.insert_one(nuevo_producto)
    print("\nProductos antes de eliminarlo")
    for p in productos_col.find():
        print(p["nombre"])

    borrado = productos_col.delete_one({"_id": insertado.inserted_id})
    print("\nProductos despues de eliminarlo")
    for p in productos_col.find():
        print(p["nombre"])







    


