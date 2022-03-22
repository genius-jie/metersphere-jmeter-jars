package com.zlj.utils;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class KafkaSendUtilTest {
    @Test
    public void kafkaSendTest1() {
        List<String> list = new ArrayList<>();
        list.add("{\"d\":\"s\"}");
        KafkaSendUtil.sendMsg("10.1.56.161:9192", "zlj-test", list);
    }


}