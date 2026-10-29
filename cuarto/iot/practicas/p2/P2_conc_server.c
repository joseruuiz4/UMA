/*
 * P2 - A concurrent server in C
 * P2_conc_server.c - starting code
 *
 * A TCP server that answers every client with the number of connections it
 * has served so far, then closes. Each client is attended by its own child
 * process, so a slow client does not block the others.
 *
 * TWO THINGS TO DO WITH THIS FILE:
 *
 *   1) One region is missing. It is marked below with the comment
 *      ==== YOUR CODE GOES HERE ====
 *      Read what that region has to achieve and write it.
 *
 *   2) The rest of the file contains THREE deliberate defects, of three
 *      different kinds: one that the compiler rejects, one that appears the
 *      first time you run it, and one that appears only when a client
 *      behaves in a particular way. They are outside the region you write,
 *      so a failure is either yours or theirs, and you can always tell.
 *
 * Above each correction, write a comment saying what was wrong and how you
 * noticed it.
 */

#include <arpa/inet.h>
#include <netinet/in.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

#define PORT 9100
#define BUFSIZE 256

/* Attends one client. Runs inside the child process. */
static void serve_client(int conn_fd, int served) {
  char buffer[BUFSIZE];
  int n;

  n = snprintf(buffer, BUFSIZE, "hello, you are client number %d\n", served);

  sleep(5); /* pretend the work takes time */

  /* the client may already have gone away while we were working */

  /*
   * El valor devuelto por write debe comprobarse: si el cliente cierra la
   * conexion, la escritura falla y no se debe continuar enviando datos.
   */
  if (write(conn_fd, buffer, n) < 0) {
    perror("write");
    close(conn_fd);
    return;
  }
  sleep(1);
  if (write(conn_fd, "goodbye\n", 8) < 0) {
    perror("write");
    close(conn_fd);
    return;
  }

  close(conn_fd);
  printf("server: child %d finished with client %d\n", getpid(), served);
  fflush(stdout);
}

int main(void) {
  int listen_fd, conn_fd;
  struct sockaddr_in serv_addr, cli_addr;
  socklen_t cli_len;
  int served = 0;

  /*
   * Si el cliente cierra la conexion antes de una escritura, write puede
   * provocar SIGPIPE. Ignorarlo permite comprobar el error mediante su valor
   * devuelto en serve_client.
   */
  if (signal(SIGPIPE, SIG_IGN) == SIG_ERR) {
    perror("signal");
    exit(1);
  }

  listen_fd = socket(AF_INET, SOCK_STREAM, 0);
  if (listen_fd < 0) {
    perror("socket");
    exit(1);
  }

  /* The port is released for immediate reuse. Without this, a server that
     has just been stopped leaves the port reserved for about a minute and
     the next run fails with "Address already in use". This is given to
     you: it is not one of the defects. */
  {
    int yes = 1;
    if (setsockopt(listen_fd, SOL_SOCKET, SO_REUSEADDR, &yes, sizeof(yes)) <
        0) {
      perror("setsockopt");
      exit(1);
    }
  }

  memset(&serv_addr, 0, sizeof(serv_addr));
  /*
  Al ejecutar el programa sale el error de:
  bind: Address family not supported by protocol

    Esto se debe a que la estructura sockaddr_in tiene un campo sin_family que
  indica el tipo de direccionamiento, que debe ser el mismo que el de la
  variable listen_fd, en este caso AF_INET (definido en la linea 64).

  Sustituimos UNIX por AF_INET y el error desaparece.
  */
  serv_addr.sin_family = AF_INET;
  serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
  serv_addr.sin_port = htons(PORT);

  if (bind(listen_fd, (struct sockaddr*)&serv_addr, sizeof(serv_addr)) < 0) {
    perror("bind");
    exit(1);
  }

  if (listen(listen_fd, 8) < 0) {
    perror("listen");
    exit(1);
  }

  printf("server: listening on port %d, my process number is %d\n", PORT,
         getpid());
  fflush(stdout);

  for (;;) {
    cli_len = sizeof(cli_addr);
    /*
    Al compilar por terminal avisa del error de que la funcion "accept" pide 3
    argumentos y solo se le estan pasando 2. Además avisa del warning de que la
    variable "cli_len" esta definida pero no se usa. En la definicion de la
    funcion accept el tercer argumento se define como "ADDR_LEN to the address's
    actual length", por lo que se le debe pasar la direccion de la variable
    "cli_len" para que pueda modificarla y guardar el tamaño de la direccion del
    cliente.
    */
    conn_fd = accept(listen_fd, (struct sockaddr*)&cli_addr, &cli_len);
    if (conn_fd < 0) {
      perror("accept");
      continue;
    }

    served++;
    printf("server: client %d connected from %s\n", served,
           inet_ntoa(cli_addr.sin_addr));
    fflush(stdout);

    /* ==== YOUR CODE GOES HERE ====
     *
     * This region has to do four things:
     *
     *   1. Create a child process.
     *   2. In the child: the listening socket is of no use, so close it,
     *      call serve_client(conn_fd, served), and end the child with
     *      exit(0). The child must never return to this loop.
     *   3. In the parent: the connected socket now belongs to the child,
     *      so close it and continue waiting for the next client.
     *   4. If the child could not be created, report it and close the
     *      connected socket.
     *
     * About eight lines. The function that creates a child returns 0 in
     * the child, the number of the child in the parent, and a negative
     * value on failure.
     */

    pid_t pid = fork();
    if (pid < 0) {
      perror("fork");
      close(conn_fd);
    } else if (pid == 0) {
      close(listen_fd);
      serve_client(conn_fd, served);
      exit(0);
    } else {
      close(conn_fd);
    }
    /*
     * ==== END OF YOUR CODE ====
     */
  }

  return 0;
}
