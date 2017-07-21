package com.sixin.manager;

import com.sixin.base.BaseActivity;

import java.util.ArrayList;

/**
 * 活动的管理类，用于统一管理活动
 * @author 周文涛
 */

public class ActivityManager {

    private static ActivityManager mActivityManager;

    private ArrayList<BaseActivity> mList = new ArrayList<>();

    private ActivityManager(){

    }

    public static ActivityManager instance(){

        if(mActivityManager == null){
            synchronized (ActivityManager.class){
                if(mActivityManager == null){
                    mActivityManager = new ActivityManager();
                }
            }
        }
        return mActivityManager;

    }

    /**
     * 往栈中添加某个活动
     * @param activity 需要添加到栈中的活动
     * */
    public void addActivity(BaseActivity activity){
        mList.add(activity);
    }

    /**
     * 从栈中移除某个活动
     * @param activity 需要添从栈中移除的activity
     * */
    public void removeActivity(BaseActivity activity){
        mList.remove(activity);
    }

    /**
     * 销毁当前栈中所有的活动
     * */
    public void finishAllActivity(){
        for(int i = 0; i < mList.size(); i++ ){
            mList.get(i).finish();
        }
    }

}
