package com.example.yusm.mypractise.singleton;
/*
 *
 * Date: 2020/11/12
 * Desc：
 */

public class Singleton4 {

    private Singleton4(){}

    private static final class InstanceHolder{
        private static Singleton4 INSTANCE = new Singleton4();
    }

    public static Singleton4 getInstance(){
        return InstanceHolder.INSTANCE;
    }
}
