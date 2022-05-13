package com.zlj.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Map;

public class MongodbUtil {
    private static MongodbUtil mongodbUtil;
    private static MongoCollection collection ;
    private static MongoClient mongoClient ;
    private static MongoDatabase database;
    private MongodbUtil() {
    }

    public static synchronized  MongodbUtil getInstance(String url, String databaseName, String collectionName) {
        mongoClient = MongoClients.create(url);
        database = mongoClient.getDatabase(databaseName);
        collection = database.getCollection(collectionName);
        mongodbUtil=new MongodbUtil();
        return mongodbUtil;
    }

    public void sendMsg(Map<String, Object> valueMap) {
        Document document = new Document(valueMap);
        collection.insertOne(document);
    }


}
