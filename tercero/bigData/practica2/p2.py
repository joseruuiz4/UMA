import redis 
r = redis.Redis(host='localhost', port=6379, decode_responses=True)

#
#   Perfil de usuario tipo Hash con clave: usuario:<id>     
#   Lista de tareas tipo List con clave: tareas:<id_usuario>  
#




if __name__ == "__main__":
    r.flushall()
    
    clave_usuario="usuario:1"

    print(f"Creando {clave_usuario}")


    # Crear y almacenar perfil del usuario
    r.hset(clave_usuario, mapping={
        "nombre": "Jose Ruiz",
        "email": "jose@email.com",
        "edad": 12
    })

    print(f"{clave_usuario} creado \n")



    clave_tareas="tareas:1"

    print(f"Anyadiendo tareas a {clave_tareas}")


    # Añadir multiples tareas
    r.rpush(clave_tareas, "Hacer practica de python redis")
    r.rpush(clave_tareas, "Estudiar")
    r.rpush(clave_tareas, "Hacer la compra", "Dormir")
    
    print(f"Tareas anyadidas a {clave_tareas} \n")


    print(f"Completando tarea de {clave_tareas}")
    #Marcar tarea como completada
    r.lpop(clave_tareas)
    print(f"Tarea completada de {clave_tareas} \n")


    # Conultar toda la informacion
    perfil = r.hgetall(clave_usuario)
    tareas_pendientes = r.lrange(clave_tareas, 0, -1)

    print("--PERFIL DE USUARIO:--")
    for campo, valor in perfil.items():
        print(f"{campo}: {valor}")

    print("\n --TAREAS PENDIENTES:--")
    if tareas_pendientes:
        for t in tareas_pendientes:
            print("-", t)
    else:
        print("No hay tareas pendientes.")





