package com.sixin.util;

import android.util.Log;

/**
 * 日志工具类，通过修改该类中DEBUG的值
 * ，来确定是否需要打印日志。DEBUG = true，
 * 打印日志，否则不打印日志
 *
 * @author 周文涛
 */

public class LogUtil {

    /**
     * 用于控制日志是否输出的常量
     * */
    private static final  boolean DEBUG = true;

    /**
     * 打印debug日志的方法
     *@param clazz 打印的日志所在的类的类类型对象
     *@param msg 需要打印的日志内容
     * */
    public static  void d(Class clazz,String msg){
        if(DEBUG){
            Log.d(clazz.getName(),msg);
        }
    }

    /**
     * 打印error日志的方法
     *@param clazz 打印的日志所在的类的类类型对象
     *@param msg 需要打印的日志内容
     * */
    public static void e(Class clazz,String msg){
        if(DEBUG){
            Log.e(clazz.getName(),msg);
        }
    }

}
