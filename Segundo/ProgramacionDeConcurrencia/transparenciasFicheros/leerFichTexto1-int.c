/*
 * leerFichTexto.c
 *
 *  Created on: 16 mar. 2021
 *      Author: Monica
 */

/* Dos formas de controlar el final del fichero
   Probar también cambiando para leer como char y no como int
*/
#include <stdio.h>

int main(){
	FILE *fd; //declarar el descriptor del fichero

	//Paso 1. Abrir el fichero
	fd = fopen("prueba.txt","rt"); //wt, wb, rt, rb, w, r
	if (fd == NULL){
		perror("Error al crear el fichero");
	}else{
		//leer del fichero TEXTO
		int valor; 

		//EOF == -1
		
		//feof(fd) --> devuelve 0/1 segun si se ha llegado o no al final del fichero
		while(!feof(fd)){
			//No es necesario incluir un espacio en blanco despues de %d porque se
			//estan leyendo datos de tipo int. El espacio es un separador
			fscanf(fd,"%d",&valor); //Devuelve el número de datos leídos, o -1 si no ha podido leer ningún valor.
			printf("%d ",valor);
		}
		printf("\n");

		//Alternativa a feof(fd) para controlar el final del fichero
		/*int nleidos;
		while((nleidos = fscanf(fd,"%d",&valor))>0){
			printf("%d ",valor);
		}
		printf("\n");*/
		
		//cierro el fichero
		fclose(fd);
	}

	return 0;
}
