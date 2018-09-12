package com.ycl.dao;

import org.apache.ibatis.session.SqlSession;

import com.ycl.util.DaoUtil;
import com.ycl.util.MyBatisUtil;

public class BaseDao {
	public BaseDao() {
		//this指代调用当前方法的对象
		DaoUtil.diDao(this);
	}
	
	public void add(Object obj) {
		SqlSession session=null;
		try {
			session=MyBatisUtil.getSession();
			session.insert(obj.getClass().getName()+".add", obj);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			MyBatisUtil.closeSession(session);
		}	
	}
	public void update(Object obj) {
		SqlSession session=null;
		try {
			session=MyBatisUtil.getSession();
			session.update(obj.getClass().getName()+".update", obj);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			MyBatisUtil.closeSession(session);
		}
	}
	public void delete(Class clz,int id) {
		SqlSession session=null;
		try {
			session=MyBatisUtil.getSession();
			session.update(clz.getName()+".delete", id);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			MyBatisUtil.closeSession(session);
		}
	};
	public Object load(Class clz,int id) {
		SqlSession session=null;
		Object object=null;
		try {
			session=MyBatisUtil.getSession();
			object=session.selectOne(clz.getName()+".load", id);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			MyBatisUtil.closeSession(session);
		}
		return object;
	};
}
