package com.ycl.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	private static Properties propertyIncDao;
	public static Properties getPropertyIncDao() {
		try {
			if(propertyIncDao==null) {
				propertyIncDao=new Properties();
				InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("dao.properties");
				propertyIncDao.load(in);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return propertyIncDao;
	}
}
