package com.example.yusm.mypractise.utils;
/*
 *
 * Date: 2019/7/5
 * Desc：
 */

import java.lang.reflect.Field;

public class FieldUtils {

    public static Object getField(Class clazz,Object target,String name) throws Exception {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        return field.get(target);
    }

    public static Field getField(Class clazz,String name) throws Exception {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        return field;
    }

    public static void setField(Class clazz,Object target,String name,Object value) throws Exception {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        field.set(target,value);
    }
}
