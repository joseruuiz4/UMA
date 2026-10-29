#include <stdlib.h>
#include <stdio.h>

void ej2(int *ptr1, int *ptr2){
    int temp;

    temp = *ptr1;
    *ptr1 = *ptr2;
    *ptr2 = temp;
}

int main()
{
    int v1 = 7;
    int v2 = 10;

    printf("El valor de v1 es %d, y el valor de v2 es %d\n", v1, v2);
    ej2(&v1, &v2);
    printf("El valor de v1 es %d, y el valor de v2 es %d\n", v1, v2);

    return 0;
}
