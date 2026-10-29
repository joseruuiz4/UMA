#include "Procesos.h"
#include <string.h>
#include <stdlib.h>

void crear(LProcesos *lp){
    *lp = NULL;
}

LProcesos crearNodo(unsigned prioridad, unsigned id, unsigned tiempo){
	LProcesos nuevo = malloc(sizeof(struct Proceso));
	if (nuevo != NULL){
		nuevo->id = id;
		nuevo->prioridad = prioridad;
		nuevo->tiempo = tiempo;
		nuevo->sig = NULL;
	}
	return nuevo;
}


void insertar(LProcesos *lp,unsigned prioridad, unsigned id, unsigned tiempo){
    LProcesos nuevo, ptr, ant;
    
    if(tiempo != 0 && prioridad >= 1 && prioridad <= 5 && tiempo % INTERVALO == 0){
        ant = NULL;
        ptr = *lp;

        while (ptr != NULL && (ptr->prioridad > prioridad || (ptr->prioridad == prioridad && ptr->id != id))){
			ant = ptr;
			ptr = ptr->sig;
		}
        
        if(ptr !=NULL && ptr->id == id){
            ptr->tiempo+=tiempo;
        }else{
            nuevo = crearNodo(prioridad, id, tiempo);


            if(ant == NULL){
                if(ptr == NULL){
                    *lp = nuevo;
                }else{
                    nuevo->sig = ptr;
                    *lp = nuevo;
                }
            }else{
                nuevo->sig = ptr;
                ant->sig = nuevo;
            }
            
        }



        }


        

    }



void mostrar(LProcesos lp){
    LProcesos ptr;
    ptr = lp;
    if(lp == NULL){
        printf("Lista vacia Moni :P\n");
    }
    
    while(ptr != NULL){
        printf("[%d, %d, %d]  ", ptr->prioridad, ptr->id, ptr->tiempo);
        ptr = ptr->sig;
    }
    printf("\n");
}

void guardarFicheroPrioridad(LProcesos lp, unsigned prioridad, FILE *fd){
    fprintf(fd, "Prioridad %d\n", prioridad);
    int i = 0;
    while(lp != NULL){

        if(lp->prioridad == prioridad){
        fprintf(fd, "id:%d tiempo:%d\n", lp->id, lp->tiempo);
        i++;
        }
        lp = lp->sig;
    }
    if(i == 0){
        fprintf(fd, "Sin procesos\n");

    }
}


unsigned ejecutarMultiplesProcesadores(LProcesos *lp, unsigned tiempo, unsigned num_proc){
    LProcesos ptr, ant;
    unsigned contador = num_proc;
    ant = NULL;
    ptr = *lp;

    if(ptr != NULL && tiempo != 0 && tiempo % INTERVALO == 0){
        while(contador > 0 && ptr != NULL){
            contador--;
            
            if(ptr -> tiempo > tiempo){
                ptr->tiempo -= tiempo;
                ant =ptr;
                ptr = ptr->sig;
            }else{
                if(ant == NULL){
                    *lp = (*lp)->sig;
                    free(ptr);

                    ptr = *lp;

                }else{
                    ant->sig = ptr->sig;
                    free(ptr);
                    ptr = ant->sig;
                }
            }
    
        }
    }

    return contador;
}


void ejecutarMultiplesVeces(LProcesos* lp, unsigned tiempo, unsigned veces){
    while(veces >0){
        ejecutarMultiplesProcesadores(lp, tiempo, 1);
        veces--;
    }
}



LProcesos extraerProcesos(LProcesos * lp, unsigned prioridad){
    LProcesos aux, ptr1, ant;
    aux = NULL;

    ptr1 = *lp;
    ant = NULL;
    while(ptr1 !=NULL && ptr1->prioridad > prioridad){
        ant = ptr1;
        ptr1 = ptr1->sig;
    }

    while(ptr1 != NULL && ptr1->prioridad == prioridad){

        if(ant == NULL){
            insertar(&aux, ptr1->prioridad, ptr1->id, ptr1->tiempo);
            *lp = ptr1->sig;
            free(ptr1);
            ptr1 = *lp;
            
        }else{
            ant->sig = ptr1->sig;
        insertar(&aux, ptr1->prioridad, ptr1->id, ptr1->tiempo);
        free(ptr1);
        ptr1 = ptr1->sig;
        }

    }



    return aux;
}







void borrar(LProcesos *lp){
    LProcesos ptr = *lp;
    while(ptr != NULL){
        *lp = (*lp)->sig;
        free(ptr);
        ptr = *lp;
    }
}