/*
 ============================================================================
 Name        : EjerciciosClase.c

 Author      :
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>

/* Analizar qué hace exactamente este código*/
int main() {
	double sum, v;
	
	printf("Introduce un valor real: ");
	scanf("%lf",&v);
	printf("El valor es: %f",v);

	sum = 0;

	while (scanf("%lf", &v) == 1){
		printf("\t %.2f\n",sum+=v);
	}

	return 0;
}
