#include <stdio.h>
#include <stdlib.h>

typedef struct DatosProceso *Proceso;

struct DatosProceso{
	char historial[6];
	Proceso sig;
};

struct ColaProcesos{
	Proceso frente;
	Proceso final;
};
typedef struct ColaProcesos ColaProcesos;

void crearCola(ColaProcesos *cola);
void anyadirProceso(ColaProcesos * cola, char * historial);
char* extraerProceso(ColaProcesos * cola, char * historial);
void vaciarCola(ColaProcesos *cola);
int colaVacia(ColaProcesos cola);
void moverProcesos(ColaProcesos * origen, ColaProcesos * destino, int num);
void mostrar(ColaProcesos cola);
int procesosEsperando(ColaProcesos cola);