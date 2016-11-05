package com.Mongo;

import com.Mongo.entidades.Usuario;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
@RequestMapping("/mensaje")
public class mensajeController {

    MongoClient mongoClient = new MongoClient("localhost",27017);
    MongoDatabase db = mongoClient.getDatabase("practica");
    MongoCollection<org.bson.Document> collection = db.getCollection("usuario");




    @RequestMapping("")
    public String getUsuarios(Model model, HttpServletRequest request) {


        model.addAttribute("usuario", request.getSession().getAttribute("usuario"));
        return "/mensaje";
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
        HashMap<String,String> usuarioM = new HashMap<>();
        usuarioM.put("username",usuario);
        usuarioM.put("password",password);
        usuarioM.put("nombre",nombre);
        usuarioM.put("apellido",apellido);
        usuarioD.putAll(usuarioM);

        collection.insertOne(usuarioD);

        return "redirect:/";

    }





}
