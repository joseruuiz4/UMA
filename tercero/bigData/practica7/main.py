import pymongo
from pymongo import MongoClient
import datetime
from datetime import datetime

def crear_conexion():
    client = MongoClient("mongodb://admin:admin@localhost:27017/")
    db = client["practica7"]
    db.categorias.drop()
    db.estudiantes.drop()
    db.prestamos.drop()
    db.libros.drop()
    return client, db



def insertar_Categorias(db):
    categorias = [[
            {
                "_id": 1,
                "nombre": "Ciencia Ficción",
                "descripcion": "Novelas de ciencia ficción y fantasía",
                "libros_count": 0,
                "ubicacion": "Ala A"
            },
            {
                "_id": 2,
                "nombre": "Literatura Clásica", 
                "descripcion": "Obras clásicas de la literatura universal",
                "libros_count": 0,
                "ubicacion": "Ala B"
            },
            {
                "_id": 3,
                "nombre": "Tecnología",
                "descripcion": "Libros de programación y tecnología",
                "libros_count": 0,
                "ubicacion": "Ala C"
            },
            {
                "_id": 4,
                "nombre": "Ciencias Sociales",
                "descripcion": "Sociología, psicología y antropología",
                "libros_count": 0,
                "ubicacion": "Ala D"
            }
    ]]
    db.categorias.insert_many(categorias)

def insertar_Estudiantes(db):
    estudiantes = [

            {

                "nombre": "Ana García López",

                "email": "ana.garcia@universidad.edu",

                "carrera": "Ingeniería Informática",

                "semestre": 5,

                "edad": 21,

                "ciudad": "Madrid",

                "fecha_registro": datetime(2022, 9, 1),

                "activo": True

            },

            {

                "nombre": "Carlos Rodríguez Martín",

                "email": "carlos.rodriguez@universidad.edu", 

                "carrera": "Literatura",

                "semestre": 3,

                "edad": 20,

                "ciudad": "Barcelona",

                "fecha_registro": datetime(2023, 1, 15),

                "activo": True

            },

            {

                "nombre": "María Chen Wang",

                "email": "maria.chen@universidad.edu",

                "carrera": "Psicología", 

                "semestre": 7,

                "edad": 23,

                "ciudad": "Valencia",

                "fecha_registro": datetime(2021, 9, 1),

                "activo": True

            },

            {

                "nombre": "David Martínez Ruiz",

                "email": "david.martinez@universidad.edu",

                "carrera": "Ingeniería Informática",

                "semestre": 6,

                "edad": 22,

                "ciudad": "Madrid",

                "fecha_registro": datetime(2022, 2, 1),

                "activo": False

            }
    ]
    db.estudiantes.insert_many(estudiantes)



def insertar_Libros(db):
    libros = [

            {

                "titulo": "1984",

                "autor": "George Orwell",

                "genero": "Ciencia Ficción",

                "año_publicacion": 1949,

                "isbn": "978-0451524935",

                "editorial": "Signet Classics",

                "paginas": 328,

                "precio": 12.99,

                "stock": 5,

                "disponible": True,

                "tags": ["distopía", "política", "clásico"],

                "fecha_ingreso": datetime(2023, 1, 15),

                "categoria_id": 1

            },

            {

                "titulo": "Cien años de soledad",

                "autor": "Gabriel García Márquez", 

                "genero": "Realismo Mágico",

                "año_publicacion": 1967,

                "isbn": "978-0307474728",

                "editorial": "Penguin Random House",

                "paginas": 417,

                "precio": 15.50,

                "stock": 3,

                "disponible": True,

                "tags": ["realismo mágico", "latinoamericano", "clásico"],

                "fecha_ingreso": datetime(2023, 2, 20),

                "categoria_id": 2

            },

            {

                "titulo": "Python Crash Course",

                "autor": "Eric Matthes",

                "genero": "Programación",

                "año_publicacion": 2019,

                "isbn": "978-1593279288",

                "editorial": "No Starch Press",

                "paginas": 544,

                "precio": 39.99,

                "stock": 8,

                "disponible": True,

                "tags": ["python", "programación", "aprendizaje"],

                "fecha_ingreso": datetime(2023, 3, 10),

                "categoria_id": 3

            },

            {

                "titulo": "El Principito",

                "autor": "Antoine de Saint-Exupéry",

                "genero": "Literatura Infantil",

                "año_publicacion": 1943,

                "isbn": "978-0156012195",

                "editorial": "Harcourt Brace",

                "paginas": 96,

                "precio": 9.99,

                "stock": 0,

                "disponible": False,

                "tags": ["infantil", "filosofía", "clásico"],

                "fecha_ingreso": datetime(2023, 1, 5),

                "categoria_id": 2

            },

            {

                "titulo": "Clean Code",

                "autor": "Robert C. Martin",

                "genero": "Programación",

                "año_publicacion": 2008,

                "isbn": "978-0132350884",

                "editorial": "Prentice Hall",

                "paginas": 464,

                "precio": 47.99,

                "stock": 6,

                "disponible": True,

                "tags": ["programación", "calidad", "best practices"],

                "fecha_ingreso": datetime(2023, 4, 15),

                "categoria_id": 3

            }

    ]
    db.libros.insert_many(libros)



