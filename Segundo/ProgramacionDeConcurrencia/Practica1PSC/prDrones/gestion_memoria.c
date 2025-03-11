/*
a) dos punteros auxiliares: ant ptr
T_Manejador ant, ptr;

b) incializar estos punteros
ptr = (*manejador) --comienzo de la lista
ant = NULL

c) bucsar dentro de la lista la posicion que queremos insertar o eliminar
buclke while condicion haciendo;
...
ant = ptr;
ptr = ptr->sig

d) segun los valores que tengan ant y ptr sabes en que posicion de la lista estas

if(ant==NULL) // ptr apunta la principio lista
(...)
else  // ptr apunta a un elemento distinto al primero de la lsita
{
if(ptr==NULL)//ant apunta al ultimo de la lista
(...)

else // apunta a un elemento intermedio de la lista
{

}
*/

#include "gestion_memoria.h"
#include <stdio.h>
#include <stdlib.h>

#define MAX 999;

void crear(T_Manejador *manejador)
{
    (*manejador) = (T_Manejador)malloc(sizeof(struct T_Nodo));
    (*manejador)->inicio = 0;
    (*manejador)->fin = MAX;
    (*manejador)->sig = NULL;
};

void destruir(T_Manejador *manejador)
{
    T_Manejador ptr;

    while ((*manejador) != NULL)
    {
        ptr = *manejador;
        (*manejador) = (*manejador)->sig;
        free(ptr);
    }
}

void obtener(T_Manejador *manejador, unsigned tam, unsigned *dir, unsigned *ok)
{
    (*ok) = 0;
    (*dir) = 0;
    T_Manejador ptr = (*manejador);
    T_Manejador ant = NULL;
    
    while (ptr != NULL && *ok==0)
    {
        unsigned hueco = ptr -> fin - ptr -> inicio;
        if (hueco >= tam)
        {
            (*ok) = 1;
            (*dir) = ptr->inicio;
        }
        else
        {
            ant = ptr;
            ptr = ptr->sig;
        }
    }

    
}

void mostrar(T_Manejador manejador)
{
    printf("-------\n");
    while (manejador != NULL)
    {
        printf("Bloque libre desde la posicion %d hasta %d\n", manejador->inicio, manejador->fin);
        manejador = manejador->sig;
    }
    fflush(stdout);
}

void devolver(T_Manejador *manejador, unsigned tam, unsigned dir){
    T_Manejador ptr1 = (*manejador);
    T_Manejador ptr2 = NULL;
    while(ptr1 != NULL && dir > ptr1 -> inicio){
        ptr2 = ptr1;
        ptr1 = ptr1 -> sig;
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
