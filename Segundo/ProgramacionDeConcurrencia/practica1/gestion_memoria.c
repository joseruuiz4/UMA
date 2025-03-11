#include <stdio.h>
#include <stdlib.h>
#include <gestion_memoria.h>
#define MAX 999

void crear(T_Manejador * manejador){
    (*manejador) = (T_Manejador) malloc(sizeof(struct T_Nodo));
    (*manejador) -> inicio = 0;
    (*manejador) -> fin = MAX;
    (*manejador) -> sig = NULL;
}

void destruir(T_Manejador * manejador){
    T_Manejador  borrar;
    while((*manejador) != NULL){
        borrar = (*manejador);
        (*manejador) = (*manejador) -> sig;
        free(borrar);
    }
}

void obtener(T_Manejador *manejador, unsigned tam, unsigned* dir, unsigned* ok){
    (*dir) = 0;
    (*ok) = 0;
    T_Manejador ptr1 = (*manejador);
    T_Manejador ptr2 = (*manejador);
    unsigned memoriaBloque;
    while((*manejador) != NULL && !(*ok)){
        memoriaBloque = (ptr1 -> fin) - (ptr1 -> inicio) + 1;
        if(memoriaBloque >= tam){
            (*ok) = 1;
            (*dir) = ptr1 -> inicio;
        }else{
            ptr2 = ptr1;
            ptr1 = ptr1 -> sig;
        }
        
    }
}

void mostrar(T_Manejador manejador){
    printf("--------\n");
    while(manejador != NULL){
        printf("Bloque libre desde la posicion %d hasta la posicion %d", manejador -> inicio, manejador -> fin);
        manejador = manejador -> sig;
    }
    fflush(stdout);
}

void devolver(T_Manejador *manejador, unsigned tam, unsigned dir){
    T_Manejador ptr1 = (*manejador);
    T_Manejador ptr2 = NULL;
    
    while(ptr1 != NULL && dir > ptr1 -> inicio){
        ptr2 = ptr1;
        ptr1 = ptr1 ->sig;
    }
    T_Manejador nuevo;
    nuevo = (T_Manejador) malloc(tam);
    nuevo -> inicio = dir;
    nuevo -> fin = dir + tam -1;
    nuevo -> sig = ptr1;

    if(ptr2 == NULL){
        (*manejador) = nuevo;
    }else{
        ptr2 -> sig = nuevo;
    }

}