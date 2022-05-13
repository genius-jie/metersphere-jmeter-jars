package com.zlj.utils;

import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.List;
import java.util.Properties;

public class KafkaSendUtil {

    //定义单例
    private static KafkaSendUtil kafkasendutil;
    private static Properties properties;
    private static KafkaProducer<String, String> producer;

    //构造函数私有化，外面不能new
    @SneakyThrows
    private KafkaSendUtil() {
    }

    //通过方法调用构造函数，单例初始化
    public static synchronized KafkaSendUtil getInstance(String brokerList) {
        if (kafkasendutil == null) {
            properties = new Properties();
            properties.put("bootstrap.servers", brokerList);
            properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            producer = new KafkaProducer<String, String>(properties);
            kafkasendutil = new KafkaSendUtil();
        }
        return kafkasendutil;
    }

    public void sendMsg(String topic, List<String> datas) {
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
    }

}