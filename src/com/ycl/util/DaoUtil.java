package com.ycl.util;

import java.lang.reflect.Method;

import com.ycl.annotation.ShopDi;

public class DaoUtil {
	
	public static void diDao(Object obj) {
		Method[] methods = obj.getClass().getDeclaredMethods();
		for(Method m:methods) {
			if(m.isAnnotationPresent(ShopDi.class)) {
				ShopDi annotation=m.getAnnotation(ShopDi.class);
			}
		}
	}
}
