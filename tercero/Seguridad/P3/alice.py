from Crypto.PublicKey import RSA
from Crypto.Cipher import PKCS1_OAEP
from Crypto.Signature import pss
from Crypto.Hash import SHA256



def cargar_RSAKey_Privada(fichero, password):
    key_cifrada = open(fichero, "rb").read()
    key = RSA.import_key(key_cifrada, passphrase=password)

    return key


def cargar_RSAKey_Publica(fichero):
    keyFile = open(fichero, "rb").read()
    key_pub = RSA.import_key(keyFile)

    return key_pub

def cifrarRSA_OAEP(cadena, key):
    datos = cadena.encode("utf-8")
    engineRSACifrado = PKCS1_OAEP.new(key)
    cifrado = engineRSACifrado.encrypt(datos)

    return cifrado



def firmarRSA_PSS(texto, key_private):
    h = SHA256.new(texto.encode("utf-8")) # Crea un nuevo objeto SHA 256, pasándole el texto
    print(h.hexdigest()) # Muestra el hash del texto en hexadecimal (NOTA: prueba a poner print(h.digest()) en la siguiente línea...)
    signature = pss.new(key_private).sign(h)

    return signature


    
keyPrivadaAlice = cargar_RSAKey_Privada("keyPrivateAlice.pem", "password")
keyPublicaBob = cargar_RSAKey_Publica("keyPublicBob.pub")


msg = "Hola amigos de la seguridad"
mensajeCifrado = cifrarRSA_OAEP(msg, keyPublicaBob)
firma = firmarRSA_PSS(msg, keyPrivadaAlice)  # Se firma el mensaje original o el cifrado¿?¿?

file_out = open("mensajeCifrado.txt", "wb")
file_out.write(mensajeCifrado)
file_out.close()

file_out = open("firma.txt", "wb")
file_out.write(firma)
file_out.close()