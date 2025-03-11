#include <stdio.h>
#include <stdlib.h>

/* 4. leer 10 enteros de una sola vez en una zona de memoria reservada de forma dinámica
*/

int main(){
	FILE* fd;
	int * miArray; 

	fd = fopen("ficheroBinario.dat","rb");

	if (fd == NULL){
		perror("Error creando ficheroBinario.dat");
	}else{
		miArray = (int *)malloc(sizeof(int)*10);
		fread(miArray,sizeof(int),10,fd);
		for (int i=0; i<10; i++){
			printf("%d ", miArray[i]);
		}
		printf("\n");
		fclose(fd);
	}
	return 0;
}
