package com.ycl.util;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static SqlSessionFactory factory;
	static {
		try {
			InputStream in = MyBatisUtil.class.getClassLoader().getResourceAsStream("config.xml");
			factory=new SqlSessionFactoryBuilder().build(in);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static SqlSession getSession() {
		return factory.openSession();
	}
	
	public static void closeSession(SqlSession session) {
		if(session!=null) {
			session.close();
		}
	}
}
