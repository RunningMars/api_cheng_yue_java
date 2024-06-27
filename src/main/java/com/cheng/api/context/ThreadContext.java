package com.cheng.api.context;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 用于存储用户上下文信息
 */
public class ThreadContext {

    static ThreadLocal<Map<String, JSONObject>> threadLocal = new ThreadLocal<>();

    public static void set(Map<String, JSONObject> value) {
        threadLocal.set(value);
    }

    public static JSONObject get(String key) {
        Map<String, JSONObject> value = threadLocal.get();
        return value.get(key);
    }

    public static int getUpdatedById() {
        JSONObject o = get("authInfo");
        return o.getInteger("id");
    }

    public static String getUpdatedByName() {
        JSONObject o = get("authInfo");
        return o.getString("name");
    }
}
