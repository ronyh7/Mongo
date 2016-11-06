package com.Mongo;

import com.Mongo.entidades.Mensaje;
import com.Mongo.entidades.Usuario;
import com.mongodb.MongoClient;
import com.mongodb.MongoCommandException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.event.CommandFailedEvent;
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
import java.util.function.Consumer;

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
        List<Document> parametros = new ArrayList<>();


        parametros.add(new Document("$project", new Document("comentarios",1).append("mensaje",1).append("cantidad",1)));
        parametros.add(new Document("$unwind","$comentarios"));
        parametros.add(new Document("$group", new Document("_id", "$_id").append("cantidad", new Document("$max","$comentarios._id"))));
        parametros.add(new Document("$sort",new Document("cantidad",-1)));




        AggregateIterable<Document> resultados = collection.aggregate(parametros);
        List<Document> r2 = new ArrayList();

        resultados.forEach(new Consumer<Document>() {
            @Override
            public void accept(Document document) {
                mensajes.forEach(new Consumer<Document>() {
                    @Override
                    public void accept(Document document2) {
                        if(document.get("_id")==document2.get("_id")){
                            Document d = new Document();
                            d.append("_id",document2.get("_id"));
                            d.append("mensaje",document2.get("mensaje"));
                            d.append("autor",document2.get("autor"));
                            d.append("comentarios",document2.get("comentarios"));
                            d.append("cantidad",document.get("cantidad"));
                            r2.add(d);
                        }
                    }
                });
            }
        });
        /*mensajes.forEach(new Consumer<Document>() {
            @Override
            public void accept(Document document) {
                System.out.println("JSON1: "+ document.toJson());
            }
        });*/
        r2.forEach(new Consumer<Document>() {
            @Override
            public void accept(Document document) {
                System.out.println("JSON2: "+ document.toJson());
            }
        });
        List<Document> mensajesSin = collection.find(new Document("comentarios",new Document("$exists",false))).into(new ArrayList<Document>());
        for(int i=0; i<mensajesSin.size();i++){
            Document d = mensajesSin.get(i);
            d.append("cantidad",0);
            r2.add(d);
        }

        model.addAttribute("mensajes",r2);
        model.addAttribute("mensaje", new Mensaje());
        Usuario usuario =(Usuario)request.getAttribute("usuario");
        if(usuario==null){
            model.addAttribute("usuario",new Usuario());
        }
        else{
            model.addAttribute("usuario",usuario);
        }

        return "/indice";
    }



    @PostMapping("/crearMensaje")
    @Transactional
    public String mensaje(@ModelAttribute Mensaje mensaje, HttpServletRequest request){
        List<Document> mensajes = collection.find().into(new ArrayList<Document>());
        int id = mensajes.size()+1;
        org.bson.Document Mensaje = new org.bson.Document();
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        Document comentarios = new Document();

        Mensaje.append("_id",id).append("mensaje",mensaje.getMensaje()).append("autor",u.getUsername());


        collection.insertOne(Mensaje);

        return "redirect:/";

    }











}
