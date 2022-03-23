package com.zlj.utils;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.zlj.utils.MongodbUtil.MongodbServer;
import static org.junit.jupiter.api.Assertions.*;

class MongodbUtilTest {
    @Test
    public void insertTest(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id",1);
        map.put("name","java");
        map.put("age",1);
        map.put("birthday",new Date());
        MongodbServer builder = MongodbUtil.builder("mongodb://mongo:MongoDB_863*^#@10.1.56.161:27017", "test", "t1");
        builder.sendMsg(map);
    }
}