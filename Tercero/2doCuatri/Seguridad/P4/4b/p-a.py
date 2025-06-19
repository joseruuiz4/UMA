
from Crypto.Hash import SHA256, HMAC
import base64
import json
import sys
from socket_class import SOCKET_SIMPLE_TCP
import funciones_aes
from Crypto.Random import get_random_bytes

# Paso 0: Inicializacion
########################

# Lee clave KAT
KAT = open("KAT.bin", "rb").read()

# Paso 3) A->T: KAT(Alice, Na) en AES-GCM
#########################################

# Crear el socket de conexion con T (5552)
print("Creando conexion con T...")
socket = SOCKET_SIMPLE_TCP('127.0.0.1', 5552)
socket.conectar()

# Crea los campos del mensaje
t_n_origen = get_random_bytes(16)

# Codifica el contenido (los campos binarios en una cadena) y construyo el mensaje JSON
msg_AT = []
msg_AT.append("Alice")
msg_AT.append(t_n_origen.hex())
json_AT = json.dumps(msg_AT)
print("A -> T (descifrado): " + json_AT)

# Cifra los datos con AES GCM
aes_engine = funciones_aes.iniciarAES_GCM(KAT)
cifrado, cifrado_mac, cifrado_nonce = funciones_aes.cifrarAES_GCM(aes_engine, json_AT.encode("utf-8"))

# Envia los datos
socket.enviar(cifrado)
socket.enviar(cifrado_mac)
socket.enviar(cifrado_nonce)



# Paso 4) T->A: KAT(K1, K2, Na) en AES-GCM
##########################################

# Recibe la respuesta de T
cifrado = socket.recibir()
cifrado_mac = socket.recibir()
cifrado_nonce = socket.recibir()

# Descifro los datos con AES GCM
datos_descifrado_AT = funciones_aes.descifrarAES_GCM(KAT, cifrado_nonce, cifrado, cifrado_mac)

# Decodifica el contenido: K1, K2, Na
json_AT = datos_descifrado_AT.decode("utf-8", "ignore")
print("T -> A (descifrado): " + json_AT)
msg_AT = json.loads(json_AT)

# Extraigo el contenido
t_k1, t_k2, t_na = msg_AT
t_k1 = bytearray.fromhex(t_k1)
t_k2 = bytearray.fromhex(t_k2)
t_na = bytearray.fromhex(t_na)

# Cierra el socket con T, no lo utilizaremos mas
socket.cerrar()

if(t_na == t_n_origen):
    print("El nonce recibido es correcto")
else:
    print("El nonce recibido no es correcto")
    sys.exit(1)

# Paso 5) A->B: KAB(Nombre) en AES-CTR con HMAC
###############################################

print("Creando conexion con Bob...")
socket_Bob = SOCKET_SIMPLE_TCP('127.0.0.1', 5553)
socket_Bob.conectar()

# Crea los campos del mensaje
t_nombre = "Jhonny"
msg_AB = []
msg_AB.append(t_nombre)
msg_AB.append(t_na.hex())
json_AB = json.dumps(msg_AB)

# Cifra los datos con AES CTR
ctr_engine, nonce = funciones_aes.iniciarAES_CTR_cifrado(t_k1)
cifrado = funciones_aes.cifrarAES_CTR(ctr_engine, json_AB.encode("utf-8"))

# Calcula el HMAC del mensaje cifrado
hmac = HMAC.new(t_k2, cifrado)

# Envia los datos
socket_Bob.enviar(cifrado)
socket_Bob.enviar(hmac.digest())
socket_Bob.enviar(nonce)

# Paso 6) B->A: KAB(Apellido) en AES-CTR con HMAC
#################################################

# Recibe la respuesta de Bob
cifrado = socket_Bob.recibir()
cifrado_mac = socket_Bob.recibir()
cifrado_nonce = socket_Bob.recibir()

# Descifro los datos con AES CTR
ctr_engine = funciones_aes.iniciarAES_CTR_descifrado(t_k1, cifrado_nonce)
datos_descifrado_BA = funciones_aes.descifrarAES_CTR(ctr_engine, cifrado)
json_BA = datos_descifrado_BA.decode("utf-8", "ignore")
msg_BA = json.loads(json_BA)
apellido_B = msg_BA[0]

print("Apellido recibido de Bob: " + apellido_B)

# Verifica el HMAC
hmac_B = HMAC.new(t_k2, cifrado)
hmac_B.verify(cifrado_mac)
print("HMAC verificado correctamente")

# Paso 7) A->B: KAB(END) en AES-CTR con HMAC
############################################

# Crea el mensaje de finalizacion
msg_AB_END = []
msg_AB_END.append("END")
json_AB_END = json.dumps(msg_AB_END)
# Cifra el mensaje de finalizacion con AES CTR
ctr_engine, cifrado_nonce = funciones_aes.iniciarAES_CTR_cifrado(t_k1)
cifrado = funciones_aes.cifrarAES_CTR(ctr_engine, json_AB_END.encode("utf-8"))

hmac = HMAC.new(t_k2, cifrado)
# Envia el mensaje de finalizacion a Bob
socket_Bob.enviar(cifrado)
socket_Bob.enviar(hmac.digest())
socket_Bob.enviar(cifrado_nonce)
# Cierra el socket con Bob, no lo utilizaremos mas
socket_Bob.cerrar()