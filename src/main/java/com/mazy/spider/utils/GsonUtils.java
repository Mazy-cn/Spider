package com.mazy.spider.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @ClassName: GsonUtils
 * @Description:
 * @Auther: Mazy
 * @create: 2020-04-27 19:03
 */
public class GsonUtils {
    public static Gson gson = new Gson();
    private static Gson disableHtmlEscapingGson = new GsonBuilder().disableHtmlEscaping().create();
    public static String toJson(Object object){
        return gson.toJson(object);
    }
    public static String disableHtmlEscaping(Object object){
        return disableHtmlEscapingGson.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> classOfT){
       return gson.fromJson(json,classOfT);
    }
    public static <T> T fromJson(String json, Type typeOfT){
        return gson.fromJson(json,typeOfT);
    }
    /**
     * @param json map的序列化结果
     * @param <K>   k类型
     * @param <V>   v类型
     * @return Map<K       ,       V>
     */
    public static <K, V> Map<K, V> toMap(String json, Class<K> kClazz, Class<V> vClazz) {
        return disableHtmlEscapingGson.fromJson(json, TypeToken.getParameterized(Map.class,kClazz,vClazz).getType());
    }
}
