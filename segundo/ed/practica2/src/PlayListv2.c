#include "PlayListv2.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void PlayList_create(struct PlayList* myPlayList) {
  myPlayList->currentsong = NULL;
  myPlayList->head = NULL;
}

struct Node* createNode(const char* value) {
  struct Node* node = malloc(sizeof(struct Node));

  if (node == NULL || strlen(value) > MAX_SONG_LENGTH) {
    return NULL;
  }

  strcpy(node->data, value);

  return node;
}

int PlayList_insertAtFront(struct PlayList* myPlayList, const char* value) {
  if (myPlayList == NULL) return -1;
  struct Node* node = createNode(value);
  if (node == NULL) return -1;
  if (myPlayList->head == NULL) {
    myPlayList->head = node;
    node->prev = NULL;
    node->next = NULL;
    myPlayList->currentsong = myPlayList->head;
  } else {
    struct Node* aux = myPlayList->head;
    aux->prev = node;
    myPlayList->head = node;
    node->prev = NULL;
    node->next = aux;
  }
  return 0;
}

int PlayList_insertInOrder(struct PlayList* myPlayList, const char* valor) {
  if (myPlayList == NULL) return -1;
  struct Node* node = createNode(valor);
  if (node == NULL) return -1;
  if (myPlayList != NULL) {
    if (myPlayList->head == NULL) {
      myPlayList->head = node;
      node->prev = NULL;
      node->next = NULL;
    } else {
      struct Node* aux = myPlayList->head;

      while (strcmp(aux->data, node->data) < 0 && aux->next != NULL) {
        aux = aux->next;
      }

      // EL ULTIMO
      if (aux->next == NULL && strcmp(aux->data, node->data) < 0) {
        aux->next = node;
        node->prev = aux;
        node->next = NULL;
      }  // EL PRIMERO
      else if (aux->prev) {
        aux->prev = node;
        node->next = aux;
        node->prev = NULL;
        myPlayList->head = node;
      }  // EN MEDIO
      else {
        aux->prev->next = node;
        node->prev = aux->prev;

        node->next = aux;
        aux->prev = node;
      }
    }
  }
  return 0;
}

int PlayList_insertAtEnd(struct PlayList* myPlayList, const char* value) {
  if (myPlayList == NULL) return -1;
  struct Node* node = createNode(value);
  if (node == NULL) return -1;
  if (myPlayList != NULL) {
    if (myPlayList->head == NULL) {
      myPlayList->head = node;
      node->prev = NULL;
      node->next = NULL;
    } else {
      struct Node* aux = myPlayList->head;

      while (aux->next != NULL) {
        aux = aux->next;
      }

      aux->next = node;
      node->prev = aux;
      node->next = NULL;
    }
  }
  return 0;
}

int PlayList_insertAfter(struct PlayList* myPlayList, const char* cancion,
                         const char* value) {
  if (myPlayList == NULL) return -1;
  struct Node* node = createNode(value);
  if (node == NULL) return -1;
  if (myPlayList != NULL) {
    if (myPlayList->head == NULL) {
      return -1;
    } else {
      struct Node* aux = myPlayList->head;

      while (strcmp(aux->data, cancion) != 0 && aux->next != NULL) {
        aux = aux->next;
      }

      // EL ULTIMO
      if (aux->next == NULL && strcmp(aux->data, node->data) != 0) {
        return -1;

      }  // EN MEDIO Y PRIMERO
      else {
        aux->next->prev = node;
        node->next = aux->next;

        node->prev = aux;
        aux->next = node;
      }
    }
  }
  return 0;
}

int PlayList_insertBefore(struct PlayList* myPlayList, const char* cancion,
                          const char* value) {
  if (myPlayList == NULL) return -1;
  struct Node* node = createNode(value);
  if (node == NULL) return -1;
  if (myPlayList != NULL) {
    if (myPlayList->head == NULL) {
      return -1;
    } else {
      struct Node* aux = myPlayList->head;

      while (strcmp(aux->data, cancion) != 0 && aux->next != NULL) {
        aux = aux->next;
      }

      // EL ULTIMO
      if (aux->next == NULL && strcmp(aux->data, cancion) != 0) {
        return -1;
      }  // EL PRIMERO
      else if (aux->prev == NULL) {
        aux->prev = node;
        node->next = aux;
        node->prev = NULL;
        myPlayList->head = node;
      }  // EN MEDIO
      else {
        aux->prev->next = node;
        node->prev = aux->prev;

        node->next = aux;
        aux->prev = node;
      }
    }
  }
  return 0;
}

