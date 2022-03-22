package com.zlj.utils;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

public class KafkaSendUtil {
    public static void sendMsg(String brokerList, String topic, List<String> datas) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", brokerList);
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer producer=null ;
        try {
            producer = new KafkaProducer<String, String>(properties);
            for (int i = 0; i < datas.size(); i++) {

                ProducerRecord<String, String> message = new ProducerRecord<String, String>(topic, datas.get(i));
                producer.send(message);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (producer != null) {
                producer.close();
            }
        }
    }
}