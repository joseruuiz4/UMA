#include <stdio.h>
#include <stdlib.h>

void ej1(int *ptr){
    printf("Valor ej1 = %d\n", *ptr);
}


int main()
{

    int v = 8;
    ej1(&v);
    
    return 0;
}
