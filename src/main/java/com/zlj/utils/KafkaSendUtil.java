package com.zlj.utils;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.List;
import java.util.Properties;

public class KafkaSendUtil {
    public static KafkaStreamServer bulidServer(String brokerList) {
        return new KafkaStreamServer(brokerList);
    }

    public static class KafkaStreamServer {
        KafkaProducer<String, String> producer = null;

        private KafkaStreamServer(String brokerList) {
            Properties properties = new Properties();
            properties.put("bootstrap.servers", brokerList);
            properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            producer = new KafkaProducer<String, String>(properties);
        }

        public KafkaStreamServer sendMsg( String topic, List<String> datas) {
            try {
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
            return this;
        }
    }
}