def insertar_Prestamos(db):
    libro_1984 = db.libros.find_one({"titulo": "1984"})
    libro_python = db.libros.find_one({"titulo": "Python Crash Course"})
    estudiante_ana = db.estudiantes.find_one({"nombre": "Ana García López"})
    estudiante_carlos = db.estudiantes.find_one({"nombre": "Carlos Rodríguez Martín"})
    prestamos = [

            {

                "libro_id": libro_1984["_id"],

                "estudiante_id": estudiante_ana["_id"],

                "fecha_prestamo": datetime(2024, 1, 10),

                "fecha_devolucion": datetime(2024, 1, 24),

                "fecha_devolucion_real": None,
                "estado": "activo",

                "multa": 0

            },

            {

                "libro_id": libro_python["_id"],

                "estudiante_id": estudiante_carlos["_id"],

                "fecha_prestamo": datetime(2024, 1, 5),

                "fecha_devolucion": datetime(2024, 1, 19),

                "fecha_devolucion_real": datetime(2024, 1, 18),
                "estado": "devuelto",

                "multa": 0

            }

    ]

def ejercicio_1(db):
    db.Libros.update_one({"titulo":"1984"},{"$inc": {"stock":-3}})
    libro = db.Libros.find_one({"titulo":"1984"})
    print(f"Libro actualizado: {libro}")

def ejercicio_2(db):
    db.Libros.update_many({"año_publicacion":{"$gte":2000}}, {"$set": {"descuento":15}})
    print("Libros actualizados")
    libros = db.Libros.find({"año_publicacion":{"$gte":2000}})
    for libro in libros:
        print(libro)

def ejercicio_3(db):
    db.Categorias.update_one({"nombre": "Matemáticas"}, {"$set": {"ubicacion": "Ala E"},"$setOnInsert":{"nombre": "Matemáticas","descripcion": "Libros de matemáticas y cálculo","libros_count": 0}},upsert=True)
    categoria = db.Categorias.find_one({"nombre": "Matemáticas"})
    print(categoria)

def ejercicio_4(db):
    libros_disponibles = db.Libros.find({"disponible":True})
    for libro in libros_disponibles:
        print(libro)

def ejercicio_5(db):
    estudiantes_informatica = db.Estudiantes.find({"carrera":"Ingeniería Informática"})
    for estudiante in estudiantes_informatica:
        print(estudiante)

def ejercicio_6(db):
    libros_20 = db.Libros.find({"precio":{"$gt": 20}})
    for libro in libros_20:
        print(libro)

def ejercicio_7(db):
    pipeline = [{"$match": {"disponible": True,"stock": {"$gt": 0}}},{"$group": {"_id": "$genero", "total_libros": {"$sum": 1}, "stock_total": {"$sum": "$stock"}, "precio_promedio": {"$avg": "$precio"}, "libros": {"$push": "$titulo"}}}]
    resultados = list(db.Libros.aggregate(pipeline))
    for resultado in resultados:
        print(resultado)


def ejercicio_8(db):
    pipeline = [{"$bucket": {"groupBy": "$precio","boundaries": [0, 10, 20, 30, 50],"default": "50+", "output": { "n_libros": {"$sum": 1}, "precio_promedio": {"$avg": "$precio"}}}}]
    resultados = list(db.Libros.aggregate(pipeline))
    for resultado in resultados:
        print(resultado)



