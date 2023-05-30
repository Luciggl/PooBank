package org.example.exception;

public class ContaNaoExisteException extends Exception{
    public ContaNaoExisteException(String msg){
        super(msg);
    }
}
