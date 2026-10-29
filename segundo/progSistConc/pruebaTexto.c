#include <stdio.h>

int main(){
FILE *fd;

fd = fopen("prueba.txt", "rt");
if(fd == NULL){
    perror("Error al crear el fichero");
}else{
    char valor;
    while(!feof(fd)){
        fscanf(fd, "%c ", &valor);
        printf("%c ", valor);
    }
    printf("\n");
    fclose(fd);
}
}