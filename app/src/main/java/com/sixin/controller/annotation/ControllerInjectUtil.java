package com.sixin.controller.annotation;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ControllerInjectUtil {
	
	
	
	/**
     * 初始化指定View中的注入属性
     * 可用于Fragment内使用InjectView<p>
     * 示例：<p>
     * 在onCreateView中:
     * <pre>
     * public View onCreateView(LayoutInflater inflater, ViewGroup container,
     *      Bundle savedInstanceState) {
     *  View viewRoot = inflater.inflate(R.layout.map_frame, container, false);
     *  ControllerInjectUtil.initInjectedView(this,viewRoot);
     * }
     * </pre>
     * @param sourceView
     */
	public static void initInjectedView(Object injectedSource,View sourceView){

		
		Field[] fields = injectedSource.getClass().getDeclaredFields();
		if(fields!=null && fields.length>0){
			for(Field field : fields){
				ViewInject viewInject = field.getAnnotation(ViewInject.class);
				if(viewInject!=null){
					int viewId = viewInject.id();
					try {
						field.setAccessible(true);
						field.set(injectedSource,sourceView.findViewById(viewId));
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					String clickMethod = viewInject.click();
					if(!TextUtils.isEmpty(clickMethod))
						setViewClickListener(injectedSource,field,clickMethod);
					
					String longClickMethod = viewInject.longClick();
					if(!TextUtils.isEmpty(longClickMethod))
						setViewLongClickListener(injectedSource,field,longClickMethod);
					
					String itemClickMethod = viewInject.itemClick();
					if(!TextUtils.isEmpty(itemClickMethod))
						setItemClickListener(injectedSource,field,itemClickMethod);
					
					String itemLongClickMethod = viewInject.itemLongClick();
					if(!TextUtils.isEmpty(itemLongClickMethod))
						setItemLongClickListener(injectedSource,field,itemLongClickMethod);
					
					Select select = viewInject.select();
					if(!TextUtils.isEmpty(select.selected()))
						setViewSelectListener(injectedSource,field,select.selected(),select.noSelected());
					
				}
			}
		}
		
		//获取方法,注入方法
		Method[] methods = injectedSource.getClass().getDeclaredMethods();
		if(methods!=null && methods.length>0){
			for(Method method:methods){
				ViewEvent event = method.getAnnotation(ViewEvent.class);
				if(event!=null){
					int viewId = event.id();
					method.setAccessible(true);
					View view = sourceView.findViewById(viewId);
					EventType type = event.type();
					setViewEvent(injectedSource, view, method, type);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param injectedSource
	 * @param sourceActivity
	 */
	public static void initInjectedRootLayout(Object injectedSource,Activity sourceActivity){
		RootLayout layoutInject = injectedSource.getClass().getAnnotation(RootLayout.class);
		if(layoutInject!=null){
			int layoutId = layoutInject.layout();
			if(layoutId!=0){
				sourceActivity.setContentView(layoutId);
			}
		}
	}
	
	public static void setViewEvent(Object injectedSource,View obj,Method method,EventType type){
		if(type == EventType.CLICK){
			try{
				if(obj instanceof View){
					((View)obj).setOnClickListener(new EventListener(injectedSource).click(method.getName()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		if(type == EventType.ITEM_CLICK){
			try {
				if(obj instanceof AbsListView){
					((AbsListView)obj).setOnItemClickListener(new EventListener(injectedSource).itemClick(method.getName()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(type == EventType.ITEM_LONG_CLICK){
			try {
				if(obj instanceof AbsListView){
					((AbsListView)obj).setOnItemLongClickListener(new EventListener(injectedSource).itemLongClick(method.getName()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(type == EventType.ITEM_SELECT){
			try {
				if(obj instanceof View){
					((AbsListView)obj).setOnItemSelectedListener(new EventListener(injectedSource).select(method.getName()).noSelect(method.getName()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(type == EventType.LONG_CLICK){
			try {
				if(obj instanceof View){
					((View)obj).setOnLongClickListener(new EventListener(injectedSource).longClick(method.getName()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(type == EventType.TOUCH){
			try {
				if(obj instanceof View){
					((View)obj).setOnTouchListener(new EventListener(injectedSource).touch(method.getName()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	private static void setViewClickListener(Object injectedSource,Field field,String clickMethod){
		try {
			Object obj = field.get(injectedSource);
			if(obj instanceof View){
				((View)obj).setOnClickListener(new EventListener(injectedSource).click(clickMethod));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void setViewLongClickListener(Object injectedSource,Field field,String clickMethod){
		try {
			Object obj = field.get(injectedSource);
			if(obj instanceof View){
				((View)obj).setOnLongClickListener(new EventListener(injectedSource).longClick(clickMethod));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void setItemClickListener(Object injectedSource,Field field,String itemClickMethod){
		try {
			Object obj = field.get(injectedSource);
			if(obj instanceof AbsListView){
				((AbsListView)obj).setOnItemClickListener(new EventListener(injectedSource).itemClick(itemClickMethod));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void setItemLongClickListener(Object injectedSource,Field field,String itemClickMethod){
		try {
			Object obj = field.get(injectedSource);
			if(obj instanceof AbsListView){
				((AbsListView)obj).setOnItemLongClickListener(new EventListener(injectedSource).itemLongClick(itemClickMethod));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void setViewSelectListener(Object injectedSource,Field field,String select,String noSelect){
		try {
			Object obj = field.get(injectedSource);
			if(obj instanceof View){
				((AbsListView)obj).setOnItemSelectedListener(new EventListener(injectedSource).select(select).noSelect(noSelect));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
