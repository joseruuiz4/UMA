from django.shortcuts import render, redirect
from django.contrib.auth import authenticate, login
from django.views.decorators.csrf import csrf_exempt
from django.http import HttpResponse, JsonResponse
import pymysql
import json

def login_view(request):
    if request.method == "POST":
        username = request.POST['username']
        password = request.POST['password']
        
        connection = pymysql.connect(
            host='localhost',      # Dirección del servidor MySQL
            user='local',     # Tu usuario de MySQL
            password='1234',  # Tu contraseña de MySQL
            database='TrabajoGI2425'   # Nombre de la base de datos
        )

        try:
            # Crear un cursor para ejecutar la consulta
            cursor = connection.cursor()
            cursor.execute("SELECT * FROM tUsuario WHERE nombre = %s AND password = %s", (username, password))

            # Obtener el resultado de la consulta
            usuario = cursor.fetchone()
            if usuario:
                # Guardar el rol del usuario en la sesión
                request.session['user_role'] = usuario[2]  # Asumiendo que el rol está en la tercera columna
                return redirect('pagina1')
            else:
                return render(request, 'login.html', {'error': "Credenciales incorrectas"})
        
        except pymysql.MySQLError as e:
            # Si hay un error en la consulta o la conexión
            return render(request, 'login.html', {'error': f"Error en la base de datos: {e}"})
        
        finally:
            # Cerrar la conexión a la base de datos
            connection.close()
    return render(request, 'login.html')


def pagina1(request):
        
    connection = pymysql.connect(
            host='localhost',      # Dirección del servidor MySQL
            user='local',     # Tu usuario de MySQL
            password='1234',  # Tu contraseña de MySQL
            database='TrabajoGI2425'   # Nombre de la base de datos
        )

    try:
            # Crear un cursor para ejecutar la consulta
            cursor = connection.cursor()
            cursor.execute("SELECT * FROM ttipopieza")

            # Obtener todos los resultados de la consulta
            materias =  cursor.fetchall()

            user_role = request.session.get('user_role', 'Invitado')
                            
            return render(request, 'pagina1.html', {'materias' : materias, 'user_role': user_role})
        
    except pymysql.MySQLError as e:
        # Si hay un error en la consulta o la conexión
        return render(request, 'login.html', {'error': f"Error en la base de datos: {e}"})
        
    finally:
        # Cerrar la conexión a la base de datos
        connection.close()


def procesar_materia_ajax(request):
    if request.method == 'POST':

        connection = pymysql.connect(
            host='localhost',      # Dirección del servidor MySQL
            user='local',     # Tu usuario de MySQL
            password='1234',  # Tu contraseña de MySQL
            database='TrabajoGI2425'   # Nombre de la base de datos
        )

        # Leer el cuerpo de la solicitud (JSON)
        data = json.loads(request.body)
        materia_id = data.get('materia_id')

        try:
            # Crear un cursor para ejecutar la consulta
            cursor = connection.cursor()
            cursor.execute(f"SELECT * FROM trabajogi2425.tpiezas WHERE ID_TIPO = %s", (materia_id))

            # Obtener todos los resultados de la consulta
            piezas =  cursor.fetchall()
            piezas_list = [
            {
                "ID": pieza[0],
                "NOMBRE": pieza[1],
                "FABRICANTE": pieza[2],
                "ID_TIPO": pieza[3]
            }
            for pieza in piezas
            ]
            return JsonResponse({"success": True, "piezas": piezas_list})

        except pymysql.MySQLError as e:
        # Si hay un error en la consulta o la conexión
            return render(request, 'login.html', {'error': f"Error en la base de datos: {e}"})
        
        finally:
        # Cerrar la conexión a la base de datos
            connection.close()
    return JsonResponse({"error": "Método no permitido"}, status=405)


def insertar_producto(request):
    if request.method == 'POST':
        data = json.loads(request.body)
        nombre = data.get('nombre')
        fabricante = data.get('fabricante')
        materia_id = data.get('materia_id')

        connection = pymysql.connect(
            host='localhost',
            user='local',
            password='1234',
            database='TrabajoGI2425'
        )

        try:
            print(nombre, fabricante, materia_id)
            cursor = connection.cursor()
            cursor.execute(
                "INSERT INTO tPiezas (NOMBRE, FABRICANTE, ID_TIPO) VALUES (%s, %s, %s)",
                (nombre, fabricante, materia_id)
            )
            connection.commit()
            return JsonResponse({"success": True})
        except pymysql.MySQLError as e:
           print('error')
           return render(request, 'login.html', {'error': f"Error en la base de datos: {e}"})
        finally:
            connection.close()
    return JsonResponse({"error": "Método no permitido"}, status=405)


def borrar_producto(request):
    if request.method == 'POST':
        data = json.loads(request.body)
        id = data.get('id')

        connection = pymysql.connect(
            host='localhost',
            user='local',
            password='1234',
            database='TrabajoGI2425'
        )

        try:
            cursor = connection.cursor()
            cursor.execute(
                "DELETE FROM tPiezas WHERE ID = %s",
                (id,)
            )
            connection.commit()
            return JsonResponse({"success": True})
        except pymysql.MySQLError as e:
            return JsonResponse({"success": False, "error": str(e)})
        finally:
            connection.close()
    return JsonResponse({"error": "Método no permitido"}, status=405)


def actualizar_producto(request):
    if request.method == 'POST':
        data = json.loads(request.body)
        id = data.get('id')
        nombre = data.get('nombre')
        fabricante = data.get('fabricante')
        materia_id = data.get('materia_id')

        connection = pymysql.connect(
            host='localhost',
            user='local',
            password='1234',
            database='TrabajoGI2425'
        )

        try:
            cursor = connection.cursor()
            cursor.execute(
                "UPDATE tPiezas SET NOMBRE = %s, FABRICANTE = %s, ID_TIPO = %s WHERE ID = %s",
                (nombre, fabricante, materia_id, id)
            )
            connection.commit()
            return JsonResponse({"success": True})
        except pymysql.MySQLError as e:
            return JsonResponse({"success": False, "error": str(e)})
        finally:
            connection.close()
    return JsonResponse({"error": "Método no permitido"}, status=405)

