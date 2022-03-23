package com.zlj.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Map;

public class MongodbUtil {
    public static MongodbServer builder(String url, String databaseName, String collectionName) {
        return new MongodbServer(url, databaseName, collectionName);
    }

    public static class MongodbServer {
        MongoCollection collection = null;

        private MongodbServer(String url, String databaseName, String collectionName) {
            MongoClient mongoClient = MongoClients.create(url);
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            collection = database.getCollection(collectionName);
        }

        public void sendMsg(Map<String, Object> valueMap) {
            Document document = new Document(valueMap);
            collection.insertOne(document);
        }
    }

}
