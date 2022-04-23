package com.cdt.exception;

public class ModelNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1l;

    public ModelNotFoundException (String mensaje){
        super(mensaje);
    }

}
