package com.zlj.utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
class KafkaSendUtilTest {
    @Test
    public void kafkaSendTest1() {
        List<String> list = new ArrayList<String>();
        list.add("{\"d\":\"s\"}");
        list.add("{\"d1\":\"s1\"}");
        KafkaSendUtil kafka= KafkaSendUtil.getInstance("10.1.56.161:9192");
        kafka.sendMsg( "zlj-test", list);
    }
}