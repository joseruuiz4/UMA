public enum EstadoAnimal {
    DISPONIBLE("disponible"),
    ADOPTADO("adoptado"),
    ENTRAMITAMIENTO("enTramitamiento");

    private final String estadoAnimal;

    EstadoAnimal(String estadoAnimal) {
        this.estadoAnimal = estadoAnimal;
    }

    public String getEstadoAnimal() {
        return estadoAnimal;
    }

}