#include "cola.h"
#include <string.h>

void crearCola(ColaProcesos * cola){
    cola -> frente = NULL;
    cola -> final = NULL;
    
}

void anyadirProceso(ColaProcesos * cola, char * historial){
    Proceso nuevo;

    nuevo = (Proceso) malloc(sizeof(struct DatosProceso));
    strcpy(nuevo -> historial, historial);
    nuevo -> sig = NULL;

    if(colaVacia(*cola)){
        cola -> final = nuevo;
        cola -> frente = nuevo;
    }else{
        cola -> final -> sig = nuevo;
        cola -> final = nuevo;
    }
}


void extraerProceso(ColaProcesos * cola, char * historial){
    Proceso ptr;

    if(!colaVacia(*cola)){
        ptr = cola -> frente;
        strcpy(historial, ptr -> historial);
        cola -> frente = ptr -> sig;
        free(ptr);
        if(cola ->frente == NULL){
            cola -> final = NULL;
        }
    }else{
        return NULL;
    }
}


int colaVacia(ColaProcesos cola){
    return (cola.final == NULL);
}


void vaciarCola(ColaProcesos * cola){
    Proceso ptr;
    while(ptr != NULL){
        ptr = cola -> frente;
        cola -> frente = ptr -> sig;
        free(ptr);
    }
    cola -> final = NULL;
}

void moverProcesos(ColaProcesos *dest, ColaProcesos *orig, int num){
	char *h;
	char historial[11];
	int i;

	if (orig->frente != NULL){
		i = 1;
		do{
			*h = extraerProceso(orig,historial);
			insertarProceso(dest,historial);
			i++;
		}while (i<num && h!=NULL);
	}
}