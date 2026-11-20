package com.pos.util;

public class PosExeption extends RuntimeException{
    public PosExeption(String massage) {
        super(massage);
    }

    public PosExeption(String massage, Throwable cause) {
        super(massage, cause);
    }
}
