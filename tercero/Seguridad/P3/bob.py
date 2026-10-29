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


def descifrarRSA_OAEP(cifrado, key):
    engineRSADescifrado = PKCS1_OAEP.new(key)
    datos = engineRSADescifrado.decrypt(cifrado)
    cadena = datos.decode("utf-8")

    return cadena


def comprobarRSA_PSS(texto, firma, key_public):
    h = SHA256.new(texto.encode("utf-8")) # Crea un nuevo objeto SHA 256, pasándole el texto
    print(h.hexdigest()) # Muestra el hash del texto en hexadecimal (NOTA: prueba a poner print(h.digest()) en la siguiente línea...)
    verifier = pss.new(key_public)
    try:
        verifier.verify(h, firma)
        return True
    except (ValueError, TypeError):
        return False
    


keyPrivateBob = cargar_RSAKey_Privada("keyPrivateBob.pem", "password")
keyPublicAlice = cargar_RSAKey_Publica("keyPublicAlice.pub")

texto  = open("mensajeCifrado.txt", "rb").read()
firma = open("firma.txt", "rb").read()

mensajeDescifrado = descifrarRSA_OAEP(texto, keyPrivateBob)
print(mensajeDescifrado)

OK = comprobarRSA_PSS(mensajeDescifrado, firma, keyPublicAlice)
print(OK)