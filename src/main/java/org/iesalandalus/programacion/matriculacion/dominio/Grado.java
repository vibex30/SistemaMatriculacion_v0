package org.iesalandalus.programacion.matriculacion.dominio;

public enum Grado {
    GDCFGB ("Grado básico"),
    GDCFGM ("Grado medio"),
    GDCFGS ("Grado superior");

    private String cadenaAMostrar;

    private Grado (String cadenaAMostrar){
        this.cadenaAMostrar=cadenaAMostrar;
    }

    public String imprimir(){
        return this.ordinal()+".-"+cadenaAMostrar;
    }

    @Override
    public String toString() {
        return "Grado{" +
                "cadenaAMostrar='" + cadenaAMostrar + '\'' +
                '}';
    }
}
