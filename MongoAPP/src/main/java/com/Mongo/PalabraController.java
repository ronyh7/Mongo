package com.Mongo;

import com.Mongo.entidades.Mensaje;
import com.Mongo.entidades.Usuario;
import com.mongodb.DBCollection;
import com.mongodb.MapReduceCommand;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.MapReduceAction;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yaml.snakeyaml.tokens.WhitespaceToken;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by rony- on 10/25/2016.
 */
@Controller
@RequestMapping("/palabra")
public class PalabraController {

    MongoClient mongoClient = new MongoClient("localhost",27017);
    MongoDatabase db = mongoClient.getDatabase("practica");
    MongoCollection<Document> collection = db.getCollection("mensaje");

    String mapa = "function(){if(this.comentarios){for(var j=0; j<this.comentarios.length;j++){var s=this.comentarios[j].texto.split(\" \");for(var i=0; i < s.length; i++){emit(s[i],1);}}}}";
    String reduce = "function(key,values){var res=0;values.forEach(function(v){res++});return res;}";

    @RequestMapping("")
    public String comentario(Model model, HttpServletRequest request) {
        MapReduceIterable<Document> map =collection.mapReduce(mapa,reduce);
        db.createCollection("palabras");
        MongoCollection<Document> collectionP = db.getCollection("palabras");
        map.forEach(new Consumer<Document>() {
            @Override
            public void accept(Document document) {
                collectionP.insertOne(document);
            }
        });
        map.collectionName("palabras");

        List<Document> palabras = collectionP.find().sort(new Document("value",-1)).into(new ArrayList());

        model.addAttribute("palabras",palabras);
        Usuario usuario =(Usuario)request.getAttribute("usuario");
        if(usuario==null){
            model.addAttribute("usuario",new Usuario());
        }
        else{
            model.addAttribute("usuario",usuario);
        }
        collectionP.drop();

        return "/palabra";
    }

    @RequestMapping("/palabraMensaje")
    public String mensajesPalabra(@RequestParam("palabra") String palabra, Model model, HttpServletRequest request){
        List<Document> mensajes = collection.find().into(new ArrayList<Document>());

        List<Document> match = new ArrayList<>();

        mensajes.forEach(new Consumer<Document>() {
            @Override
            public void accept(Document document) {
                if(document.get("comentarios")!= null) {
                    List<Document> comentarios = (List<Document>) document.get("comentarios");
                    for(int j=0 ; j< comentarios.size();j++) {
                        String palabras[] = comentarios.get(j).get("texto").toString().split(" ");
                        for (int i = 0; i < palabras.length; i++) {
                            if (palabra.equals(palabras[i])) {
                                match.add(document);
                                break;
                            }
                        }
                    }
                }
            }
        });
        model.addAttribute("palabra",palabra);
        model.addAttribute("mensajes",match);

        Usuario usuario =(Usuario)request.getSession().getAttribute("usuario");
        if(usuario==null){
            model.addAttribute("usuario",new Usuario());
        }
        else{
            model.addAttribute("usuario",usuario);
        }


        return "/palabraMensaje";
    }





}
