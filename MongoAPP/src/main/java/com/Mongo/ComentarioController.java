package com.Mongo;

import com.Mongo.entidades.Mensaje;
import com.Mongo.entidades.Usuario;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.ne;

/**
 * Created by rony- on 10/25/2016.
 */
@Controller
@RequestMapping("/comentario")
public class ComentarioController {

    MongoClient mongoClient = new MongoClient("localhost",27017);
    MongoDatabase db = mongoClient.getDatabase("practica");
    MongoCollection<org.bson.Document> collection = db.getCollection("mensaje");
    int idMensaje;



    @RequestMapping("")
    public String comentario(@RequestParam ("id") int id, Model model, HttpServletRequest request) {
        Document d = collection.find(new Document("_id",id)).first();
        idMensaje=id;
        model.addAttribute("mensaje",d.get("mensaje"));
        model.addAttribute("autor",d.get("autor"));
        model.addAttribute("comentarios",d.get("comentarios"));
        return "/comentario";
    }

    @PostMapping("/crearComentario")
    @Transactional
    public String postNuevoComentario(@RequestParam("comentario") String comentario, HttpServletRequest request){

        Document mensaje = collection.find(new Document("_id",idMensaje)).first();
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        if (mensaje.get("comentarios")==null) {
                Document comentarioN = new Document();
                comentarioN.append("_id", 1).append("texto", comentario).append("autor", u.getUsername());
                List<Document> comentarios = new ArrayList<>();
                comentarios.add(comentarioN);
                mensaje.append("comentarios",comentarios);

        }
        else {
            List<Document> comentarios = (List<Document>) mensaje.get("comentarios");
            int idc = comentarios.size() + 1;

            Document nuevoC = new Document();
            nuevoC.append("_id", idc).append("texto", comentario).append("autor", u.getUsername());
            comentarios.add(nuevoC);

            System.out.println(comentarios.size());
            //collection.updateOne(eq("_id", idMensaje),new Document("$push", new Document("comentarios",c)));
            mensaje.remove("comentarios");
            mensaje.append("comentarios", comentarios);
        }
        collection.replaceOne(new Document("_id",idMensaje),mensaje);



        return "redirect:/";

    }





}
