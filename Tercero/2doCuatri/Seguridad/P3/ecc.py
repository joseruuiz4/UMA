from Crypto.PublicKey import ECC
from Crypto.Hash import SHA256
from Crypto.Signature import DSS

# Ver https://pycryptodome.readthedocs.io/en/latest/src/public_key/ecc.html
# Ver https://pycryptodome.readthedocs.io/en/latest/src/signature/dsa.html 

def crear_ECCKey():
    # Use 'NIST P-256'
    key = ECC.generate(curve='p256')
    return key

def guardar_ECCKey_Privada(fichero, key, password):
    with open(fichero, "wt") as f:
        data = key.export_key(format="PEM", passphrase=password, protection="PBKDF2WithHMAC/SHA256/AES256-CBC/PKCS7Padding", prot_params={"iteration_count":131072})
        f.write(data)

def cargar_ECCKey_Privada(fichero, password):
    with open(fichero, "rt") as f:
        data = f.read()
        key = ECC.import_key(data, password)
    return key

def guardar_ECCKey_Publica(fichero, key):
    with open(fichero, "wt") as f:
        data  =key.public_key().export_key(format="PEM")

def cargar_ECCKey_Publica(fichero):
    with open(fichero, "rt") as f:
        data = f.read()
        key_pub = ECC.import_key(data)
    return key_pub

# def cifrarECC_OAEP(cadena, key):
# El cifrado con ECC (ECIES) aun no está implementado
# Por lo tanto, no se puede implementar este método aun en la versión 3.9.7 
#    return cifrado

# def descifrarECC_OAEP(cifrado, key):
# El cifrado con ECC (ECIES) aun no está implementado
# Por lo tanto, no se puede implementar este método aun en la versión 3.9.7 
#    return cadena

def firmarECC_PSS(texto, key_private):
    h = SHA256.new(texto)
    signer = DSS.new(key_private, "fips-186-3")
    signature = signer.sign(h)
    return signature

def comprobarECC_PSS(texto, firma, key_public):
    h = SHA256.new(texto)
    verifier = DSS.new(key_public, "fips-186-3")
    try:
        verifier.verify(h, firma)
        return True
    except (ValueError, TypeError):
        return False