def ejercicio_9(db):
    pipeline = [
{
"$lookup": {
"from": "Libros", 
"localField": "libro_id", 
"foreignField": "_id", 
"as": "info_libro" 
}
},
{
"$unwind": "$info_libro"
},
{
"$lookup": {
"from": "Estudiantes", 
"localField": "estudiante_id", 
"foreignField": "_id", 
"as": "info_estudiante" 
}
},
{
"$unwind": "$info_estudiante"
},
{
"$project": {
"_id": 0,
"estado_prestamo": "$estado",
"fecha_prestamo": "$fecha_prestamo",
"fecha_devolucion": "$fecha_devolucion",
"fecha_devolucion_real": "$fecha_devolucion_real",
"multa": "$multa",
"libro_titulo": "$info_libro.titulo",
"libro_autor": "$info_libro.autor",
"libro_genero": "$info_libro.genero",
"libro_precio": "$info_libro.precio",
"estudiante_nombre": "$info_estudiante.nombre",
"estudiante_email": "$info_estudiante.email",
"estudiante_carrera": "$info_estudiante.carrera",
"estudiante_ciudad": "$info_estudiante.ciudad"
}
}
]
    resultados = db.Prestamos.aggregate(pipeline)
    for resultado in resultados:
        print(resultado)


def ejercicio_10(db):
    pipeline = [
    {
    "$match":{
    "activo":True 
    }
    },
    {
    "$group": {
    "_id": "$ciudad", 
    "total_estudiantes": {"$sum": 1}, 
    "edad_promedio": {"$avg": "$edad"}, 
    "carreras_unicas" : {"$addToSet": "$carrera"}, 
    "nombres_estudiantes": {"$push": "$nombre"} 
    }
    },
    {
    "$project": {
    "_id": 1,
    "total_estudiantes": 1,
    "edad_promedio": 1,
    "cantidad_carreras": {"$size": "$carreras_unicas"}, 
    "carreras_unicas": 1,
    "nombres_estudiantes": 1
    }
    }]
    resultados = db.Estudiantes.aggregate(pipeline)
    for resultado in resultados:
        print(resultado)


def ejercicio_11(db):
    estudiante = {
    "nombre": "Estudiante Temporal",
    "email": "estudiante.temporal@universidad.edu",
    "carrera": "Temporal",
    "semestre": 1,
    "edad": 18,
    "ciudad": "Málaga",
    "fecha_registro": "2022-11-01",
    "activo": False
    }
    db.Estudiantes.insert_one(estudiante)
    print(f"\nEstudiante insertado:")
    print(estudiante)
    resultado_delete = db.Estudiantes.delete_one({"_id": estudiante["_id"]})
    print(f"\nEstudiantes eliminados: {resultado_delete.deleted_count}")

def ejercicio_12(db):
    db.Libros.update_many(
    {"genero": "Programación"}, {"$set": {"ubicacion": "Sala de ordenadores", "etiqueta_especial": "Tecnología"}})
    print("Libros de Programación actualizados:")
    libros_programacion = db.Libros.find({"genero": "Programación"})
    for libro in libros_programacion:
        print(libro)

if __name__ == "__main__":
    client, db = crear_conexion()
    insertar_Categorias(db)
    insertar_Estudiantes(db)
    insertar_Libros(db)
    insertar_Prestamos(db)
    print("\nEjercicio 1:")
    ejercicio_1(db)
    print("\nEjercicio 2:")
    ejercicio_2(db)
    print("\nEjercicio 3:")
    ejercicio_3(db)
    print("\nEjercicio 4:")
    ejercicio_4(db)
    print("\nEjercicio 5:")
    ejercicio_5(db)
    print("\nEjercicio 6:")
    ejercicio_6(db)
    print("\nEjercicio 7:")
    ejercicio_7(db)
    print("\nEjercicio 8:")
    ejercicio_8(db)
    print("\nEjercicio 9:")
    ejercicio_9(db)
    print("\nEjercicio 10:")
    ejercicio_10(db)
    print("\nEjercicio 11:")
    ejercicio_11(db)
    print("\nEjercicio 12:")
    ejercicio_12(db)
    client.close()