package com.example.yusm.mypractise.designpattern;
/*
 *
 * Date: 2019/7/18
 * Desc：
 */

import java.util.HashMap;
import java.util.Map;

public class GoodsFactory {

    private static Map<String,Goods> pool = new HashMap<>();

    public static Goods getGoods(String name){
        if(pool.containsKey(name)){
            System.out.println("使用缓存，key为："+name);
            return pool.get(name);
        }else{
            Goods goods = new Goods(name);
            pool.put(name,goods);
            System.out.println("创建商品，key为："+name);
            return goods;
        }
    }
}
