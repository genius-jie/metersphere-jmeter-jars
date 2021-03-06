package com.zlj.utils;


import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

class ClickHouseUtilTest {
    @Test
    public void SelectTest() {
        String url = "jdbc:clickhouse://10.1.52.99:8123/Databank";
        String user = "default";
        String password = "Admin@123";
        String sql = "select count(*) from Databank.zlj_test;";
        String[] params = {};
        ClickHouseUtil clickHouseUtils = ClickHouseUtil.getInstance(url, user, password);
          ResultSet resultSet = clickHouseUtils.QueryResultSet(sql, params);
          try {
              while (resultSet.next()) {
              int column = resultSet.getInt(1);
              // int scores = resultSet.getInt("score");
                  System.out.println(column);
              }
          } catch (SQLException e) {
          }
    }

    @Test
    public void insertTest() {
        String url = "jdbc:clickhouse://10.1.11.53:8123/nqtest";
        String user = "";
        String password = "";
//        String sql = "insert into  scores values(?,?,?);";
//        String[] params = {"zhanglijie", "sport", "100"};
//        ClickHouseUtil clickHouseUtils = ClickHouseUtil.getInstance(url, user, password);
//        int insertcount = clickHouseUtils.insertorupdate(sql, params);
//        System.out.println(insertcount);
        ClickHouseUtil clickHouseUtils = ClickHouseUtil.getInstance(url, user, password);
        String cluster="";
        String[] params = {};
        String sql="";
        clickHouseUtils.insertorupdate("CREATE DATABASE IF NOT EXISTS ZLJ "+cluster, params);
        clickHouseUtils.insertorupdate("DROP  TABLE IF EXISTS ZLJ.zlj_test "+cluster, params);
//        Thread.sleep(13000);
        // log.error("CREATE TABLE ZLJ.zlj_test "+cluster+"(`uuid` String,     `type` String,     `tags` String,     `timestamp` DateTime,     `tenantId` String,     `objectId` String,     `metric_name` String,     `ingress_time` DateTime,     `metric_value` Float64 ) ENGINE = MergeTree ORDER BY uuid SETTINGS index_granularity = 8192");
        clickHouseUtils.insertorupdate("CREATE TABLE ZLJ.zlj_test   (`uuid` String,     `type` String,     `tags` String,     `timestamp` DateTime,     `tenantId` String,     `objectId` String,     `metric_name` String,     `ingress_time` DateTime,     `metric_value` Float64 ) ENGINE = MergeTree ORDER BY uuid SETTINGS index_granularity = 8192", params);
        clickHouseUtils.close();
    }

    @Test
    public void updateTest() {
        String url = "jdbc:clickhouse://10.1.11.53:8123/nqtest";
        String user = "";
        String password = "";
        String sql = "ALTER TABLE scores  update  name =?  where name ='zhanglijie'";
        String[] params = {"zhangsan"};
        ClickHouseUtil clickHouseUtils = ClickHouseUtil.getInstance(url, user, password);
        int insertcount = clickHouseUtils.insertorupdate(sql, params);
        System.out.println(insertcount);
    }

    @Test
    public void deleteTest() {
        String url = "jdbc:clickhouse://10.1.11.53:8123/nqtest";
        String user = "";
        String password = "";
        String sql = "ALTER TABLE scores  DELETE   where name =?";
        String[] params = {"zhanglijie"};
            String s= "curl -XPOST -H \"Content-Type: application/json\"  -u admin:Admin@123 http://${esIp}:${esport}/dim_group_dimension/_delete_by_query?refresh -d \"{\\\"query\\\": {\\\"bool\\\": { \\\"must\\\": [ {\\\"prefix\\\": { \\\"metric_name\\\": \\\"zhanglijie\\\"}}]}}}\"";

                ClickHouseUtil clickHouseUtils = ClickHouseUtil.getInstance(url, user, password);
        int deletecount = clickHouseUtils.delete(sql, params);
        System.out.println(deletecount);
    }
}