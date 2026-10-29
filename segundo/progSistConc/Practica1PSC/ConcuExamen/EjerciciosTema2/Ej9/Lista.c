/*
 ============================================================================
 Name        : Lista.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <Lista.h>
#include <stdio.h>
#include <stdlib.h>

void Crear(T_Lista * lista){
	*lista = NULL;
}

void Mostrar(T_Lista lista){
	if(lista == NULL){
		printf("Lista vacía\n");
	}else
	{
		while (lista != NULL)
		{
			printf("%d", lista -> num);
			lista = lista -> sig;
		}
		printf("\n");
		
	}
	fflush(stdout);
	
}

void insertarPrincipio(T_Lista * lista, int elem){
	T_Lista nuevo;
	nuevo = (T_Lista) malloc(sizeof(struct T_Nodo));
	nuevo -> num = elem;
	nuevo -> sig = *lista;
	(*lista) = nuevo;

}

void insertarFinal(T_Lista * lista, int elem){
	T_Lista nuevo, ptr;

	nuevo = (T_Lista) malloc(sizeof(struct T_Nodo));
	nuevo -> num = elem;
	nuevo -> sig = NULL;

	if((*lista) == NULL){
		*lista = nuevo;
	}else{
		ptr = *lista;
		while(ptr -> sig != NULL){
			ptr = ptr -> sig;
		}
		ptr -> sig = nuevo;
	}
}


void insertarOrdenado(T_Lista * lista, int elem){
	T_Lista nuevo, ptr, ant;

	ant = NULL;
	ptr = *lista;

	

	while(ptr -> sig != NULL && ptr -> num < elem){
		ant = ptr;
		ptr = ptr-> sig;
	}
	if(ant == NULL){
		insertarPrincipio(lista, elem);
	}else{
		nuevo = (T_Lista) malloc(sizeof(struct T_Nodo));
		nuevo -> num = elem;
		nuevo -> sig = ptr;
		ant -> sig = nuevo;
	}


}

void eliminarElemento(T_Lista * lista, int elem){
	
	T_Lista nuevo, ptr, ant;

	ant = NULL;
	ptr = *lista;

	if(ptr == NULL){
		printf("Lista vacia\n");
	}

	while(ptr -> sig != NULL && ptr -> num != elem){
		ant = ptr;
		ptr = ptr-> sig;
	}
	if(ant == NULL){
		*lista = ptr -> sig;
		free(ptr);
	}else if (ptr == NULL){
		printf("Nodo no encontrado\n");
	}else{
		ant -> sig = ptr -> sig;
		free(ptr);
	}


}


