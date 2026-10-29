#include <stdio.h>
#include <stdlib.h>
#include "arbolbb.h"

void Crear(T_Arbol* arbol){
    *arbol = NULL;
}

void Destruir(T_Arbol* arbol){
    if(*arbol !=NULL){
        Destruir(&((*arbol) -> der));
        Destruir(&((*arbol) -> izq));
        free(*arbol);
        *arbol = NULL;
    }
}

void Insertar(T_Arbol* arbol, unsigned num){
    if(*arbol == NULL){
        *arbol = (T_Arbol) malloc(sizeof(struct T_Nodo));
        (*arbol) -> der = NULL;
        (*arbol) -> izq = NULL;
        (*arbol) -> dato = num;
    }else{
        if(num!= (*arbol) -> dato){
            if(num > (*arbol) -> dato){
                Insertar(&(*arbol) -> der, num);
            }else if(num < (*arbol) -> dato){
                Insertar(&(*arbol) -> izq, num);
            }
        }
    }
}


void Mostrar(T_Arbol arbol){
    if(arbol != NULL){
        Mostrar(arbol -> izq);
        printf("%d ", arbol -> dato);
        Mostrar(arbol -> der);
    }
}

void Salvar(T_Arbol arbol, FILE* fichero){
    if(arbol != NULL){
        unsigned numact = arbol -> dato;
        Salvar(arbol -> izq, fichero);
        fwrite(&numact, sizeof(int), 1, fichero);
        Salvar(arbol -> der, fichero);
    }
}