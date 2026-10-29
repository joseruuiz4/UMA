#include <stdio.h>
#include <stdlib.h>

/* 5. interpretar de forma distinta el formato del fichero: el primer valor es el número de valores en el resto del fichero
*/

int main(){
	FILE* fd;
	int nValores;
	int * miArray; 

	fd = fopen("ficheroBinario2.dat","rb");

	if (fd == NULL){
		perror("Error creando ficheroBinario2.dat");
	}else{
		fread(&nValores,sizeof(int),1,fd);
		
		//reservo memoria de forma dinámica para guardar los nValores
		miArray = (int *)malloc(sizeof(int)*nValores);

		//leo los nValores con una sola operacion de lectura binaria
		fread(miArray,sizeof(int),nValores,fd);

		for (int i=0; i<nValores; i++){
			printf("%d ", miArray[i]);
		}
		
		fclose(fd);
	}
	return 0;
}
