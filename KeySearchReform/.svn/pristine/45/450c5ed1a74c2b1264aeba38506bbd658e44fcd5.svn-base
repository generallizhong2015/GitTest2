package com.gsoft.keyhandover.util;


import com.google.gson.Gson;

public class JsonUtil {
    static Gson gson = new Gson();
    public static <T> String getString(T data){
        return gson.toJson(data);
    }

    public static <T> T getObject(String json,Class<T> classType){
        return (T) gson.fromJson(json,classType);
    }
}
