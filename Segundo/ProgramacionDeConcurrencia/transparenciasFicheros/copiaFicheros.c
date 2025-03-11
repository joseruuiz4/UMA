/*
 * copiaFicheros.c
 *
 *  Created on: 10/2/2020
 *      Author: usuario
 */

#include <stdio.h>

#define SIZE 1024

int main() {
	int ok = 1, leidos, escritos;
	char buffer[SIZE];

	FILE *fent, *fsal;

	fent = fopen("entrada.dat", "rb"); /* Control de errores omitido */
	fsal = fopen("salida.dat", "wb");

	while (ok && (leidos = fread(buffer, 1, sizeof(buffer), fent)) > 0) {
		escritos = fwrite(buffer, 1, leidos, fsal);
		ok = escritos == leidos;
	}

	fclose(fent);
	fclose(fsal);

	return 0;
}
