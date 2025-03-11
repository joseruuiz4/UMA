#include "Lista.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void crear(Lista *l)
{
    *l = NULL;
}

int lista_vacia(Lista l)
{
    return (l == NULL);
}

void escribir(Lista l)
{
    Lista ptr = l;

    escribir_fichero(stdout, l);
}

void escribir_fichero(FILE *fp, Lista l)
{
    Lista ptr = l;

    while (ptr != NULL)
    {
        if (ptr->sig == NULL)
        {
            fprintf(fp, "%s", ptr->palabra);
        }
        else
        {
            fprintf(fp, "%s, ", ptr->palabra);
        }
        ptr = ptr->sig;
    }

    fprintf(fp, "\n");
}

void creaNodo(Lista *l, char *palabra)
{
    *l = (Lista)malloc(sizeof(struct item));
    strcpy((*l)->palabra, palabra);
    (*l)->sig = NULL;
}

void insertar(char *palabra, Lista *l)
{
    Lista ptr, nuevo;
    ptr = *l;
    creaNodo(&nuevo, palabra);
    if (ptr == NULL)
    {
        *l = nuevo;
    }
    else
    {
        if (!buscar_palabra(palabra, *l))
        {
            while (ptr->sig != NULL)
            {
                ptr = ptr->sig;
            }
            ptr->sig = nuevo;
        }
    }
}

void destruir(Lista *l)
{
    Lista ptr = *l;

    while (ptr != NULL)
    {
        (*l) = (*l)->sig;

        printf("Se va a eliminar la palabra %s\n", ptr->palabra);
        free(ptr);

        ptr = *l;
    }
}

int buscar_palabra(char *palabra, Lista l)
{
    int esta = 0;

    while (!esta && l != NULL)
    {
        if (strcmp(l->palabra, palabra) == 0)
        {
            esta = 1;
        }

        l = l->sig;
    }

    return esta;
}
