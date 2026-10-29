import json

import funciones_aes
import funciones_rsa

from Crypto.Hash import HMAC
from socket_class import SOCKET_SIMPLE_TCP


socket = SOCKET_SIMPLE_TCP('127.0.0.1', 5000)
socket.conectar()


key1 = funciones_aes.crear_AESKey()
key2 = funciones_aes.crear_AESKey()

keyPubBob = funciones_rsa.cargar_RSAKey_Publica("rsa_bob.pub")
keyPivAlice = funciones_rsa.cargar_RSAKey_Privada("rsa_alice.pem", "alice")
k1Cifrado = funciones_rsa.cifrarRSA_OAEP_BIN(key1, keyPubBob)
k2Cifrado = funciones_rsa.cifrarRSA_OAEP_BIN(key2, keyPubBob)

firmaAlicekey = funciones_rsa.firmarRSA_PSS(key1 + key2, keyPivAlice)

socket.enviar(k1Cifrado)
socket.enviar(k2Cifrado)
socket.enviar(firmaAlicekey)




aes, nonce = funciones_aes.iniciarAES_CTR_cifrado(key1)

texto = "Alice"

cipherText = aes.encrypt(texto.encode("utf-8"))

# generamos HMAC
mensajeFirmado = cipherText + nonce
h = HMAC.new(key2,mensajeFirmado).digest()

datos = []
datos.append(cipherText.hex())
datos.append(nonce.hex())
datos.append(h.hex())
jStr = json.dumps(datos)

socket.enviar(jStr.encode("utf-8"))

socket.cerrar()