/*
 * escribirFicheroV2.c
 *
 *  Created on: 16 mar. 2021
 *      Author: Monica
 */

#include <stdio.h>

int main(){
	FILE* fd;
	//Creamos un array con 23 huecos y guardamos una cadena cualquiera
	char cadena[23]="aaaaaaaaaaaaaaaaaaaaaaa";

	//Abrimos el fichero binario para leer del fichero ("rb")
	fd = fopen("ficheroBinario3.dat","rb");

	if (fd == NULL){
		perror("Error creando ficheroBinario3.dat");
	}else{
		printf("Cadena = %s\n",cadena);
		//Prueba 1. Leemos del fichero 20 caracteres --> No estamos leyendo el '\0'
		fread(cadena,sizeof(char),20,fd);

		//Prueba 2. Leemos del fichero 21 caracteres --> Estamos leyendo el '\0'
		//fread(cadena,sizeof(char),21,fd); //21 = 20 + fin de cadena

		//Prueba 3. Leemos caracter a caracter hasta final del fichero, incluye la lectura de '\0' sin hacer nada especial (es un caracter mas en el fichero)
		/*int i=0;
		while (!feof(fd)){
			fread(&cadena[i],sizeof(char),1,fd);
			i++;
		}*/
		printf("Cadena = %s\n",cadena);

		fclose(fd);
	}
	return 0;
}
