/*
 * escribirFicheroV2.c
 *
 *  Created on: 16 mar. 2021
 *      Author: Monica
 */

#include <stdio.h>
#include <string.h>

int main(){
	FILE* fd;
	//Cadena de caracteres que queremos escribir en el fichero
	char *cadena = "cadena de caracteres";

	//Abrimos el fichero para escribir en binario ("wb")
	fd = fopen("ficheroBinario3.dat","wb");

	if (fd == NULL){
		perror("Error creando ficheroBinario3.dat");
	}else{
		/* Mostramos por pantalla la longitud de la cadena
		 * 		sizeof(cadena): no devuelve la longitud de la cadena, sino el tama�o de la variable "cadena" de tipo puntero en bytes
		 * 		strlen(cadena): devuelve el numero de caracteres en la cadena, sin contar '\0'
		 */
		printf("Tamanio cadena = %d %d\n",(int)sizeof(cadena),(int)strlen(cadena));

		fwrite(cadena,sizeof(char),strlen(cadena)+1,fd); //escribimos la cadena mas '\0'

		fclose(fd);
	}

	return 0;
}
