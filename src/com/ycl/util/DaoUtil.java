package com.ycl.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.ycl.annotation.ShopDi;
import com.ycl.factory.DaoFactory;

public class DaoUtil {
	
	public static void diDao(Object obj) {
		try {
			Method[] methods = obj.getClass().getDeclaredMethods();
			for(Method m:methods) {
				if(m.isAnnotationPresent(ShopDi.class)) {
					ShopDi annotation=m.getAnnotation(ShopDi.class);
					String an=annotation.value();
					if(an==null && "".equals(an.trim())) {
						an=m.getName().substring(3);
						//获取到set的dao的名字了
						an=an.substring(0, 1).toLowerCase()+an.substring(1);
					}
					Object dao = DaoFactory.getDaoFactory().getDao(an);
					m.invoke(obj, dao);
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
