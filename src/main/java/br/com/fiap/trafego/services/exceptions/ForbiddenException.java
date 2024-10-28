package br.com.fiap.trafego.services.exceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String msg){
        super(msg);
    }
}
