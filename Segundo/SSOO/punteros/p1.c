#include <stdio.h>

int main()
{
  int var_entera;
  int *p;  /* Creamos el puntero p */
  var_entera = 10;

  p = &var_entera;

  printf("\n"); /* Dejamos un espacio */
  
  printf("Dirección de memoria de la variable var_entera = %p \n", &var_entera);
  printf("Valor de la variable var_entera = %d \n\n", var_entera);
  printf("Dirección de memoria que alberga al puntero p = %p \n", &p);
  printf("Dirección de memoria que es apuntada por el puntero p = %p \n", p);
  printf("Contenido de la dirección apuntada por el puntero p = %d \n", *p);
  printf("\n");
}


