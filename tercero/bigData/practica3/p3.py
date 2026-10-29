import redis
from datetime import datetime

r = redis.Redis(host='localhost', port=6379, decode_responses=True)

def registrarEquipo(id, nombre, ciudad, entrenador, fundacion):
    clave_equipo = f"equipo:{id}"
    r.hset(clave_equipo, mapping={
        "nombre": nombre,
        "ciudad": ciudad,
        "entrenador": entrenador,
        "fundacion": fundacion
    })
    print(f"Equipo {nombre} registrado con ID {id}.")

def obtenerEquipo(id):
    clave_equipo = f"equipo:{id}"
    return r.hgetall(clave_equipo)


def registrarJugador(id, nombre, edad, posicion, equipo_id):
    clave_jugador = f"jugador:{id}"
    r.hset(clave_jugador, mapping={
        "nombre": nombre,
        "edad": edad,
        "posicion": posicion,
        "equipo_id": equipo_id
    })
    print(f"Jugador {nombre} registrado con ID {id}.")

def obtenerJugador(id):
    clave_jugador = f"jugador:{id}"
    return r.hgetall(clave_jugador)

def inicializarClasificacion():
    equipos = r.keys("equipo:*")
    clasificacion = {}
    for equipo in equipos:
        equipo_id = equipo.split(":")[1]
        clasificacion[equipo_id] = 0
    if clasificacion:
        r.zadd("clasificacion", clasificacion)

def crearRankingGoleadores():
    jugadores = r.keys("jugador:*")
    ranking = {}
    for jugador in jugadores:
        jugador_id = jugador.split(":")[1]
        ranking[jugador_id] = 0
    if ranking:
        r.zadd("ranking_goleadores", ranking)

def registrarPartido(id, local, visitante, resultado, fecha):
    clave_partido = f"partido:{id}"
    r.hset(clave_partido, mapping={
        "local": local,
        "visitante": visitante,
        "resultado": resultado,
        "fecha": fecha
    })
    r.zadd("partidos_jugados", {id: datetime.strptime(fecha, "%Y-%m-%d").timestamp()})
    print(f"Partido {id} registrado: {local} -> {resultado} <- {visitante}")

def actualizarClasificacion(equipo_id, puntos):
    r.zincrby("clasificacion", puntos, equipo_id)
    print(f"Clasificación actualizada para el equipo ID {equipo_id} con +{puntos} puntos.")

def actualizarGoleadores(jugador_id, goles):
    r.zincrby("ranking_goleadores", goles, jugador_id)
    print(f"Ranking de goleadores actualizado para el jugador ID {jugador_id} con +{goles} goles.")

def programarPartido(id, local, visitante, fecha):
    clave_partido = f"partido:{id}"
    r.hset(clave_partido, mapping={
        "local": local,
        "visitante": visitante,
        "fecha": fecha
    })
    r.zadd("partidos_programados", {id: datetime.strptime(fecha, "%Y-%m-%d").timestamp()})
    print(f"Partido programado {id}: {local} vs {visitante} el {fecha}")

def obtenerClasificacion():
    clasificacion = r.zrevrange("clasificacion", 0, -1, withscores=True)
    lista = []
    for equipo_id, puntos in clasificacion:
        equipo = obtenerEquipo(equipo_id)
        lista.append((equipo.get("nombre", "Desconocido"), int(puntos)))

    return lista

def obtenerGoleadores():
    goleadores = r.zrevrange("ranking_goleadores", 0, -1, withscores=True)
    lista = []
    for jugador_id, goles in goleadores:
        jugador = obtenerJugador(jugador_id)
        lista.append((jugador.get("nombre", "Desconocido"), int(goles)))

    return lista

def obtenerHistorialPartidos():
    partidos = r.zrange("partidos_jugados", 0, -1)
    lista = []
    for partido_id in partidos:
        partido = r.hgetall(f"partido:{partido_id}")
        lista.append(partido)
    return lista


def traspasoJugador(jugador_id, antiguo_equipo, nuevo_equipo):
    traspaso = f"Traspaso: Jugador ID {jugador_id} de {antiguo_equipo} a {nuevo_equipo}"
    r.lpush("traspasos", traspaso)
    clave_jugador = f"jugador:{jugador_id}"
    r.hset(clave_jugador, "equipo_id", nuevo_equipo)
    print(f"Jugador ID {jugador_id} traspasado al equipo ID {nuevo_equipo}.")



if __name__ == "__main__":
    r.flushall()
    
    print("Ejercicio 1: Registrar equipos fundacionales")
    registrarEquipo(1, "Leones FC", "Madrid", "Carlos Ruiz", 1920)
    registrarEquipo(2, "Águilas Deportivas", "Barcelona", "Ana Martínez", 1935)
    registrarEquipo(3, "Tiburones FC", "Valencia", "David González", 1948)

    print("\nEjercicio 2: Establecer clasificacion inicial")
    inicializarClasificacion()

    print("\nEjercicio 3: Registrar jugadores clave")
    registrarJugador(1, "Luis Torres", 25, "Delantero", 1)
    registrarJugador(2, "María Rodríguez", 28, "Centrocampista", 2)
    registrarJugador(3, "Javier López", 30, "Defensa", 3)
    registrarJugador(4, "Sofía García", 22, "Delantero", 1)

    print("\nEjercicio 4: Crear ranking de goleadores")
    crearRankingGoleadores()

    print("\nEjercicio 5: Registrar primeros partidos")
    registrarPartido(1, "Leones FC", "Águilas Deportivas", "2-1", "2025-10-31")
    registrarPartido(2, "Tiburones FC", "Leones FC", "0-0", "2025-11-07")
    registrarPartido(3, "Águilas Deportivas", "Tiburones FC", "3-2", "2025-11-14")

    print("\nEjercicio 6: Actualizar clasificación")
    actualizarClasificacion(1, 3)  
    actualizarClasificacion(3, 1)  
    actualizarClasificacion(1, 1)  
    actualizarClasificacion(2, 3)

    print("\nEjercicio 7: Actualizar ranking de goleadores")
    actualizarGoleadores(1, 2) 
    actualizarGoleadores(2, 1)
    actualizarGoleadores(2, 2) 
    actualizarGoleadores(4, 1) 

    print("\nEjercicio 8: Programar próximos partidos")
    programarPartido(4, "Leones FC", "Tiburones FC", "2025-11-21")
    programarPartido(5, "Águilas Deportivas", "Leones FC", "2025-11-28")

    print("\nEjercicio 9: Consultas de clasificacion")
    clasificacion = obtenerClasificacion()
    print("Clasificación Actual:")
    for equipo, puntos in clasificacion:
        print(f"{equipo}: {puntos} puntos")
    
    goleadores = obtenerGoleadores()
    print("\nRanking de Goleadores:")
    for jugador, goles in goleadores:
        print(f"{jugador}: {goles} goles")

    historial_partidos = obtenerHistorialPartidos()
    print("\nHistorial de Partidos Jugados:")
    for partido in historial_partidos:
        print(partido)
    


    print("\nEjercicio 10: Traspaso de jugador")
    traspasoJugador(4, 1, 2)

    r.close()






