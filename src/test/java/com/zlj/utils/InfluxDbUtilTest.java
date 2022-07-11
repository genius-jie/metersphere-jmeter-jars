package com.zlj.utils;

import org.influxdb.dto.QueryResult;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class InfluxDbUtilTest {

    @Test
    void query() {
    }

    @Test
    void insert() {
        InfluxDbUtil influxDB = InfluxDbUtil.setUp();

        Map<String, String> tags = new HashMap<>();
        Map<String, Object> fields = new HashMap<>();
        tags.put("TAG_NAME", "abc");
        fields.put("TAG_VALUE", "111");
        try {
            fields.put("TIMAMPEST", "ooo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        influxDB.insert(tags, fields, "dm_zlj_usql_kafka2influxdb_dw");
        //查询

        QueryResult result = influxDB.query("select * from dm_zlj_usql_kafka2influxdb_dw; ");
        System.out.println(result);
        System.out.println(influxDB.getPassword());
    }

    @Test
    void deleteMeasurementData() {
    }
}