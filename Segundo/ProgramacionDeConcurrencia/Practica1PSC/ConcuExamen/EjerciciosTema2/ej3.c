#include <stdlib.h>
#include <stdio.h>
const int max = 6;

void leerEnteros(int *ptr1, int nelem){
    int i;

    printf("Introducta %d elementos:", nelem);
    fflush(stdout);

    for(i = 0; i < nelem; i++)
        scanf("%d", &ptr1[i]);
}

void mostrarEnteros(int *ptr, int nelem){
    int i;

	for (i=0;i<nelem;i++)
		printf("%d ",ptr[i]);
	printf("\n");
}

int main()
{
    int nelem;
    int array[max];
    int *memDin;

    leerEnteros(array, 6);
    mostrarEnteros(array, 6);

    //Ej4y6. Zona de memoria creada de forma din�mica
	printf("\nIntroduzca numero elementos en array: ");
	fflush(stdout);
	scanf("%d",&nelem);

	memDin = (int *)malloc(sizeof(int)*nelem);

	if (memDin==NULL)
		perror("Error en peticion de memoria");
	else {
		leerEnteros(memDin,nelem);
		mostrarEnteros(memDin,nelem);
	}

    return 0;
}
