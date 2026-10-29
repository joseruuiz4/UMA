#include "Bloques.h"
#include <stdio.h>
#include <stdlib.h>


void crearNodo(ListaBloques *nodo, unsigned int dir){
	*nodo = (ListaBloques) malloc(sizeof(struct Nodo));
	if (*nodo != NULL){
		(*nodo)->dirInicio = dir;
		(*nodo)->sig = NULL;
	}
}


void crear(ListaBloques *lb, unsigned int tamMemoria){
    int veces, dir;
    if(tamMemoria != 0){
        veces = tamMemoria/512;
        dir = tamMemoria - 512;
        ListaBloques nuevo;
        *lb = NULL;

        for(int i = 0; i < veces; i++){
            crearNodo(&nuevo, dir);
            insertarBloque(lb, nuevo);
            dir -= 512;
        }
    }else{
        *lb = NULL;
    }
}





void obtenerBloque(ListaBloques *lb, ListaBloques *bloque){
    
    if(*lb != NULL){
        *bloque = *lb;
        *lb = (*lb)->sig;
        (*bloque)->sig = NULL;
    }else{
        *bloque = NULL;
    }
}



void insertarBloque(ListaBloques *lb, ListaBloques bloque){
    ListaBloques ptr, ant;
    ant = NULL;
    ptr = *lb;

    while(ptr != NULL && ptr->dirInicio < bloque->dirInicio){
        ant = ptr;
        ptr = ptr->sig;
    }

    if(ant == NULL){
        bloque->sig = ptr;
        *lb = bloque;
    }else{
        bloque->sig = ptr;
        ant->sig = bloque;
    }


}


void imprimir(ListaBloques lb){
    printf("(");
    while(lb != NULL){
        printf("%d ", lb->dirInicio);
        lb = lb->sig;
    }
    printf(")\n");
}

void borrar(ListaBloques *lb){
    ListaBloques aux;
    
    while(*lb != NULL){
        aux = *lb;
        *lb = (*lb)->sig;
        free(aux);
    }
}