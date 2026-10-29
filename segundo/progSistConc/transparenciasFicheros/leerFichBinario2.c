#include <stdio.h>
#include <stdlib.h>

/* 5. interpretar de forma distinta el formato del fichero: el primer valor es el número de valores en el resto del fichero
*/

int main(){
	FILE* fd;
	int nValores;
	int valor; 

	fd = fopen("ficheroBinario2.dat","rb");

	if (fd == NULL){
		perror("Error creando ficheroBinario2.dat");
	}else{
		fread(&nValores,sizeof(int),1,fd);

		//Como se exactamente el numero de valores, los puedo leer con un for
		for (int i = 0; i<nValores; i++){
			fread(&valor,sizeof(int),1,fd);
			printf("%d ", valor);
		}
		printf("\n");
		
		fclose(fd);
	}
	return 0;
}
