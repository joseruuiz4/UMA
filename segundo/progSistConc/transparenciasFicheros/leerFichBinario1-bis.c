#include <stdio.h>
#include <stdlib.h>

/* 3. leer 10 enteros de una sola vez en un array estático
*/

int main(){
	FILE* fd;
	int miArray[10]; 

	fd = fopen("ficheroBinario.dat","rb");

	if (fd == NULL){
		perror("Error creando ficheroBinario.dat");
	}else{
		fread(miArray,sizeof(int),10,fd);
		for (int i=0; i<10; i++){
			printf("%d ", miArray[i]);
		}
		printf("\n");
		fclose(fd);
	}
	return 0;
}
