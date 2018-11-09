package com.jfree.demo.config;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JSONUtil {

    //对象转json
    public static String poToJson(Object obj) {
        //SerializerFeature.PrettyFormat
        String json = JSONObject.toJSONString(obj);
        return json;
    }

    //对象转json,json字符串的额外的要求
    public static String poToJson(Object obj, SerializerFeature... features) {
        String json = JSONObject.toJSONString(obj, features);
        return json;
    }

    //json转对象
    public static <T> T jsonToPo(String json, Class<T> clazz) {
        T t = JSONObject.parseObject(json, clazz);
        return t;
    }

    //json对象转List
    public static <T> List<T> jsonToList(String json, Class<T> clazz) {
        List<T> list = JSONObject.parseArray(json, clazz);
        return list;
    }

    //json对象转Map
    public static Map jsonToMap(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        Map<String, Object> map = (Map<String, Object>) jsonObject;
        return map;
    }

    public static LinkedHashMap jsonToLinkedHashMap(String json) {
        LinkedHashMap<String, String> map = JSONObject.parseObject(
                json, LinkedHashMap.class, Feature.OrderedField);
                return map;
    }
}
