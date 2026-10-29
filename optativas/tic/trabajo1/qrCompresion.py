# Primera version del codigo del algoritmo de compresion basado en codigos QR

import random
import string

MIX_CHARS = r" .,;:!?¡¿'\"()[]{}<>@#$/%&=+-*_~^`|\\ñÑáéíóúÁÉÍÓÚüÜ"


def detectar_modo(char):
    if char.isdigit():
        return "NUM"
    elif ('A' <= char <= 'Z') or ('a' <= char <= 'z'):
        return "ALF"
    else:
        return "MIX"
    
def separar_por_modo(cadena):
    if not cadena:
        return []
    
    segmentos = []
    modo_actual = detectar_modo(cadena[0])
    segmento = cadena[0]

    for c in cadena[1:]:
        modo_c = detectar_modo(c)
        if modo_c == modo_actual:
            segmento += c
        else:
            segmentos.append((modo_actual, segmento))
            segmento = c
            modo_actual = modo_c
    segmentos.append((modo_actual, segmento))
    return segmentos

def comprimir_segmento(modo, segmento):
    bits = ""
    if modo == "NUM":
        # NUM -> 10 bits por cada 3 digitos, 7 bits por 2 digitos, 4 bits por 1 digito
        for i in range(0, len(segmento), 3):
            block = segmento[i:i+3]
            num = int(block)
            length = 10 if len(block) == 3 else (7 if len(block) == 2 else 4)
            bits += format(num, f'0{length}b')
    elif modo == "ALF": 
        # ALF -> 6 bits por caracter
        for c in segmento:
            bits += format(ord(c) % 64, '06b')
    else:  # MIX -> 8 bits por caracter
        for c in segmento:
            if c in MIX_CHARS:
                idx = MIX_CHARS.index(c)
                bits += format(idx, '06b')
            else:
                raise ValueError(f"Carácter '{c}' no está definido")
    return bits

def comprimir_cadena(cadena):
    modos = {"NUM": "00", "ALF": "01", "MIX": "10"}
    codificado = ""
    for modo, segmento in separar_por_modo(cadena):
        codificado += modos[modo]
        codificado += comprimir_segmento(modo, segmento)
    return codificado

def comprimir_cadena_con_longitud(cadena):
    modos = {"NUM": "00", "ALF": "01", "MIX": "10"}
    codificado = ""
    for modo, segmento in separar_por_modo(cadena):
        if len(segmento) > 7:
            partes = [segmento[i:i+7] for i in range(0, len(segmento), 7)]
        else:
            partes = [segmento]
        for parte in partes:
            codificado += modos[modo]
            codificado += format(len(parte), '03b')  # longitud del segmento en 3 bits
            codificado += comprimir_segmento(modo, parte)
    return codificado

def comprimir_cadena_con_longitud_k(cadena, k):
    modos = {"NUM": "00", "ALF": "01", "MIX": "10"}
    grado = (1 << k) - 1
    codificado = ""
    for modo, segmento in separar_por_modo(cadena):
        if len(segmento) > grado:
            partes = [segmento[i:i+grado] for i in range(0, len(segmento), grado)]
        else:
            partes = [segmento]
        for parte in partes:
            codificado += modos[modo]
            codificado += format(len(parte), f'0{k}b')  # longitud del segmento en k bits
            codificado += comprimir_segmento(modo, parte)
    return codificado


def descomprimir(bits):
    code_to_mode = {"00": "NUM", "01": "ALF", "10": "MIX"}
    i = 0
    texto = ""

    while i + 2 <= len(bits):  # mientras queden bits para un marcador
        # Leer modo (2 bits)
        mode_bits = bits[i:i+2]
        i += 2
        if mode_bits not in code_to_mode:
            break
        modo = code_to_mode[mode_bits]

        # Leer longitud (3 bits)
        if i + 3 > len(bits):
            break
        seg_len = int(bits[i:i+3], 2)  # número de símbolos
        i += 3

        # Decodificar según el modo
        if modo == "NUM":
            # leer seg_len dígitos en bloques de 3→10 bits, 2→7 bits, 1→4 bits
            digitos_restantes = seg_len
            while digitos_restantes > 0:
                bloque = 3 if digitos_restantes >= 3 else (2 if digitos_restantes == 2 else 1)
                bits_bloque = 10 if bloque == 3 else (7 if bloque == 2 else 4)
                if i + bits_bloque > len(bits):
                    return texto
                valor = int(bits[i:i+bits_bloque], 2)
                i += bits_bloque
                texto += str(valor).zfill(bloque)
                digitos_restantes -= bloque

        elif modo == "ALF":
            # cada carácter 6 bits
            for _ in range(seg_len):
                if i + 6 > len(bits):
                    return texto
                val = int(bits[i:i+6], 2)
                i += 6
                texto += chr((val % 64) + 64)  # misma regla que usaste al codificar

        else:  # MIX
            for _ in range(seg_len):
                if i + 6 > len(bits):
                    return texto
                val = int(bits[i:i+6], 2)
                i += 6
                texto += MIX_CHARS[val]

    return texto




