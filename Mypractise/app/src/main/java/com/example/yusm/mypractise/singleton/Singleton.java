package com.example.yusm.mypractise.singleton;
/*
 *
 * Date: 2020/11/12
 * Desc：
 */

public class Singleton {

    private static Singleton instance = new Singleton();

    private Singleton(){

    }

    public static Singleton getInstance(){
        return instance;
    }

}
