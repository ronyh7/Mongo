package com.Mongo;

import com.Mongo.entidades.Usuario;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;


@Controller()
@RequestMapping("/login")
public class LoginController {

    MongoClient mongoClient = new MongoClient("localhost",27017);
    MongoDatabase db = mongoClient.getDatabase("practica");

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getLoginPage(Model model, HttpServletRequest request) {
        model.addAttribute("usuario", new Usuario());
        return "/login";
    }

    @PostMapping
    public String postLoginPage(@RequestParam("username") String username,
                                @RequestParam("password") String password, HttpServletRequest request){

        Usuario u = new Usuario();
        u.setUsername(username);
        u.setPassword(password);

        MongoCollection mc = db.getCollection("usuario");
        List<Document> parametros = new ArrayList<>();
        parametros.add(new Document("$match",new Document("username",username).append("password",password)));

        AggregateIterable<Document> resultados = mc.aggregate(parametros);

        resultados.forEach(new Consumer<Document>() {
            @Override
            public void accept(Document document) {

            }
        });
        if(resultados.first()!=null) {
            HttpSession session = request.getSession(true);
            session.putValue("usuario",u);
            return "redirect:/";
        }
        else
            return "redirect:/login";

    }


}
