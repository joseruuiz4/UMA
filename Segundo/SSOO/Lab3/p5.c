#include <stdio.h>
#include <stdlib.h>


int main (int argc, char* argv[])
{
  int i;
  printf("\n");
  printf("Este programa se ha invocado con %d argumentos\n\n", argc);
  printf("El nombre del ejecutable (argumento 0) es %s\n\n", argv[0]);
  
  if (argc == 1)
  {
    printf("El programa se ha llamado sin argumentos \n\n");
    exit(1);
  }
  
  /* Si llegamos aquí, hay dos o más argumentos */
  
  for (i=1; i<argc; i++)
  {
    printf("Argumento número %d = %s \n", i, argv[i]);
  }
  printf("\n");
} 