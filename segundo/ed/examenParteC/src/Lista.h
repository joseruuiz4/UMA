#ifndef LISTA_H
#define LISTA_H

// Definición de la estructura Nodo
typedef struct Nodo {
    int valor;
    struct Nodo *siguiente;
} Nodo;

// Definición de la estructura Lista
typedef struct {
    int longitud;
    Nodo *cabeza;
} Lista;

// Función para inicializar una lista vacía
// Parámetros:
// Develve: puntero a la cabeza de la lista
Lista *inicializar();

// Función para insertar un valor en una posición específica
// Parámetros:
// 	lista : puntero a la cabeza de la lista donde se inserta un nuevo nodo
//		posición : posición en la lista donde se inserta un nuevo nodo. La primera posición es la 0
// 	valor : valor que contendrá el nuevo nodo insertado
// Devuelve : 0 si no se ha podido insertar el nodo o valor distinto de 0 si el nodo se ha insertado correctamente
int insertar(Lista *lista, int posicion, int valor);

// Función para borrar el primer nodo con un valor específico y su siguiente
// Parámetros:
//		lista : puntero a la cabeza de la lista donde se va a borrar un nodo
//		valor : valor que tiene el nodo que hay que borrar
// Devuelve: 0 si no se borró ningún nodo y distinto de 0 si se borró al menos un nodo
int borrar_valor_y_siguiente(Lista *lista, int valor);

// Función que devuelve la suma de cada elemento de la lista multiplicado por su posición en la lista.
// Parámetros:
//		lista : puntero a la cabeza de la lista
// Devuelve: La suma de los valores de cada elementos de la lista multiplicado por su posición en la lista
int sumaPorPosicion( Lista *lista );


// Función para liberar la memoria de la lista
// Parámetros:
// 	lista : puntero al puntero a la cabeza de la lista que se quiere liberar. 
void liberarLista(Lista **lista);

#endif
