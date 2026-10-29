#include "Polinomio.h"
#include <stdio.h>
#include <stdlib.h>

void polinomioCero(TPolinomio *p)
{
    *p = NULL;
}

unsigned int grado(TPolinomio p)
{
    return p->exp;
}

unsigned int coeficiente(TPolinomio p, unsigned int exp){
	unsigned int coef = 0;

	while (p != NULL && p->exp > exp){
		p = p -> sig;
	}

	if (p != NULL && p->exp == exp){
		coef = p->coef;
	}

	return coef;
}

void crearNodo(TPolinomio *nuevo, unsigned int coef, unsigned int exp)
{
    *nuevo = (TPolinomio)malloc(sizeof(struct TMonomio));
    (*nuevo)->coef = coef;
    (*nuevo)->exp = exp;
    (*nuevo)->sig = NULL;
}

void insertar(TPolinomio *p, unsigned int coef, unsigned int exp)
{
    TPolinomio ptr, ant;
    
    
    if (coef != 0)
    {
        ant = NULL;
        ptr = *p;
        while (ptr != NULL && ptr->exp > exp)
        {
                ant = ptr;
                ptr = ptr->sig;
        }
        if (ptr != NULL && exp == ptr->exp)
        {
            ptr->coef += coef;
        }
        else
        {
            TPolinomio nuevo;
            crearNodo(&nuevo, coef, exp);

            if(ant == NULL){
                nuevo->sig = *p;
                *p = nuevo;
            }else{
                nuevo->sig = ptr;
                ant->sig = nuevo;
                
            }
            
        }
    }
}


void imprimir(TPolinomio p){
	int exp;
	unsigned int coef;

	if (p != NULL){
		exp = grado(p);
		printf("[");
		while (exp>=0){
			coef = coeficiente(p,exp); //usar esta llamada es mas ineficiente que la otra version de imprimir
			printf("(%d,%d)",coef,exp);
			exp--;
		}
		printf("]\n");
	}else{
		printf("Polinomio cero");
	}
}

void destruir(TPolinomio *p)
{
    TPolinomio borrar = *p;
    while (borrar != NULL)
    {
        *p = (*p)->sig;
        free(borrar);
        borrar = *p;
    }
}

void crearDeFichero(TPolinomio *p, char *nombre){
    FILE * f = fopen(nombre, "rt");
    char coef, exp;
    unsigned int c, e;
    int nledios;

    if(f == NULL){
        printf("Error abriendo el fichero\n");
    }else{
        polinomioCero(p);
        while ((nledios = fscanf(f,"%c%c",&coef,&exp))> 0){
			c = coef-'0';
			e = exp-'0';
			insertar(p,c,e);
		}
		fclose(f);
    }
}