package com.Mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rony- on 10/25/2016.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    MongoClient mongoClient = new MongoClient("localhost",27017);
    MongoDatabase db = mongoClient.getDatabase("practica") ;

    @RequestMapping("")
    public String getIndexPage(Model model, HttpServletRequest request) {

        return "/indice";
    }










}
