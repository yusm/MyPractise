package com.example.yusm.mypractise.designpattern;
/*
 *
 * Date: 2019/7/18
 * Desc：
 */

import java.lang.reflect.Proxy;

public class Singleton {

    private static volatile Singleton instance;

    private Singleton(){}

    public static Singleton getInstance(){
        if(instance==null){
            synchronized (Singleton.class){
                if(instance==null)
                    instance = new Singleton();
            }
        }
        return instance;
    }

    private void proxy(){
//        Proxy.newProxyInstance(getClass().getClassLoader(),)
    }
}
