#include <stdio.h>
#include <stdlib.h>
#include "Lista.h"

void imprimir( Lista *l );

int main() {
    Lista *lista = inicializar();
    if (!insertar(lista, 0, 10)) printf("Error al insertar 10\n");
    if (!insertar(lista, 1, 20)) printf("Error al insertar 20\n");
    if (!insertar(lista, 2, 30)) printf("Error al insertar 30\n");
    if (!insertar(lista, 1, 15)) printf("Error al insertar 15\n");
    if (!insertar(lista, 4, 40)) printf("Error al insertar 40\n");
    if (!insertar(lista, 5, 50)) printf("Error al insertar 50\n");
    if (!insertar(lista, 3, 25)) printf("Error al insertar 25\n");
    if (!insertar(lista, 6, 60)) printf("Error al insertar 60\n");
    imprimir(lista);
    printf("\n" );
    
    printf("Eliminando 20 y su siguiente...\n");
    if (borrar_valor_y_siguiente(lista, 20)){
    	printf( "Primera aparición del valor 20 borrada correctamente\n" );
    }else{
    	printf( "Error al borrar el valor 20\n" );
    }
    imprimir(lista);
    printf("\n");
    
    if (!borrar_valor_y_siguiente(lista, 70)){
    	printf( "CORRECTO! El valor 70 no se ha podido borrar porque no estaba en la lista\n" );
    }else{
    	printf( "Error al borrar el valor 70 que no estaba\n" );
    }
    imprimir(lista);
    printf("\n");

    
    if (sumaPorPosicion(lista) == 685){
    	printf( "La suma por posición ha dado correctamente 685\n" );
    }else{
    	printf( "La suma por posición debería haber dado 685 y ha dado %d\n", sumaPorPosicion( lista ) );
    }
   
    
    liberarLista(&lista);
    imprimir(lista);

    return 0;
}

















void imprimir( Lista *l ){
    int i;
    
    if (l == NULL){
        printf( "Lista no inicializada\n" );
        return;
    }
    Nodo *aux= l->cabeza;
    if (l->longitud == 0){
        printf( "Lista vacía\n" );
        return;
    }
    printf( "Lista: " );
    for( i = 0; i < l->longitud; i++ ){
        printf( "%d ;", aux->valor );
        aux= aux->siguiente;
    }
    printf( "\n" );
}
