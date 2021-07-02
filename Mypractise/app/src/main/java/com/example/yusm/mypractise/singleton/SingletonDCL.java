package com.example.yusm.mypractise.singleton;
/*
 *
 * Date: 2020/11/12
 * Desc：
 */


public class SingletonDCL {

    private static volatile SingletonDCL instance;

    private SingletonDCL(){

    }

    public static SingletonDCL getInstance(){
        if(instance==null){
            synchronized (SingletonDCL.class){
                if(instance==null)
                    instance = new SingletonDCL();
            }
        }
        return instance;
    }
}
