package com.zlj.utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
class KafkaSendUtilTest {
    @Test
    public void kafkaSendTest1() {
        List<String> list = new ArrayList<String>();
//        list.add("{\"name\":\"zlj\",\"price\":3,\"timestamp\":1654764422080,\"__PROCESS_TIME__\":1654764422080}");
        list.add("{\"name\":\"zlj\",\"price\":32,\"timestamp\":1654764422080,\"__PROCESS_TIME__\":1654764422080}");
        KafkaSendUtil kafka= KafkaSendUtil.getInstance("10.1.56.161:9192");
        kafka.sendMsg( "ods_zlj_out_78", list);
    }
}