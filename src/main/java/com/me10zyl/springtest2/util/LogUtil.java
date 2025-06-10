package com.me10zyl.springtest2.util;

import java.util.concurrent.ConcurrentHashMap;

public class LogUtil {
    private static final ConcurrentHashMap<Object, Boolean> flags = new ConcurrentHashMap<>();

    public static void log(String msg,  Object key){
        if(key != null){
            if(flags.get(key) == null){
                print(msg);
                flags.put(key, true);
            }
            return;
        }
        print(msg);
    }

    private static void print(String msg){
        System.out.println(msg);
    }

    public static void log(String msg){
        log(msg,  null);
    }
}
