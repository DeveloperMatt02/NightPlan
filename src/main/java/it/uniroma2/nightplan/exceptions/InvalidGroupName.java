package it.uniroma2.nightplan.exceptions;

import java.io.Serial;

public class InvalidGroupName extends Exception{
    @Serial
    private static final long serialVersionUID = 13465743L;

    public InvalidGroupName(String message){
        super(message);
    }
}