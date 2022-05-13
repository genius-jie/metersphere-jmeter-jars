package com.zlj.utils;

import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
class MongodbUtilTest {
    @Test
    public void insertTest(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id",1);
        map.put("name","java");
        map.put("age",1);
        map.put("birthday",new Date());
        MongodbUtil mongodbUtil = MongodbUtil.getInstance("mongodb://mongo:MongoDB_863*^#@10.1.56.161:27017", "test", "t1");
        mongodbUtil.sendMsg(map);
    }
}