package com.cricrush.assignment.cricrushassignment.controller;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;


/**
 * Created by CSI on 8/21/2018.
 */
@RestController
@RequestMapping(value = "init")
public class InitController {

    @RequestMapping(value = "getTeamData")
    @ResponseBody
    public Map<String,Object> getTeam(@RequestParam(value = "status" )String status){
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("cricrush");
        DBCollection coll = db.getCollection("matches");
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase database = client.getDatabase("cricrush");
        MongoCollection<Document> collection = database
                .getCollection("matches");

        List<Document> documents = (List<Document>) collection.find(eq("status", status)).into(new ArrayList<Document>());
        Map<String,Object> retMap= new HashMap<>();
        for(Document document :documents) {
            System.out.println(document);
        }
        retMap.put("matches",documents);
        return retMap;
    }

}
