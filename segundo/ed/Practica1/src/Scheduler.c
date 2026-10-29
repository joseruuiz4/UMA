//
// Luis Llopis, Data Structures, UMA.
//

#include "Scheduler.h"

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

struct Node* Scheduler_new() {
  struct Node* ptrNode;
  ptrNode = NULL;

  return ptrNode;
}

size_t Scheduler_size(const struct Node* p_last) {
  if (p_last == NULL) return 0;

  struct Node* aux = p_last->p_next;

  int cont = 1;

  while (aux != p_last) {
    aux = aux->p_next;
    cont++;
  }

  return cont;
}

void Scheduler_clear(struct Node** p_p_last) {
  if (p_p_last != NULL && *p_p_last != NULL) {
    struct Node* aux = (*p_p_last)->p_next;
    struct Node* siguiente = aux;

    while (aux != *p_p_last) {
      siguiente = aux->p_next;
      free(aux);
      aux = siguiente;
    }
    free(*p_p_last);
    *p_p_last = NULL;
  }
}

struct Task* Scheduler_first(const struct Node* p_last) {
  if (p_last == NULL) return NULL;
  struct Node* primera = p_last->p_next;
  struct Task* copia = Task_copyOf(&primera->task);
  return copia;
}

void Scheduler_first_v2(const struct Node* p_last, struct Task* p_task) {
  if (p_last == NULL || p_task == NULL) return;
  *p_task = p_last->p_next->task;
}

void Scheduler_enqueue(struct Node** p_p_last, const struct Task* p_task) {
  // La tarea dada es NULL
  if (p_task == NULL) return;

  // Se mete al final de la cola
  if (p_p_last != NULL && *p_p_last != NULL) {
    struct Node* nuevo;
    nuevo = malloc(sizeof(struct Node));
    nuevo->task = *p_task;
    nuevo->p_next = (*p_p_last)->p_next;

    (*p_p_last)->p_next = nuevo;
    *p_p_last = nuevo;
  }

  // Planificador vacio
  if (p_p_last != NULL && *p_p_last == NULL) {
    struct Node* nuevo;
    nuevo = malloc(sizeof(struct Node));

    nuevo->task = *p_task;
    nuevo->p_next = nuevo;

    *p_p_last = nuevo;
  }
}

void Scheduler_dequeue(struct Node** p_p_last) {
  if (p_p_last == NULL || *p_p_last == NULL) return;
  struct Node* primero = (*p_p_last)->p_next;

  if (primero == *p_p_last) {
    free(primero);
    *p_p_last = NULL;
    primero = NULL;
  } else {
    (*p_p_last)->p_next = primero->p_next;
    free(primero);
    primero = NULL;
  }
}

void Scheduler_print(const struct Node* p_last) {
  if (p_last == NULL) return;

  struct Node* aux = p_last->p_next;
  struct Node* primero = p_last->p_next;
  do {
    printf("[ ");
    Task_print(&aux->task);
    printf(" ] ");

    aux = aux->p_next;
  } while (aux != primero);

  printf("\n");
}
