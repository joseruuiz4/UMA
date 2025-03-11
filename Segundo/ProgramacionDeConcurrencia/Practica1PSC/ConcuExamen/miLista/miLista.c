#include "miLista.h"

void crear(Lista *l){
    *l = NULL;
}

Lista crearV2(){
    return NULL;
}

int estaVacia(Lista l){
    return l == NULL;
}

Lista crearNodo(int elem){
    Lista nuevo = (Lista) malloc(sizeof(struct NodoLista));
    if(nuevo != NULL){
        nuevo -> dato = elem;
        nuevo -> sig = NULL;
    }
    return nuevo;
}

void insertar(Lista *l, int elem){
    Lista nuevo = crearNodo(elem);
    if(nuevo != NULL){
        if(*l == NULL){
            *l = nuevo;
        }else{
            nuevo -> sig = *l;
            *l = nuevo;
        }
    }
}

void insertarOrdenado(Lista *l, int elem){
    Lista ant, ptr;

    ptr = *l;
    ant = NULL;

    while(ptr != NULL && elem > ptr -> dato){
        ant = ptr;
        ptr = ptr -> sig;
    }

    if(ant == NULL){
        insertar(l, elem);
    }else{
        Lista nuevo = crearNodo(elem);
        ant -> sig = nuevo;
        nuevo -> sig = ptr;
    }



}

void insertarOrdenadoSinReps(Lista *l, int elem){
    Lista ant, ptr;

    ptr = *l;
    ant = NULL;

    while(ptr != NULL && elem > ptr -> dato){
        ant = ptr;
        ptr = ptr -> sig;
    }

    if(!(ptr != NULL && ptr -> dato == elem)){
        if(ant == NULL){
            insertar(l, elem);
        }else{
            Lista nuevo = crearNodo(elem);
            ant -> sig = nuevo;
            nuevo -> sig = ptr;
        }
    }


}

void eliminar(Lista *l){
    Lista ptr = *l;
    if(*l !=NULL){
        *l = (*l) -> sig;
        ptr -> sig = NULL;
        free(ptr);
    }
}

Lista sacar(Lista *l){
    Lista ptr = *l;
    
    if (*l != NULL){
        *l = (*l)->sig;
        ptr->sig = NULL;
    }
    
    return ptr;
} 

void eliminarElem(Lista *l,int elem){ 
    if (*l != NULL){ //Si es vacía no hay que hacer nada
        Lista ptr, ant;

        ptr = *l;
        ant = NULL;

        //Buscamos el elemento a eliminar
        while (ptr != NULL && ptr->dato != elem){
            ant = ptr;
            ptr = ptr->sig;
        }

        if (ptr != NULL){ //Si lo ha encontrado ptr != NULL
            if (ant == NULL) eliminar(l); //Elimino del principio de la lista
            else{
                //eliminamos del medio o del final
                ant->sig = ptr->sig;
                free(ptr);
            }
        }
    }
}

Lista sacarElem(Lista *l, int elem){
    Lista dato = NULL;
    if (*l != NULL){ //Si es vacía no hay que hacer nada
        Lista ptr, ant;

        ptr = *l;
        ant = NULL;

        //Buscamos el elemento a sacar
        while (ptr != NULL && ptr->dato != elem){
            ant = ptr;
            ptr = ptr->sig;
        }

        if (ptr != NULL){ //Si lo ha encontrado ptr != NULL
            if (ant == NULL)
                dato = sacar(l); //Saco del principio de la lista
            else{
                //sacamos del medio o del final
                ant->sig = ptr->sig;
                dato = ptr;
            }
        }
    }    
    return dato;
}

void eliminarMultiplesElem(Lista *l,int elem){ 
    if (*l != NULL){ //Si es vacía no hay que hacer nada
        Lista ptr, ant;

        ptr = *l;
        ant = NULL;

        //Buscamos el elemento a eliminar y si lo encontramos lo eliminamos y seguimos buscando
        while (ptr != NULL){
            if (ptr->dato != elem){ //avanzamos al siguiente elemento
                ant = ptr;
                ptr = ptr->sig;
            }else{ //Hemos encontrado el elemento y lo borramos
                /* Este if es muy parecido que el que elimina una ocurrencia, pero ...
                    - lo hemos movido dentro del bucle while que hace la búsqueda
                    - una vez borrado el nodo, colocamos ant y ptr correctamente para seguir buscando
                */
                if (ant == NULL) {
                    eliminar(l); //Elimino del principio de la lista
                    //Coloco los punteros en la situación inicial. ant ya tiene valor NULL
                    ptr = *l; 
                }else{
                    //eliminamos del medio o del final
                    ant->sig = ptr->sig;
                    free(ptr);
                    //Coloco los punteros. ant ya tiene el valor correcto
                    ptr = ant->sig;
                }
            }
        }
    }
}