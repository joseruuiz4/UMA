/*
 * leerFicheroBinario.c
 *
 *  Created on: 16 mar. 2021
 *      Author: Monica
 */

/* Leer 10 valores de tipo entero del fichero binario */
#include <stdio.h>

int main(){
	FILE* fd;

	fd = fopen("ficheroBinario.dat","rb");

	if (fd == NULL){
		perror("Error creando ficheroBinario.dat");
	}else{
		int valor;

		/*no sabemos cuantos valores hay*/
		int nleidos;
		while ((nleidos = fread(&valor,sizeof(int),1,fd)) > 0){
			printf("%d ", valor);
		}

		/*alternativa: sin declarar nleidos*/
		/*
		while (fread(&valor,sizeof(int),1,fd) > 0){
			printf("%d ", valor);
		}*/

		fclose(fd);

	}
	return 0;
}