def fuente_aleatoria(longitud):
    chars = ''.join(chr(i) for i in range(32, 127)) + "ñÑáéíóúÁÉÍÓÚüÜ"
    return ''.join(random.choice(chars) for _ in range(longitud))


    


def bits_original(texto):
    return len(texto) * 8

def ratio_compresion_bits(texto, codificado_bits):
    b_orig = bits_original(texto)
    return b_orig / len(codificado_bits) if len(codificado_bits) > 0 else float('inf')

def probar_entradas(entradas, imprimir_detalle=True):
    resultados = []
    for texto in entradas:
        # Sin longitud (tu base)
        s_sin = comprimir_cadena(texto)
        r_sin = ratio_compresion_bits(texto, s_sin)

        # Con distintas longitudes
        resultados_k = {}
        for k in [3, 4, 6, 16]:
            s_k = comprimir_cadena_con_longitud_k(texto, k)
            resultados_k[k] = ratio_compresion_bits(texto, s_k)

        resultados.append((texto, len(texto), r_sin, resultados_k))

        if imprimir_detalle:
            print(f"\nTexto: {texto[:20]}...")
            print(f"  Ratio sin longitud: {r_sin:.3f}")
            for k, r in resultados_k.items():
                print(f"  Ratio con {k:>2} bits de longitud: {r:.3f}")
            print("-" * 40)
    return resultados


import matplotlib.pyplot as plt

def graficar_resultados(resultados):
    textos = [t[:10] + "..." for t, *_ in resultados]
    r_sin = [r[2] for r in resultados]
    r_k3  = [r[3][3] for r in resultados]
    r_k4  = [r[3][4] for r in resultados]
    r_k6  = [r[3][6] for r in resultados]
    r_k16 = [r[3][16] for r in resultados]

    plt.plot(textos, r_sin, 'o-', label='Sin longitud')
    plt.plot(textos, r_k3,  'o-', label='Con 3 bits')
    plt.plot(textos, r_k4,  'o-', label='Con 4 bits')
    plt.plot(textos, r_k6,  'o-', label='Con 6 bits')
    plt.plot(textos, r_k16, 'o-', label='Con 16 bits')

    plt.xlabel("Mensaje")
    plt.ylabel("Ratio de compresión (mayor = mejor)")
    plt.title("Comparación de eficiencia según bits de longitud de segmento")
    plt.legend()
    plt.tight_layout()
    plt.savefig("grafica_ratio_kbits.png")
    plt.close()


import math
from collections import Counter

def entropia(texto):
    if not texto:
        return 0
    freqs = Counter(texto)
    total = len(texto)
    return -sum((count/total) * math.log2(count/total) for count in freqs.values())



if __name__ == "__main__":

    pruebas = [
        "PruebaAlgoritmoDeCompresion",          
        "Hola123456789012345678901234567890",  
        "31415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679",
        "Esternocleidomastoideo 3,14159265358979323846 sonbloqueslargos",
        "Es te tex to ti 1 ene mucho espaci os 4 re",
        "En un lugar de la Mancha, de cuyo nombre no quiero acordarme, no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor. Una olla de algo más vaca que carnero, salpicón las más noches, duelos y quebrantos los sábados, lentejas los viernes, algún palomino de añadidura los domingos, consumían las tres partes de su hacienda."
    ]

    # fuente aleatoria para máxima entropía
    import random, string
    mensaje = fuente_aleatoria(1000)
    pruebas.append(mensaje)

    probar_entradas(pruebas)


    mensaje = fuente_aleatoria(100)
    print("Mensaje original:", mensaje)
    codificado = comprimir_cadena_con_longitud(mensaje)
    print("Mensaje codificado en bits:", codificado)

    decodificado = descomprimir(codificado)
    print("Mensaje decodificado:", decodificado)
    print("Decodificación correcta:", mensaje == decodificado)

    print("\n ")

    mensaje = "La entropía (incertidumbre) de una distribución p en m símbolos es al menos logbm. El máximo valor ocurre sii todos los símbolos son equiprobables."
    print("Mensaje original:", mensaje)
    codificado = comprimir_cadena_con_longitud(mensaje)
    print("Mensaje codificado en bits:", codificado)

    decodificado = descomprimir(codificado)
    print("Mensaje decodificado:", decodificado)
    print("Decodificación correcta:", mensaje == decodificado)


    mensaje = fuente_aleatoria(10000)
    pruebas.append(mensaje)

    for prueba in pruebas:
        H = entropia(prueba)
        codificado = comprimir_cadena_con_longitud(prueba)
        ratio = ratio_compresion_bits(prueba, codificado)
        tipo = "Natural" if prueba.__len__() < 999 else "Máxima entropía"
        print(f"\nTipo de fuente: {tipo}")
        print(f"Mensaje de ejemplo: {prueba[:40]}...")
        print(f"Longitud: {len(prueba)} caracteres")
        print(f"Entropía H(X): {H:.3f} bits/símbolo")
        print(f"Ratio de compresión: {ratio:.3f}")

    graficar_resultados(probar_entradas(pruebas, imprimir_detalle=False))








