package com.ycl.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.ycl.util.PropertiesUtil;

public class DaoFactory implements IFactoryDao {
	private static DaoFactory daoFactory=new DaoFactory();
	private DaoFactory() {};
	public static DaoFactory getDaoFactory() {
		return daoFactory;
	}
	private Map<String,Object> daos=new HashMap<String,Object>();
	@Override
	public Object getDao(String name) {
		try {
			Object daoInstance=null;
			if(daos.get(name)==null) {
				Properties propertyIncDao=PropertiesUtil.getPropertyIncDao();
				String daoName=propertyIncDao.getProperty(name);
				Class<?> dao = Class.forName(daoName);
				daoInstance=dao.newInstance();
			}else {
				daoInstance=daos.get(name);
			}
			return daoInstance;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