void PlayList_deleteFromFront(struct PlayList* myPlayList) {
  if (myPlayList == NULL) return;
  struct Node* aux = myPlayList->head;
  if (aux == NULL)
    return;
  else {
    myPlayList->head = aux->next;
    aux->next->prev = NULL;
  }
  free(aux);
}

int PlayList_deleteSong(struct PlayList* myPlayList, const char* value) {
  if (myPlayList == NULL && myPlayList->head == NULL) return 0;
  struct Node* aux = myPlayList->head;

  while (aux->next != NULL && strcmp(aux->data, value) != 0) {
    aux = aux->next;
  }

  // ULTIMO NODO Y NO ESTA EN LA LISTA
  if (aux->next == NULL && strcmp(aux->data, value) != 0) return 0;
  // EL PRIMERO DE LA LISTA ES EL QUE SE ELIMINA
  else if (aux == myPlayList->head) {
    myPlayList->head = aux->next;
    myPlayList->head->prev = NULL;
    free(aux);
    return 1;
  }  // ULTIMO NODO Y ES LA QUE HAY QUE BORRAR
  else if (aux->next == NULL && strcmp(aux->data, value) == 0) {
    aux->prev->next = NULL;
    free(aux);
    return 1;
  }  // SI ESTA ENMEDIO
  else {
    aux->prev->next = aux->next;
    aux->next->next = aux->prev;
    free(aux);
    return 1;
  }
}

void PlayList_print(struct PlayList myPlayList) {
  if (myPlayList.head == NULL) {
    printf("La lista esta vacia");
    return;
  }

  while (myPlayList.head != NULL) {
    printf("[ %s ]", myPlayList.head->data);
    myPlayList.head = myPlayList.head->next;
  }
  printf("\n");
}

void PlayList_order(struct PlayList* myPlayList) {
  if (myPlayList == NULL) return;
  if (myPlayList->head == NULL) return;
  if (myPlayList->head->next == NULL) return;
  struct Node* aux = myPlayList->head;
  int intercambios = 1;
  while (intercambios == 1) {
    aux = myPlayList->head;
    intercambios = 0;
    while (aux->next != NULL) {
      if (strcmp(aux->data, aux->next->data) > 0) {
        char datos[MAX_SONG_LENGTH];
        strncpy(datos, aux->data, MAX_SONG_LENGTH);
        strncpy(aux->data, aux->next->data, MAX_SONG_LENGTH);
        strncpy(aux->next->data, datos, MAX_SONG_LENGTH);
        intercambios = 1;
      }
      aux = aux->next;
    }
  }
}

void PlayList_deleteAll(struct PlayList* myPlayList) {
  if (myPlayList == NULL) return;
  struct Node* aux = myPlayList->head;
  while (myPlayList->head != NULL) {
    myPlayList->head = myPlayList->head->next;
    free(aux);
    aux = myPlayList->head;
  }
}

char* PlayList_currentSong(struct PlayList myPlayList) {
  return (myPlayList.head == NULL || myPlayList.currentsong == NULL)
             ? NULL
             : myPlayList.currentsong->data;
}

void PlayList_forwardSong(struct PlayList* myPlayList) {
  if (myPlayList == NULL || myPlayList->head == NULL ||
      myPlayList->currentsong == NULL || myPlayList->currentsong->next == NULL)
    return;
  myPlayList->currentsong = myPlayList->currentsong->next;
}

void PlayList_backwardSong(struct PlayList* myPlayList) {
  if (myPlayList == NULL || myPlayList->head == NULL ||
      myPlayList->currentsong == NULL || myPlayList->currentsong->prev == NULL)
    return;
  myPlayList->currentsong = myPlayList->currentsong->prev;
}