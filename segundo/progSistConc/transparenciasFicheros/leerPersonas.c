#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
  FILE * fd = fopen("personas.txt","rt");
  if (fd == NULL){
    perror("Error abriendo fichero");
    exit(-1);
  }

  int codigo = 0, edad = 0;
  char nombre[20];

  /* Buscar en la web "cplusplus" la especificación de fscanf y fgets 
     para ver como funcionan para leer una cadena de caracteres
  */
  while (!feof(fd)){
	  fscanf(fd,"%d %d",&codigo,&edad);
    //fscanf(fd,"%s",nombre); //pararía al encontrar el primer espacio en blanco
	  fgets(nombre,sizeof(nombre),fd); //termina al leer el numero de caracteres indicado o con el retorno de carro. 
    
    //El retorno de carro lo guarda también en nombre y lo eliminamos
    nombre[strlen(nombre)-1] = '\0'; //strlen(nombre) devuelve la longitud (hasta '\0' sin incluirlo)
	  
    printf("%d %d %s\n",codigo,edad,nombre);
  }

  fclose(fd);
  return 0;
}
