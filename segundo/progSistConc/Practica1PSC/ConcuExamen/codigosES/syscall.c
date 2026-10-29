/*
 * syscall.c
 */

#include <stdio.h>
#include <unistd.h>

//¿Qué ocurre si ejecutamos el programa tal cuál está?
//¿Qué ocurre si añadimos \n a la primera cadena que se muestra con printf?
//¿Qué ocurre si añadimos fflush(stdout); entre las dos sentencias de escritura

int main() {
  printf("Hola, mundo\n") ;        /* funcion C de E/S */
  fflush(stdout);
  write(1, "Adios, mundo ", 15) ; /* llamada al sistema */
  return 0;
}

