#ifndef __MILISTA_H__
#define __MILISTA_H__

#include <stdio.h>  //Entrada-salida
#include <stdlib.h> //Memoria dinamica

//Definición tipos para lista enlazada
typedef struct NodoLista * Lista;
struct NodoLista{
    int dato;
    Lista sig;
};

//Operaciones de creacion de la lista
void crear(Lista *l);
Lista crearV2();

//Operaciones para insertar elementos en la lista

//Operaciones para borrar elementos de la lista

//Operaciones de busqueda sobre la lista

//Operaciones de liberacion de la memoria dinamica de la lista

#endif