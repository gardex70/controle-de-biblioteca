package br.edu.ifpr.foz.controledebiblioteca.models;

public enum BookStatus {
    AVAILABLE("Disponível"),
    UNAVAILABLE("Indisponível"),
    BORROWED("Emprestado");

    private final String value;

    BookStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
