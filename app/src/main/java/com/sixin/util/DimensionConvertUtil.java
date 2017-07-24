package com.sixin.util;

import android.content.Context;

/**
 * 像素px与密度无关像素dp、字体大小单位sp之间的转换工具类
 * @author 周文涛
 */

public class DimensionConvertUtil {

    /**
     * 像素px转换为密度无关像素dp
     * @param px 像素值
     * */
    public static int px2dp(Context context,int px){

        float scale = context.getResources().getDisplayMetrics().density;
        LogUtil.d(DimensionConvertUtil.class,"scale:"+scale);
        return (int) (px/scale+0.5f);

    }

    /**
     * 像素px转换为字体大小单位sp
     * @param px 像素值
     * */
    public static int px2sp(Context context,int px){

        float scaleFont = context.getResources().getDisplayMetrics().scaledDensity;
        LogUtil.d(DimensionConvertUtil.class,"scaleFont:"+scaleFont);
        return (int) (px/scaleFont+0.5f);

    }

    /**
     * 密度无关像素转换为像素px
     * @param dp 密度无关像素值
     * */
    public static int dp2px(Context context,int dp){

        float scale = context.getResources().getDisplayMetrics().density;
        LogUtil.d(DimensionConvertUtil.class,"scale:"+scale);
        return (int) (dp*scale+0.5f);

    }

    /**
     * 字体大小单位sp转换为像素px
     * */
    public static int sp2px(Context context,int sp){

        float scaleFont = context.getResources().getDisplayMetrics().scaledDensity;
        LogUtil.d(DimensionConvertUtil.class,"scaleFont:"+scaleFont);
        return (int) (sp*scaleFont+0.5f);

    }

}
