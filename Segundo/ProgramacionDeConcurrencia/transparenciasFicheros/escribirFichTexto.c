/*
 * escribirFicheroV2.c
 *
 *  Created on: 16 mar. 2021
 *      Author: Monica
 */

#include <stdio.h>

int main(){
	FILE *fd; //declarar el descriptor del fichero

	//Paso 1. Abrir el fichero de texto para escribir ("wt" o "w")
	fd = fopen("prueba.txt","wt"); //wt, wb, rt, rb, w, r
	if (fd == NULL){
		perror("Error al crear el fichero");
	}else{
		//escribir 10 enteros en el fichero TEXTO separados por espacio en blanco
		for (int i=0; i<10; i++){
			fprintf(fd,"%d ",i);
		}
		fprintf(fd,"\n");

		//cierro el fichero
		fclose(fd);
	}

	return 0;
}
