package com.ulacit.matriculas.matriculasulacit.Modelos;


public class Constante {

    public Constante() {}
    /*
     * Status de error del response
     * Aqui se aplica el patron de Singleton
     * */
    public final static int ok = 200;
    public final static int badRequest = 400;
    
    public final static String itemNotFound = "Item not found";
    public final static String itemDeleted = "Item Deleted";
}
