#include <stdio.h>
#include "cola.h"

int main(){
	ColaProcesos cola1, cola2;
	char proceso[6];

	crearCola(&cola1);
	printf("Llega proceso ID111 a cola 1...\n");
	anyadirProceso(&cola1,"ID111");
	printf("Llega proceso ID222 a cola 1...\n");
	anyadirProceso(&cola1,"ID222");
	printf("Llega proceso ID333 a cola 1...\n");
	anyadirProceso(&cola1,"ID333");
	printf("Llega proceso ID444 a cola 1...\n");
	anyadirProceso(&cola1,"ID444");
	printf("Llega proceso ID555 a cola 1...\n");
	anyadirProceso(&cola1,"ID555");
	printf("Llega proceso ID666 a cola 1...\n");
	anyadirProceso(&cola1,"ID666");

	mostrar(cola1);
	printf("Procesos esperando en cola 1: %d\n",procesosEsperando(cola1));

	extraerProceso(&cola1,proceso);
	printf("Siguiente proceso a atender de cola 1: %s\n",proceso);
	extraerProceso(&cola1,proceso);
	printf("Siguiente proceso a atender de cola 1: %s\n",proceso);
	extraerProceso(&cola1,proceso);
	printf("Siguiente proceso a atender de cola 1: %s\n",proceso);

	mostrar(cola1);

	crearCola(&cola2);
	printf("Llega proceso ID777 a cola 2...\n");
	anyadirProceso(&cola2,"ID777");
	printf("Moviendo 4 procesos de cola 1 a cola 2\n");
	moverProcesos(&cola2,&cola1,4);
	mostrar(cola1);
	printf("Procesos esperando en cola 1: %d\n",procesosEsperando(cola1));
	mostrar(cola2);
	printf("Procesos esperando en cola 2: %d\n",procesosEsperando(cola2));


	return 0;
}
