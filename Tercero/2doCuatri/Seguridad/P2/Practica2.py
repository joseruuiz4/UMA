# Ejercicio 1

# Cada bloque de texto se combina con el bloque cifrado anterior, por ello cualquier cambio aunque sea de una sola letra afrecta a todos los bloques de la cadena

from Crypto.Random import get_random_bytes
from Crypto.Cipher import DES, AES
from Crypto.Util.Padding import pad,unpad
from Crypto.Util import Counter
# Datos necesarios
key = get_random_bytes(8) # Clave aleatoria de 64 bits
IV = get_random_bytes(8)  # IV aleatorio de 64 bits para CBC
BLOCK_SIZE_DES = 8 # Bloque de 64 bits
data = "Hola amigos de la seguridad".encode("utf‐8") # Datos a cifrar
print(data)
data2 = "Hola amigas de la seguridad".encode("utf‐8") # Datos a cifrar
print(data2)
# CIFRADO #######################################################################
# Creamos un mecanismo de cifrado DES en modo CBC con un vector de inicialización IV
cipher = DES.new(key, DES.MODE_CBC, IV)
# Ciframos, haciendo que la variable “data” sea múltiplo del tamaño de bloque
ciphertext = cipher.encrypt(pad(data,BLOCK_SIZE_DES))
print(ciphertext)
ciphertext2 = cipher.encrypt(pad(data2,BLOCK_SIZE_DES))
print(ciphertext2)
# DESCIFRADO #######################################################################
# Creamos un mecanismo de (des)cifrado DES en modo CBC con un vector de inicialización IV para CBC
# Ambos, cifrado y descifrado, se crean de la misma forma
decipher_des = DES.new(key, DES.MODE_CBC, IV)

# Desciframos, eliminamos el padding, y recuperamos la cadena
new_data = unpad(decipher_des.decrypt(ciphertext), BLOCK_SIZE_DES).decode("utf‐8", "ignore")
# Imprimimos los datos descifrados
print(new_data)

new_data2 = unpad(decipher_des.decrypt(ciphertext2), BLOCK_SIZE_DES).decode("utf‐8", "ignore")
# Imprimimos los datos descifrados
print(new_data2)

print("------------------EJERCICIO 2---------------------")
# Ejercicio 2
key = get_random_bytes(16) # Clave aleatoria de 128 bits
IV = get_random_bytes(16)  # IV aleatorio 
BLOCK_SIZE_AES = 16 # Bloque de 128 bits

print("Apartado a.")
data = "Hola amigos de Seguridad".encode("utf‐8") # Datos a cifrar
print(data)

cipher = AES.new(key, AES.MODE_ECB)
ciphertext = cipher.encrypt(pad(data,BLOCK_SIZE_AES))
print(ciphertext)


decipher = AES.new(key, AES.MODE_ECB)

new_data = unpad(decipher.decrypt(ciphertext), BLOCK_SIZE_AES).decode("utf‐8", "ignore")
print(new_data)

print("\nApartado b.")
print(data)

nonce = get_random_bytes(BLOCK_SIZE_AES // 2)
ctr = Counter.new(64, prefix=nonce)
cipher = AES.new(key, AES.MODE_CTR, counter=ctr)
ciphertext = cipher.encrypt(data)
print(ciphertext)


decipher = AES.new(key, AES.MODE_CTR, counter=Counter.new(64, prefix=nonce))

new_data =decipher.decrypt(ciphertext).decode("utf‐8", "ignore")
print(new_data)


print("\nApartado c.")
print(data)

cipher = AES.new(key, AES.MODE_OFB, IV)
ciphertext = cipher.encrypt(pad(data,BLOCK_SIZE_AES))
print(ciphertext)


decipher = AES.new(key, AES.MODE_OFB, IV)

new_data = unpad(decipher.decrypt(ciphertext), BLOCK_SIZE_AES).decode("utf‐8", "ignore")
print(new_data)


print("\nApartado d.")
print(data)

cipher = AES.new(key, AES.MODE_CFB, IV)
ciphertext = cipher.encrypt(pad(data,BLOCK_SIZE_AES))
print(ciphertext)


decipher = AES.new(key, AES.MODE_CFB, IV)

new_data = unpad(decipher.decrypt(ciphertext), BLOCK_SIZE_AES).decode("utf‐8", "ignore")
print(new_data)

print("\nApartado e.")
print(data)

nonce = get_random_bytes(BLOCK_SIZE_AES)
cipher = AES.new(key, AES.MODE_GCM, nonce=nonce, mac_len=16)
ciphertext = cipher.encrypt_and_digest(data)
print(ciphertext)


decipher = AES.new(key, AES.MODE_GCM, nonce=nonce, mac_len=16)

new_data = decipher.decrypt_and_verify(ciphertext).decode("utf‐8", "ignore")
print(new_data)



