/*
 * syscallV2.c
 *
 *  Created on: 8/2/2020
 *      Author: usuario
 */

#include <stdio.h>
#include <unistd.h>
#include <string.h>

int main() {
  char cadena[1024];

  strcpy(cadena, "Hola, mundo") ; //cadena[11]=='\0'
  //printf("Hola, mundo") ;
  write(1, cadena, strlen(cadena)) ; //strlen(cadena) == 11
  write(1,"Adios, Mundo", strlen("Adios, Mundo")) ;

  return 0;
}

