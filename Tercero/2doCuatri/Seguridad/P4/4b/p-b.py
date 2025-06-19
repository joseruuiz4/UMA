

from Crypto.Hash import SHA256, HMAC
import base64
import json
import sys
from socket_class import SOCKET_SIMPLE_TCP
import funciones_aes
from Crypto.Random import get_random_bytes

# Paso 0: Inicializacion
########################

# Lee clave KBT
KBT = open("KBT.bin", "rb").read()

# Paso 1) B->T: KBT(Bob, Nb) en AES-GCM
#######################################

# Crear el socket de conexion con T (5551)
print("Creando conexion con T...")
socket = SOCKET_SIMPLE_TCP('127.0.0.1', 5551)
socket.conectar()

# Crea los campos del mensaje
t_n_origen = get_random_bytes(16)

# Codifica el contenido (los campos binarios en una cadena) y contruyo el mensaje JSON
msg_TE = []
msg_TE.append("Bob")
msg_TE.append(t_n_origen.hex())
json_ET = json.dumps(msg_TE)
print("B -> T (descifrado): " + json_ET)

# Cifra los datos con AES GCM
aes_engine = funciones_aes.iniciarAES_GCM(KBT)
cifrado, cifrado_mac, cifrado_nonce = funciones_aes.cifrarAES_GCM(aes_engine,json_ET.encode("utf-8"))

# Envia los datos
socket.enviar(cifrado)
socket.enviar(cifrado_mac)
socket.enviar(cifrado_nonce)

# Paso 2) T->B: KBT(K1, K2, Nb) en AES-GCM
##########################################

# Recibe la respuesta de T
cifrado = socket.recibir()
cifrado_mac = socket.recibir()
cifrado_nonce = socket.recibir()

# Descifro los datos con AES GCM
datos_descifrado_ET = funciones_aes.descifrarAES_GCM(KBT, cifrado_nonce, cifrado, cifrado_mac)

# Decodifica el contenido: K1, K2, Nb
json_ET = datos_descifrado_ET.decode("utf-8", "ignore")
print("T -> B (descifrado): " + json_ET)
msg_ET = json.loads(json_ET)

# Extraigo el contenido
t_k1, t_k2, t_nb = msg_ET
t_k1 = bytearray.fromhex(t_k1)
t_k2 = bytearray.fromhex(t_k2)

t_nb = bytearray.fromhex(t_nb)

# Cerramos el socket entre B y T, no lo utilizaremos mas
socket.cerrar() 

if(t_n_origen == t_nb):
    print("El nonce de origen coincide con el nonce de T")
else:
    print("El nonce de origen NO coincide con el nonce de T")
    sys.exit(1)

# Paso 5) A->B: KAB(Nombre) en AES-CTR con HMAC
###############################################

print("Creando conexion con Alice...")
socket_Alice = SOCKET_SIMPLE_TCP('127.0.0.1', 5553)
socket_Alice.escuchar()

# Recibe el mensaje
cifrado = socket_Alice.recibir()
cifrado_mac = socket_Alice.recibir()
cifrado_nonce = socket_Alice.recibir()

# Descifro los datos con AES CTR
ctr_engine = funciones_aes.iniciarAES_CTR_descifrado(t_k1, cifrado_nonce)
datos_descifrado_ET = funciones_aes.descifrarAES_CTR(ctr_engine, cifrado)
# Decodifica el contenido: Alice, Na
json_ET = datos_descifrado_ET.decode("utf-8", "ignore")
msg_ET = json.loads(json_ET)
name = msg_ET[0]
print("Nombre:  " + name)

hmac = HMAC.new(t_k2, cifrado)
hmac.verify(cifrado_mac)
print("HMAC verificado correctamente")

# Paso 6) B->A: KAB(Apellido) en AES-CTR con HMAC
#################################################

Apellido = "The JhonnyBerry"
respuesta = []
respuesta.append(Apellido)
json_respuesta = json.dumps(respuesta)

# Cifro la respuesta con AES CTR
ctr_engine, cifrado_nonce = funciones_aes.iniciarAES_CTR_cifrado(t_k1)
cifrado = funciones_aes.cifrarAES_CTR(ctr_engine, json_respuesta.encode("utf-8"))

# Calculo el HMAC de la respuesta
hmac = HMAC.new(t_k2, cifrado)

# Envio la respuesta a Alice
socket_Alice.enviar(cifrado)
socket_Alice.enviar(hmac.digest())
socket_Alice.enviar(cifrado_nonce)

# Paso 7) A->B: KAB(END) en AES-CTR con HMAC
############################################

# Recibe el mensaje
cifrado = socket_Alice.recibir()
cifrado_mac = socket_Alice.recibir()
cifrado_nonce = socket_Alice.recibir()

# Descifro los datos con AES CTR
ctr_engine = funciones_aes.iniciarAES_CTR_descifrado(t_k1, cifrado_nonce)
datos_descifrado = funciones_aes.descifrarAES_CTR(ctr_engine, cifrado)
# Decodifica el contenido: END
json_ET = datos_descifrado.decode("utf-8", "ignore")
msg_ET = json.loads(json_ET)
msg = msg_ET[0]
print("Mensaje recibido de Alice: " + json_ET)

# Verifico el HMAC
hmac = HMAC.new(t_k2, cifrado)
hmac.verify(cifrado_mac)
print("HMAC verificado correctamente")
# Cerramos el socket entre A y B, no lo utilizaremos mas
socket_Alice.cerrar()
