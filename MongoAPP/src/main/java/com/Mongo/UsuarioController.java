package com.Mongo;

import com.Mongo.entidades.Usuario;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.data.mongodb.MongoCollectionUtils;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rony- on 10/25/2016.
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    MongoClient mongoClient = new MongoClient("localhost",27017);
    MongoDatabase db = mongoClient.getDatabase("practica");
    MongoCollection<org.bson.Document> collection = db.getCollection("usuario");




    @RequestMapping("")
    public String getUsuarios(Model model, HttpServletRequest request) {

        List<org.bson.Document> usuarios = collection.find().into(new ArrayList<org.bson.Document>());

        model.addAttribute("usuarios", usuarios);
        return "/usuarios";
    }

    @RequestMapping("/crearUsuario")
    public String usuario(Model model, HttpServletRequest request) {
        model.addAttribute("usuario", new Usuario());
        return "/crearUsuario";
    }


    @PostMapping("/crearUsuario")
    @Transactional
    public String postNuevoUsuario(@RequestParam("username") String usuario,
                                @RequestParam("password") String password, @RequestParam("nombre") String nombre,
                                @RequestParam("apellido") String apellido){

        Usuario u = new Usuario();
        u.setUsername(usuario);
        u.setNombre(nombre);
        u.setPassword(password);
        u.setApellido(apellido);

        org.bson.Document usuarioD = new org.bson.Document();
        List<org.bson.Document> usuarios = collection.find().into(new ArrayList<org.bson.Document>());
        int id=1;
        if(usuarios!=null){
           id =usuarios.size()+1;
        }
        usuarioD.append("_id",id+"").append("username",usuario).append("password",password).append("nombre",nombre).append("apellido",apellido);

        collection.insertOne(usuarioD);

        return "redirect:/";

    }





}
