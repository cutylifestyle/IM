package com.sixin.base;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixin.common.Constants;
import com.sixin.controller.annotation.ControllerInjectUtil;
import com.sixin.util.LogUtil;

/**
 * @author 周文涛
 */
public abstract class BaseFragment extends Fragment {


    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
        View view = inflater.inflate(setLayoutId(), container, false);
        ControllerInjectUtil.initInjectedView(this, view);
        return view;
    }

    /**
     * 该方法子类必须实现，返回值为fragment的布局id;
     * */
    protected abstract int setLayoutId();

    /**
     * 通过静态方法创建fragment对象
     * @param cls 继承自Fragment的子类的类类型对象
     * */
    public static BaseFragment newsInstance(Class<? extends Fragment> cls){

        BaseFragment fragment = null;
        try {
            fragment = (BaseFragment) cls.newInstance();
        } catch (java.lang.InstantiationException | IllegalAccessException e) {
            LogUtil.e(BaseFragment.class,e.getMessage());
        }
        return fragment;

    }

    /**
     * 通过静态方法创建fragment对象
     * @param cls 继承自Fragment的子类的类类型对象
     * @param title 传递简单的字符串数据给碎片
     * */
    public static BaseFragment newsInstance(Class<? extends Fragment> cls,String title){

        BaseFragment fragment = null;
        try {
            fragment = (BaseFragment) cls.newInstance();
            Bundle bundle = new Bundle();
            bundle.putString(Constants.ArgumentsKey,title);
            fragment.setArguments(bundle);
        } catch (IllegalAccessException | java.lang.InstantiationException e) {
            LogUtil.e(BaseFragment.class,e.getMessage());
        }
        return fragment;

    }

    /**
     * 通过静态方法创建fragment对象
     * @param cls 继承自Fragment的子类的类类型对象
     * @param object 传递序列化对象给碎片
     * */
    public static BaseFragment newsInstance(Class<? extends Fragment> cls,Parcelable object ){

        BaseFragment fragment = null;
        try {
            fragment = (BaseFragment) cls.newInstance();
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.ArgumentsObjectKey,object);
            fragment.setArguments(bundle);
        } catch (IllegalAccessException | java.lang.InstantiationException e) {
            LogUtil.e(BaseFragment.class,e.getMessage());
        }
        return  fragment;

    }

    /**
     * 该方法用于活动之间的跳转
     * @param cls 需要跳转的目标
     * @param isClose 跳转到目标界面是否销毁当前页面
     * */
    public void moveTo(Class cls,boolean isClose){

        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        getActivity().startActivity(intent);

        closeCurrentPage(isClose);

    }

    /**
     * 该方法用于活动之间的跳转
     * @param cls 需要跳转的目标
     * @param isClose 跳转到目标界面是否销毁当前页面
     * @param tag 额外数据的名称
     * @param bundle 承载数据的对象
     * */
    public void moveTo(Class<?> cls,boolean isClose,String tag,Bundle bundle){

        Intent intent = new Intent();
        intent.putExtra(tag, bundle);
        intent.setClass(getActivity(), cls);
        getActivity().startActivity(intent);

        closeCurrentPage(isClose);
    }

    /**
     *关闭当前页面
     * @param isClose 跳转到目标界面是否销毁当前页面
     * */
    private void closeCurrentPage(boolean isClose){
        if(isClose){
            getActivity().finish();
        }
    }

}
