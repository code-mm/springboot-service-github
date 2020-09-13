package com.ms.common.utils;

import com.google.gson.Gson;

public class GsonUtils {

    private static final GsonUtils INSTANCE = new GsonUtils();

    private GsonUtils() {

    }

    private static final Gson gson = new Gson();

    public static GsonUtils getInstance() {
        return INSTANCE;
    }

    public static Gson getGson() {
        return gson;
    }
}
