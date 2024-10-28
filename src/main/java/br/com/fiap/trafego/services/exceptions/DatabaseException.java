package br.com.fiap.trafego.services.exceptions;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String msg){
        super(msg);
    }
}
