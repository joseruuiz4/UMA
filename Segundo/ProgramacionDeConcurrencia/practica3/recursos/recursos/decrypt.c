#include <stdio.h>
#include <stdlib.h>

/* Parte 1: algoritmo de descifrado
 * 	v: puntero a un bloque de 64 bits.
 * 	k: puntero a la clave para descifrar.
 * 	Sabiendo que "unsigned int" equivale a 4 bytes (32 bits)
 * 	Podemos usar la notación de array con v y k
 * 	v[0] v[1] --- k[0] ... k[3]
 */
void decrypt(unsigned int *v, unsigned int *k)
{
	//Definir variables e inicializar los valores de delta y sum

	//Repetir 32  veces (usar un bucle) la siguiente secuencia de operaciones de bajo nivel

			//Restar a v[1] el resultado de la operacion :
				// (v[0] desplazado a la izquierda 4 bits +k[2]) XOR (v[0] + sum)  XOR (v[0] desplazado a la derecha 5 bits)+k[3]

			//Restar a v[0] el resultado de la operacion:
				// (v[1] desplazado a la izquierda 4 bits + k[0]) XOR (v[1]+ sum)  XOR (v[1] desplazado a la derecha 5 bits)+k[1]

			// Restar a sum el valor de delta
}

/* Parte 2: Metodo main. Tenemos diferentes opciones para obtener el nombre del fichero cifrado y el descifrado
 * 1. Usar los argumentos de entrada (argv)
 * 2. Pedir que el usuario introduzca los nombres por teclado
 * 3. Definir arrays de caracteres con los nombres
 */
int main(int argc, char *argv[] )
{
	/*Declaración de las variables necesarias, por ejemplo:
	* variables para los descriptores de los ficheros ( FILE * fent, *fsal)
	* la constante k inicializada con los valores de la clave
	* buffer para almacenar los datos (puntero a unsigned int, más adelante se reserva memoria dinámica */

	/*Abrir fichero encriptado fent en modo lectura binario
	 * nota: comprobar que se ha abierto correctamente*/
    

	/*Abrir/crear fichero fsal en modo escritura binario
	 * nota: comprobar que se ha abierto correctamente*/
    

   /*Al comienzo del fichero cifrado esta almacenado el tamaño en bytes que tendrá el fichero descifrado.
    * Leer este valor (imgSize)*/

	/*Reservar memoria dinámica para el buffer que almacenara el contenido del fichero cifrado
	 * nota1: si el tamaño del fichero descifrado (imgSize) no es múltiplo de 8 bytes,
	 * el fichero cifrado tiene además un bloque de 8 bytes incompleto, por lo que puede que no coincida con imgSize
	 * nota2: al reservar memoria dinámica comprobar que se realizó de forma correcta */

	/*Leer la información del fichero cifrado, almacenado el contenido en el buffer*/

	/*Para cada bloque de 64 bits (8 bytes o dos unsigned int) del buffer, ejecutar el algoritmo de desencriptado*/

    /*Guardar el contenido del buffer en el fichero fsal
     * nota: en fsal solo se almacenan tantos bytes como diga imgSize */

	/*Cerrar los ficheros*/
}


