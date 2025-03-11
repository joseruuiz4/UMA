/*
 * Principal.c
 *
 *  Created on: 12/4/2016
 *      Author: mmar
 */

#include "ListaCircular.h"
#include <stdio.h>


// Lee el fichero y lo introduce en la lista
void cargarFichero (const char *nombreFich, TListaCircular *lc){
	// REALIZAR ESTE PROCEDIMIENTO
	FILE * f = fopen(nombreFich, "rt");
	if(f == NULL){
		printf("Error abriendo el fichero\n");
	}
	char nombre[30];
	while(!feof(f)){
		fscanf(f, "%s", nombre);
		insertar(lc, nombre);
	}

	fclose(f);

	



}


int main(){

	TListaCircular lc;
	crear(&lc);

	char nombre[30];

	int n;

	cargarFichero ("listaNombres.txt",&lc);
	recorrer(lc);
	printf("Introduce un número entre 0 y 60: ");
	fflush(stdout);
	scanf("%d",&n);
	while (longitud(lc)>1){
		mover(&lc,n);
		extraer(&lc,nombre);
		printf("%s ha salido del círculo \n",nombre);
	}

	extraer(&lc,nombre);
	printf("--------------------------------------\n");
	printf("%s ha sido seleccionado !!!!! \n",nombre);
	fflush(stdout);

	return 0;
}
