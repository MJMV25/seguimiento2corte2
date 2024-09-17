package org.example.domain;

import java.io.Serializable;

public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private String direccion;
    private String telefono;



    public Paciente() {
    }

    public Paciente(String nombre, String apellido, int edad, String genero, String direccion, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return nombre + "," + apellido + "," + edad + "," + genero + "," + direccion + "," + telefono;
    }

    public static Paciente fromString(String str) {
        String[] parts = str.split(",");
        return new Paciente(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3], parts[4], parts[5]);
    }
}
