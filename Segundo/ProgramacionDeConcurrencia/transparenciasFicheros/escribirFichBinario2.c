/*
 * escribirFicheroV2.c
 *
 *  Created on: 16 mar. 2021
 *      Author: Monica
 */

#include <stdio.h>

int main(){
	//Declaramos el descriptor del fichero: variable de tipo puntero a FILE
	FILE* fd;

	//Abrimos el fichero para escribir en binario ("wb")
	fd = fopen("ficheroBinario2.dat","wb");

	//Si no se abre correctamente fd contendr� NULL
	if (fd == NULL){
		//perror no termina la ejecuci�n del programa. Por eso hace falta el else
		perror("Error creando ficheroBinario2.dat");
		//exit(-1); //con esta l�nea el else no ser�a necesario, porque se finaliza la ejecuci�n del programa despu�s del error
	}else{
		//Escribimos primero el numero de valores
		int nValores = 12;
		fwrite(&nValores,sizeof(int),1,fd);

		//Escribimos los valores de tipo int
		for (int i = 0; i<nValores; i++){
			fwrite(&i,sizeof(int),1,fd);
		}

		fclose(fd);
	}

	return 0;
}
