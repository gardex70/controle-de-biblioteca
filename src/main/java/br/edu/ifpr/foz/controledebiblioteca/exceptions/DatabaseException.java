package br.edu.ifpr.foz.controledebiblioteca.exceptions;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String msg){
        super(msg);
    }
}
