#include "Lista.h"

#include <stdio.h>
#include <stdlib.h>

Lista* inicializar() {
  Lista* lista;
  lista = malloc(sizeof(Lista));
  lista->cabeza = NULL;
  lista->longitud = 0;

  return lista;
}

int insertar(Lista* lista, int posicion, int valor) {
  if (lista == NULL) return 0;

  struct Nodo* node;
  node = malloc(sizeof(Nodo));
  node->valor = valor;
  node->siguiente = NULL;

  if (posicion == 0) {
    node->siguiente = lista->cabeza;
    lista->cabeza = node;
    lista->longitud++;
    return 1;
  }

  struct Nodo* aux = lista->cabeza;
  struct Nodo* anterior = NULL;
  int indice = 0;
  while (aux != NULL && indice < posicion) {
    anterior = aux;
    aux = aux->siguiente;
    indice++;
  }

  anterior->siguiente = node;
  node->siguiente = aux;

  lista->longitud++;
  return 1;
}

int borrar_valor_y_siguiente(Lista* lista, int valor) {
  if (lista == NULL || lista->cabeza == NULL) return 0;
  struct Nodo* aux = lista->cabeza;
  struct Nodo* anterior = NULL;
  while (aux->siguiente != NULL && aux->valor != valor) {
    anterior = aux;
    aux = aux->siguiente;
  }

  // NO ESTA EN LA LISTA
  if (aux->siguiente == NULL && aux->valor != valor)
    return 0;
  else if (aux->valor == valor) {
    // EL PRIMERO DE UNA LISTA CON MAS DE UN ELEMENTO
    if (anterior == NULL && aux->siguiente != NULL) {
      lista->cabeza = aux->siguiente->siguiente;
      free(aux->siguiente);
      free(aux);
      lista->longitud -= 2;
      return 1;
    }  // EL PRIMERO DE UNA LISTA CON SOLO UN ELEMENTO
    else if (anterior == NULL && aux->siguiente == NULL) {
      lista->cabeza = NULL;
      free(aux);
      lista->longitud -= 1;
      return 1;
    }  // ESTA EN MITAD
    else if (aux->siguiente != NULL) {
      anterior->siguiente = aux->siguiente->siguiente;
      free(aux->siguiente);
      free(aux);
      lista->longitud -= 2;
      return 1;
    }  // ES EL ULTIMO DE LA LISTA
    else {
      anterior->siguiente = NULL;
      free(aux);
      lista->longitud -= 1;
      return 1;
    }
  } else
    return 0;
}

int sumaPorPosicion(Lista* lista) {
  if (lista == NULL) return 0;
  int suma = 0;
  int indice = 0;

  struct Nodo* aux = lista->cabeza;

  while (aux != NULL) {
    suma += aux->valor * indice;
    indice++;
    aux = aux->siguiente;
  }

  return suma;
}

void liberarLista(Lista** lista) {
  if (lista != NULL || (*lista) == NULL) return;
  struct Nodo* aux;
  while ((*lista)->cabeza != NULL) {
    aux = (*lista)->cabeza;
    (*lista)->cabeza = (*lista)->cabeza->siguiente;
    free(aux);
  }

  (*lista) = NULL;
}
