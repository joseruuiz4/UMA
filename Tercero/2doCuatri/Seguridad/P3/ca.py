


from Crypto.PublicKey import RSA
from Crypto.Cipher import PKCS1_OAEP
from Crypto.Signature import pss
from Crypto.Hash import SHA256

def crear_RSAKey():
    key = RSA.generate(2048)

    return key

def guardar_RSAKey_Publica(fichero, key):
    key_pub = key.publickey().export_key()
    file_out = open(fichero, "wb")
    file_out.write(key_pub)
    file_out.close()

def guardar_RSAKey_Privada(fichero, key, password):
    key_cifrada = key.export_key(passphrase=password, pkcs=8, protection="scryptAndAES128-CBC")
    file_out = open(fichero, "wb")
    file_out.write(key_cifrada)
    file_out.close()


keyAlice = crear_RSAKey()
keyBob = crear_RSAKey()

guardar_RSAKey_Publica("keyPublicAlice.pub", keyAlice)
guardar_RSAKey_Privada("keyPrivateAlice.pem", keyAlice, "password")

guardar_RSAKey_Publica("keyPublicBob.pub", keyBob)
guardar_RSAKey_Privada("keyPrivateBob.pem", keyBob, "password")



