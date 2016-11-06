package com.Mongo.entidades;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rony- on 10/18/2016.
 */

public class Usuario implements Serializable{

    private String username;

    private String nombre;
    private String apellido;

    private String password;


    public Usuario(String nombre, String apellido, String cedula, String username, String password){
        this.nombre=nombre;
        this.apellido=apellido;
        this.username=username;
        this.password=password;
    }

    public Usuario(){
        username="";
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
