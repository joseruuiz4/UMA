def cifradoCesarAlfabetoInglesMAY(cadena):
    """Devuelve un cifrado Cesar tradicional (+3)"""
    # Definir la nueva cadena resultado
    resultado = ''
    # Realizar el "cifrado", sabiendo que A = 65, Z = 90, a = 97, z = 122
    i = 0
    while i < len(cadena):
        # Recoge el caracter a cifrar
        ordenClaro = ord(cadena[i])
        ordenCifrado = 0
        # Cambia el caracter a cifrar
        if (ordenClaro >= 65 and ordenClaro <= 90):
            ordenCifrado = (((ordenClaro - 65) + 3) % 26) + 65

        # Añade el caracter cifrado al resultado
        resultado = resultado + chr(ordenCifrado)
        i = i + 1
    # devuelve el resultado
    return resultado

# APARTADO A

def descifradoCesarAlfabetoInglesMAY(cadena):
    resultado = ''
    i = 0
    while i < len(cadena):
        ordenCifrado = ord(cadena[i])
        ordenDescifrado = 0

        if(ordenCifrado >= 65 and ordenCifrado <= 90):
            ordenDescifrado = (((ordenCifrado - 65)-3)%26) + 65
        resultado = resultado + chr(ordenDescifrado)
        i = i + 1
    return resultado



# APARTADO B
def cifradoCesarAlfabetoInglesMAYMIN(cadena):
    resultado = ''
    i = 0
    while i < len(cadena):
        ordenClaro = ord(cadena[i])
        ordenCifrado = 0

        if (ordenClaro >= 65 and ordenClaro <= 90):
            ordenCifrado = (((ordenClaro - 65) + 3) % 26) + 65
        elif (ordenClaro >= 97 and ordenClaro <= 122):
            ordenCifrado = (((ordenClaro - 97) + 3) % 26) + 97
        resultado = resultado + chr(ordenCifrado)
        i = i + 1

    return resultado

def descifradoCesarAlfabetoInglesMAYMIN(cadena):
    resultado = ''
    i = 0
    while i < len(cadena):
        ordenCifrado = ord(cadena[i])
        ordenDescifrado = 0

        if(ordenCifrado >= 65 and ordenCifrado <= 90):
            ordenDescifrado = (((ordenCifrado - 65)-3)%26) + 65
        elif(ordenCifrado >= 97 and ordenCifrado <= 122):
            ordenDescifrado = (((ordenCifrado - 97)-3)%26) + 97
        resultado = resultado + chr(ordenDescifrado)
        i = i + 1
    return resultado

# APARTADO C
def cifradoCesarAlfabetoInglesMAYMINGeneralizado(cadena):
    resultado = ''
    i = 0
    d = int(input("Introduzca un desplazamiento: "))

    while i < len(cadena):
        ordenClaro = ord(cadena[i])
        ordenCifrado = 0

        if (ordenClaro >= 65 and ordenClaro <= 90):
            ordenCifrado = (((ordenClaro - 65) + d) % 26) + 65
        elif (ordenClaro >= 97 and ordenClaro <= 122):
            ordenCifrado = (((ordenClaro - 97) + d) % 26) + 97
        resultado = resultado + chr(ordenCifrado)
        i = i + 1

    return resultado

def descifradoCesarAlfabetoInglesMAYMINGeneralizado(cadena):
    resultado = ''
    i = 0
    d = int(input("Introduzca un desplazamiento: "))

    while i < len(cadena):
        ordenCifrado = ord(cadena[i])
        ordenDescifrado = 0

        if(ordenCifrado >= 65 and ordenCifrado <= 90):
            ordenDescifrado = (((ordenCifrado - 65)-d)%26) + 65
        elif(ordenCifrado >= 97 and ordenCifrado <= 122):
            ordenDescifrado = (((ordenCifrado - 97)-d)%26) + 97
        resultado = resultado + chr(ordenDescifrado)
        i = i + 1
    return resultado

print('\nApartado A')
claroCESARMAY = 'VENI VIDI VINCI AURIA'
print(claroCESARMAY)
cifradoCESARMAY = cifradoCesarAlfabetoInglesMAY(claroCESARMAY) 
print(cifradoCESARMAY)
descifradoCESARMAY = descifradoCesarAlfabetoInglesMAY(cifradoCESARMAY)
print(descifradoCESARMAY)


print('\nApartado B')
claroCESARMAY = 'VENI vidi VINCI auria '
print(claroCESARMAY)
cifradoCESARMAY = cifradoCesarAlfabetoInglesMAYMIN(claroCESARMAY) 
print(cifradoCESARMAY)
descifradoCESARMAY = descifradoCesarAlfabetoInglesMAYMIN(cifradoCESARMAY)
print(descifradoCESARMAY)


print('\nApartado C')
claroCESARMAY = 'VENI vidi VINCI auria AParTADO c'
print(claroCESARMAY)
cifradoCESARMAY = cifradoCesarAlfabetoInglesMAYMINGeneralizado(claroCESARMAY) 
print(cifradoCESARMAY)
descifradoCESARMAY = descifradoCesarAlfabetoInglesMAYMINGeneralizado(cifradoCESARMAY)
print(descifradoCESARMAY)