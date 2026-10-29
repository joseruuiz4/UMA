/*
 * P3 - A word around a ring with MPI
 * P3_ring.c - starting code
 *
 * Process 0 reads a word from the keyboard and sends it to process 1.
 * Every process prints that it has the word and passes it to the next one.
 * When the word returns to process 0 the program reports it and ends.
 *
 * This file contains THREE deliberate defects, of three different kinds:
 *   - one that the compiler rejects
 *   - one that appears the first time you run it
 *   - one that appears only when you use more than two processes
 *
 * Above each correction, write a comment saying what was wrong and how you
 * noticed it.
 */

#include <mpi.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

#define MAXWORD 64
#define TAG 1

int main(int argc, char* argv[]) {
  int rank, size, next, prev;
  char word[MAXWORD];
  MPI_Status status;

  MPI_Init(&argc, &argv);
  /*
  Al compilar avisa del error:
  error: ‘MPI_COMM_ALL’ no se declaró aquí (primer uso en esta función)

  El comnunicador correcto es MPI_COMM_WORLD ya que deben referirse al mismo
  grupo que usan los MPI_Send y MPI_Recv en las lineas 61, 62, 76 y 81.
  */
  MPI_Comm_rank(MPI_COMM_WORLD, &rank);
  MPI_Comm_size(MPI_COMM_WORLD, &size);

  /*
  next no da la vuelta al anillo, por lo que el proceso 0 no recibe el mensaje
  de vuelta y el programa se queda bloqueado. Para solucionarlo, se debe
  calcular next como (rank + 1) % size, de esta manera el proceso 0 recibirá el
  mensaje del último proceso
  */
  next = (rank + 1) % size;
  prev = (rank - 1 + size) % size;

  if (rank == 0) {
    printf("type a word and press enter: ");
    fflush(stdout);
    if (scanf("%63s", word) != 1) {
      printf("no word was read\n");
      MPI_Finalize();
      return 1;
    }
    printf("process 0 sends the word %s\n", word);
    fflush(stdout);

    MPI_Send(word, MAXWORD, MPI_CHAR, next, TAG, MPI_COMM_WORLD);
    MPI_Recv(word, MAXWORD, MPI_CHAR, prev, TAG, MPI_COMM_WORLD, &status);
    sleep(2);

    printf("the word %s came back to process 0, the ring is closed\n", word);
    fflush(stdout);
  } else {
    /*
    MPI_Recv(word, MAXWORD, MPI_CHAR, 0, TAG, MPI_COMM_WORLD, &status);

    Hace que todos los procesos distintos del 0 esperen a que el proceso 0 les
    envie un mensaje, pero solo el proceso 1 lo recibira. Los demas procesos se
    quedaran bloqueados esperando un mensaje que nunca llegara. Para arrelgarlo,
    se debe cambiar el origen del mensaje a prev, ya que cada proceso debe
    recibir el mensaje del proceso anterior en el anillo.
    */
    MPI_Recv(word, MAXWORD, MPI_CHAR, prev, TAG, MPI_COMM_WORLD, &status);
    sleep(2);

    printf("process %d has the word %s\n", rank, word);
    fflush(stdout);

    MPI_Send(word, MAXWORD, MPI_CHAR, next, TAG, MPI_COMM_WORLD);
  }

  MPI_Finalize();
  return 0;
}
