package org.iesalandalus.programacion.matriculacion.dominio;

import java.util.Objects;

public class Asignatura {
    public static final int MAX_NUM_HORAS_ANUALES=300;
    public static final int MAX_NUM_HORAS_DESDOBLES=6;
    private static final String ER_CODIGO="d{4}";
    private CicloFormativo cicloFormativo;
    private String codigo;
    private String nombre;
    private int horasAnuales;
    private Curso curso;
    private int horasDesdoble;
    private EspecialidadProfesorado especialidadProfesorado;



    //CONSTRUCTORES


    public Asignatura(CicloFormativo cicloFormativo, String codigo, String nombre, int horasAnuales, Curso curso, int horasDesdoble, EspecialidadProfesorado especialidadProfesorado) {
        setCicloFormativo(cicloFormativo);
        setCodigo(codigo);
        setNombre(nombre);
        setHorasAnuales(horasAnuales);
        setCurso(curso);
        setHorasDesdoble(horasDesdoble);
        setEspecialidadProfesorado(especialidadProfesorado);
    }

    public Asignatura(Asignatura asignatura){
        if (asignatura==null){
            throw new NullPointerException("Error, la asignatura no puede ser null");
        }
        setEspecialidadProfesorado(asignatura.getEspecialidadProfesorado());
        setCodigo(asignatura.getCodigo());
        setNombre(asignatura.getNombre());
        setHorasAnuales(asignatura.getHorasAnuales());
        setCurso(asignatura.getCurso());
        setHorasDesdoble(asignatura.getHorasDesdoble());
        setEspecialidadProfesorado(asignatura.getEspecialidadProfesorado());
    }

    //GETTER Y SETTER
    public CicloFormativo getCicloFormativo() {
        return cicloFormativo;
    }

    public void setCicloFormativo(CicloFormativo cicloFormativo) {
        if(cicloFormativo==null){
            throw new NullPointerException("Error, el ciclo formativo no puede ser null");
        }
        this.cicloFormativo = cicloFormativo;
    }

    public String getCodigo() {
        return codigo;
    }

    private void setCodigo(String codigo) {
        if(codigo==null){
            throw new NullPointerException("Error, el código no puede ser null");
        }
        if(codigo.isBlank()||codigo.isEmpty()){
            throw new IllegalArgumentException("Error, el código no puede estar vacío");
        }
        if (!codigo.matches(ER_CODIGO)){
            throw new IllegalArgumentException("Error, el código no sigue el patrón");
        }

        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre==null){
            throw new NullPointerException("Error, el nombre no puede ser null");
        }
        if(nombre.isBlank()||nombre.isEmpty()){
            throw new IllegalArgumentException("Error, el nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public int getHorasAnuales() {
        return horasAnuales;
    }

    public void setHorasAnuales(int horasAnuales) {
        if(horasAnuales<0||horasAnuales>MAX_NUM_HORAS_ANUALES){
            throw new IllegalArgumentException("Error, las horas no pueden ser esas");
        }
        this.horasAnuales = horasAnuales;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        if (curso==null){
            throw new NullPointerException("Error, el curso no puede ser null");
        }
        this.curso = curso;
    }

    public int getHorasDesdoble() {
        return horasDesdoble;
    }

    public void setHorasDesdoble(int horasDesdoble) {
        if (horasDesdoble<0||horasDesdoble>MAX_NUM_HORAS_DESDOBLES){
            throw new IllegalArgumentException("Error, el número de horas de desdoble no puede ser válido");
        }
        this.horasDesdoble = horasDesdoble;
    }

    public EspecialidadProfesorado getEspecialidadProfesorado() {
        return especialidadProfesorado;
    }

    public void setEspecialidadProfesorado(EspecialidadProfesorado especialidadProfesorado) {
        if(especialidadProfesorado==null){
            throw new NullPointerException("Error, la especialidad del profesorado no puede ser null");
        }
        this.especialidadProfesorado = especialidadProfesorado;
    }




    //EQUALS Y HASHCODE
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Asignatura that)) return false;
        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }




    //IMPRIMIR
    public String imprimir(){
        return "Código" + codigo;
    }



    //TO STRING
    @Override
    public String toString() {
        return "Asignatura{" +
                "cicloFormativo=" + cicloFormativo +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", horasAnuales=" + horasAnuales +
                ", curso=" + curso +
                ", horasDesdoble=" + horasDesdoble +
                ", especialidadProfesorado=" + especialidadProfesorado +
                '}';
    }
}
