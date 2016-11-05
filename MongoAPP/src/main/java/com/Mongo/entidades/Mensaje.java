package com.Mongo.entidades;

import java.util.ArrayList;

/**
 * Created by saleta on 11/5/16.
 */
public class Mensaje {
    private String mensaje;
    private Usuario usuario;
    private ArrayList<Comentario> comentarios;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
