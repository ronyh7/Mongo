package com.Mongo;

import com.Mongo.entidades.Mensaje;
import com.Mongo.entidades.Usuario;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rony- on 10/25/2016.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    MongoClient mongoClient = new MongoClient("localhost",27017);
    MongoDatabase db = mongoClient.getDatabase("practica") ;
    MongoCollection<Document> collection = db.getCollection("mensaje");


    @RequestMapping("")
    public String getIndexPage(Model model, HttpServletRequest request) {
        List<Document> mensajes = collection.find().into(new ArrayList<Document>());
        model.addAttribute("mensajes",mensajes);

        return "/indice";
    }


    @RequestMapping("/crearMensaje")
    public String mensaje(Model model, HttpServletRequest request) {
        model.addAttribute("mensaje", new Mensaje());
        return "/mensaje";
    }


    @PostMapping("/crearMensaje")
    @Transactional
    public String mensaje(@ModelAttribute Mensaje mensaje, HttpServletRequest request){

        System.out.println(mensaje.getMensaje());
        org.bson.Document Mensaje = new org.bson.Document();
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        Document comentarios = new Document();
        Mensaje.append("mensaje",mensaje.getMensaje()).append("autor",u.getUsername()).append("comentarios",comentarios);


        collection.insertOne(Mensaje);

        return "redirect:/";

    }
    @RequestMapping("/crearComentario")
    public String comentario(@RequestParam ("id") String id, Model model, HttpServletRequest request) {
        model.addAttribute("mensaje", new Mensaje());
        String object ="ObjectId(\"";
        String ID = object+id+"\")";
        System.out.println(ID);
        //List<Document> mensajes= collection.find(new Document("_id",ID)).into(new ArrayList<Document>());
        Document d = collection.find(new Document("_id",ID)).first();
        System.out.println("SIZE:"+d);
        //Document mensaje = mensajes.get(0);
        //System.out.println(mensaje);
        return "/mensaje";
    }










}
