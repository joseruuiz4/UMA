void liberarMemoria(struct Info** info){
    if((*info) -> notas != NULL){
        free((*info) -> notas);
    }
    free(*info);
    *info = NULL;
}
