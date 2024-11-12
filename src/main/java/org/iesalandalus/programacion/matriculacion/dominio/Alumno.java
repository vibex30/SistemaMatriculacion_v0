package org.iesalandalus.programacion.matriculacion.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alumno {
    private final String ER_TELEFONO="[0-9]{9}";
    private final String ER_CORREO="[w.-]+[@][w.-]+[.][a-zA-Z]+";
    private final String ER_DNI="([d]{8})([a-zA-Z])";
    public final String FORMATO_FECHA="dd/MM/yyyy";
    private final String ER_NIA="";
    private final int MIN_EDAD_ALUMNADO=16;
    private String nombre;
    private String telefono;
    private String correo;
    private String dni;
    private LocalDate fechaNacimiento;
    private String nia;


    //CONSTRUCTORES
    public Alumno(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {
        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
        setNia();

        /*this.nombre = nombre;
        this.dni = dni;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;*/
    }

    public Alumno(Alumno alumno){
        if (alumno==null){
            throw new NullPointerException("ERROR: No es posible copiar un alumno nulo.");
        }
        setNombre(alumno.getNombre());
        setDni(alumno.getDni());
        setCorreo(alumno.getCorreo());
        setTelefono(alumno.getTelefono());
        setFechaNacimiento(alumno.getFechaNacimiento());
        setNia(alumno.getNia());
    }



    //GETTER Y SETTER
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        if (nombre==null){
            throw new NullPointerException("ERROR: El nombre de un alumno no puede ser nulo.");
        }
        if (nombre.isEmpty() || nombre.isBlank()){
            throw new IllegalArgumentException("ERROR: El nombre de un alumno no puede estar vacío.");
        }
        this.nombre = nombre;
    }




    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        if (telefono==null){
            throw new NullPointerException("Error: el teléfono no puede ser nulo.");
        }
        if (telefono.isEmpty() || telefono.isBlank()){
            throw new IllegalArgumentException("Error: el campo del teléfono no puede estar vacío");
        }
        if (!telefono.matches(ER_TELEFONO)){
            throw new IllegalArgumentException("Error, el formato del teléfono no es válido.");
        }
        this.telefono = telefono;
    }




    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        if (correo==null){
            throw new NullPointerException("Error: el correo no puede ser nulo.");
        }
        if (correo.isEmpty() || correo.isBlank()){
            throw new IllegalArgumentException("Error: el campo del correo no puede estar vacío");
        }
        if (!correo.matches(ER_CORREO)){
            throw new IllegalArgumentException("Error, el formato del correo no es el adecuado.");
        }
        this.correo = correo;
    }




    public String getDni() {
        return dni;
    }
    private void setDni(String dni) {
        if (dni==null){
            throw new NullPointerException("ERROR: El dni de un alumno no puede ser nulo.");
        }
        if (dni.isEmpty() || dni.isBlank()){
            throw new IllegalArgumentException("ERROR: El dni del alumno no tiene un formato válido.");
        }
        if (!dni.matches(ER_DNI)){
            throw new IllegalArgumentException("ERROR: El dni del alumno no tiene un formato válido.");
        }
        if (!comprobarLetraDni(dni)){
            throw new IllegalArgumentException("Error, la letra del DNI no es válida");
        }
        this.dni = dni;
    }




    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    private void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento==null){
            throw new NullPointerException("Error: la fecha de nacimiento no puede ser nulo.");
        }
        if (fechaNacimiento.isAfter(LocalDate.now()) || fechaNacimiento.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Error, la fecha de nacimiento no puede ser anterior ni posterior a la fecha de hoy.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }




    public String getNia() {
        return nia;
    }
    private void setNia(){
        String caracteresNombre=nombre.toLowerCase().replaceAll("\\s", "").substring(0,4);
        String ultimosDigitosDni=dni.substring(0,8).substring(5);


        this.nia=caracteresNombre+ultimosDigitosDni;

    }
    private void setNia(String nia) {
        if (nia==null){
            throw new NullPointerException("Error: el NIA no puede ser nulo.");
        }
        if (nia.isEmpty() || nia.isBlank()){
            throw new IllegalArgumentException("Error: el campo del NIA no puede estar vacío");
        }
        this.nia = nia;
    }




    //EQUALS Y HASHCODE
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno alumno)) return false;
        return Objects.equals(dni, alumno.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }



    //FORMATEANOMBRE, COMPROBARLETRADNI,GETINICIALES, IMPRIMIR
    private String formateaNombre(String formateaNombre){
        if(formateaNombre==null){
            throw new NullPointerException("Error: el nombre formateado no puede ser null.");
        }
        if(formateaNombre.isBlank()){
            throw new IllegalArgumentException("Error: el nombre no puede estar en blanco.");
        }

        String nombreFormateado="";

        String nombreEnMinusSinEspacios = nombre.toLowerCase().trim();

        //split, separar cuando haya un espacio o mas
        String[] miArray = nombreEnMinusSinEspacios.split("\\s+");

        //poner en mayuscula la primera letra
        for(int i=0; i< miArray.length; i++)
            if (!miArray[i].isEmpty()) {
                char inicialMayuscula = Character.toUpperCase(miArray[i].charAt(0));
                nombreFormateado += inicialMayuscula + miArray[i].substring(1) + " ";
            }

        return nombreFormateado.trim();
    }



    private boolean comprobarLetraDni (String dni){

        Pattern patronDni= Pattern.compile(ER_DNI);
        Matcher m =patronDni.matcher(dni);
        if (!m.matches())
            throw new IllegalArgumentException("ERROR: La letra del dni del alumno no es correcta.");
        int numeroDni=Integer.parseInt(m.group(1));
        int resultadoDivision=numeroDni%23;
        String[] tablaLetras= {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};
        if (m.group(2).equalsIgnoreCase(tablaLetras[resultadoDivision]))
            return true;
        return false;
    }



    private String getIniciales(){
        String iniciales = "";
        for (int i = 0; i < nombre.length(); i++) {
            if (i == 0 || nombre.charAt(i - 1) == ' ') {
                iniciales += nombre.charAt(i);
            }
        }

        return iniciales.toUpperCase();
    }




    public String imprimir(){
        return String.format("Alumno: %s, DNI: %s, Teléfono: %s, Correo: %s, Fecha de Nacimiento: %s, NIA: %s",
                nombre, dni, telefono, correo, fechaNacimiento.format(DateTimeFormatter.ofPattern(FORMATO_FECHA)), nia);

    }




    //TO STRING
    @Override
    public String toString() {
        return "Alumno{" +
                "FORMATO_FECHA='" + FORMATO_FECHA + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", nia='" + nia + '\'' +
                '}';
    }
}
