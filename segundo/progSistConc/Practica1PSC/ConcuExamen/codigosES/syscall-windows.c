#include <stdio.h>
#include <io.h>

/* Cuando se ejecuta una operación de salida de alto nivel,
   la salida se suele almacenar primero en un buffer. 
       
   Hay dos enfoques distintos a la hora de definir el buffer a utilizar:
       - full-buffered: El contenido del buffer se muestra en la salida estandar
                        cuando el buffer está lleno o el programa finaliza
       - line-buffered: El contenido del buffer se muestra en la salida estandar
                        cuando el buffer está lleno, el programa finaliza y también
                        cuando hay un salto de línea.

   Habitualmente, cuando el flujo de salida en el que se va a escribir el mensaje
   es el terminal de salida estándar y se entiende que habrá un usuario esperando
   dicha salida, el buffer suele ser line-buffered para que se muestre de forma más rápida.
   Otras veces incluso puede deshabilitarse de forma automática el buffer (por ejemplo,
   dependiendo del flujo de salida (si es a la salida estándar, o a un fichero, ...)).

   HAY QUE TENER EN CUENTA QUE LA FORMA EN LA QUE SE MANEJA EL BUFFER ASOCIADO A LA E/S
   ES UNA DECISIÓN DE IMPLEMENTACIÓN Y NO DE ESPECIFICACIÓN DEL LENGUAJE. 
   Esto quiere decir que en la definición estándar del lenguaje C no se indica nada sobre 
   como implementar el mecanismo de buffering, y cada implementación de esa especificación 
   podría implementarlo de forma distinta. Por lo tanto va a ver diferencias en el 
   funcionamiento de printf() y otras operaciones de E/S dependiendo de la implementación.

   En Linux la función write() de la librería unistd.h hace una llamada al sistema operativo
   y escribe directamente en el terminal de salida, sin hacer buffering. PERO ESE EJEMPLO 
   (syscall.c) NO FUNCIONA IGUAL EN WINDOWS, porque write no es una función del sistema operativo 
   Windows. La librería se proporciona por motivos de compatibilidad, pero el comportamiento de
   las funciones no es exactamente igual. No estan implementadas de la misma manera.

   Esto es un ejemplo alternativo para Windows donde se consigue el mismo efecto cuando este programa 
   se ejecuta en Windows:
         1. Forzamos a que el buffer utilizado por la función printf() se comporte
            como un buffer de tipo full-buffered y le damos un tamaño cualquiera, ej. 1024.
         2. Utilizamos la función printf() que hará uso del buffer definido anteriormente.
         3. Utilizamos la función _write() de la librería io.h que no hace buffering.
*/
int main()
{
    /*
       Definimos para stdout un buffer de tamaño 1024 que se enviará  a la salida cuando se 
       llene el buffer (para ello hemos usado la constante _IOFBF (Input Output Full
       Buffer) como tercer parámetro de la función setvbuf(). El segundo parámetro a NULL
       significa que el buffer se crea internamente, no lo proporcionamos en la llamada.

       Incluso añadiendo un retorno de carro al final de la cadena, como el buffer
       no se ha llenado el mensaje "Adios, mundo" aparece antes

    */
    setvbuf(stdout,NULL,_IOFBF,1024); 
    
    /*Si pusieramos la siguiente línea en lugar de la anterior, estaríamos indicando
    que no se use ningún buffer y entonces se escribiría primero "Hola, mundo"
        - _IONBF: Input Output Non Buffer
    */
    //setvbuf(stdout,NULL,_IONBF,0); 

    printf("Hola, mundo\n");
   _write(1,"Adios, mundo ",13);

    return(0);
}

