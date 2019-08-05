package com.itxiaox.hotfixlib;

import java.lang.reflect.Field;

public class ReflectUtils {


    /**
     * 通过反射获取某对象，并设置私有可访问
     * @param obj 该属性所属类的对象
     * @param clazz 该属性所属类
     * @param field 属性名
     * @return 该属性对象
     */
    private static Object getField(Object obj,Class<?> clazz,String field) throws NoSuchFieldException, IllegalAccessException {
        Field localField = clazz.getDeclaredField(field);//getDeclaredField获取当前类的所有修饰符的方法，private,public
        localField.setAccessible(true);//设置私有属性可访问
        return  localField.get(obj);
    }



    public static void setField(Object obj,Class<?> clazz,Object value) throws NoSuchFieldException, IllegalAccessException {
        Field localField = clazz.getDeclaredField("dexElements");
        localField.setAccessible(true);
        localField.set(obj, value);
    }

    /**
     * 通过反射获取BaseDexClassLoader对象中的PathList对象
     * @param baseDexClassLoader BaseDexClassLoader对象
     * @return PathList对象
     */

    public static Object getPathList(Object baseDexClassLoader) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        //BaseDexClassLoader的路径： dalvik.system.BaseDexClassLoader
        return getField(baseDexClassLoader,Class.forName("dalvik.system.BaseDexClassLoader"),"pathList");
    }

    /**
     * 通过反射获取BaseDexClassLoader对象中的PathList对象，再获取dexElements对象
     * @param paramObject PathList对象
     * @return dexElements对象
     */
    public static Object getDexElements(Object paramObject) throws NoSuchFieldException, IllegalAccessException {
        return getField(paramObject,paramObject.getClass(),"dexElements");
    }
}
