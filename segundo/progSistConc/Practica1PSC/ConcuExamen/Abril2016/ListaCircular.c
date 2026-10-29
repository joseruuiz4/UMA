#include "ListaCircular.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void crear(TListaCircular *lc){
    *lc = NULL;
}


void insertar(TListaCircular *lc,char *nombre){
    TListaCircular nuevo = (TListaCircular) malloc(sizeof(struct TNodo));
    strcpy(nuevo->nombre, nombre);
    nuevo->sig = NULL;

    if(*lc == NULL){
        *lc = nuevo;
        (*lc)->sig = nuevo;
    }else{
        nuevo->sig = (*lc)->sig;
        (*lc)->sig = nuevo;
        *lc = nuevo;
    }
}



void recorrer(TListaCircular lc){
    TListaCircular ptr;

    if(lc != NULL){
        ptr = lc->sig;
        while(ptr->sig != lc->sig){
            printf("%s ", ptr->nombre);
            ptr = ptr->sig;
        }
    }
}

int longitud(TListaCircular lc){
    TListaCircular ptr;
    int cont = 0;
    if(lc != NULL){
        ptr = lc->sig;
        while(ptr->sig != lc->sig){
            cont++;
            ptr = ptr->sig;
        }
    }

    return cont;
}

void mover(TListaCircular *lc,int n){

    int cont = 0;
    if(*lc != NULL){
        
        while(cont < n){
            cont++;
            *lc = (*lc)->sig;
        }
    }

}

void extraer(TListaCircular *lc,char *nombre){
    if(*lc != NULL){
        TListaCircular ptr = (*lc)->sig;
        strcpy(nombre, ptr->nombre);
        (*lc)->sig = ptr ->sig;
        free(ptr);
        

    }
   

}