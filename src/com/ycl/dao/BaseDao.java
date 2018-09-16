package com.ycl.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ycl.model.Pager;
import com.ycl.model.SystemContext;
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
	public Object loadByUserName(Class clz,String name) {
		SqlSession session=null;
		Object obj=null;
		try {
			session=MyBatisUtil.getSession();
			obj=session.selectOne(clz.getName()+".loadByName", name);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			MyBatisUtil.closeSession(session);
		}
		return obj;
	}
	
	public List list(Class clz,Map<String,Object>params) {
		List list=null;
		SqlSession session=null;
		try {
			session=MyBatisUtil.getSession();
			list=session.selectList(clz.getName()+".list", params);
			session.commit();
		}catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			MyBatisUtil.closeSession(session);
		}
		return list;
	}
	
	public Pager find(Class clz,Map<String,Object>params) {
		int pageSize=SystemContext.getPageSize();
		int pageOffset = SystemContext.getPageOffset();
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();
		SqlSession session=null;
		Pager pager=null;
		
		try {
			session=MyBatisUtil.getSession();
			if(params==null) params=new HashMap<String,Object>();
			params.put("pageSize", pageSize);
			params.put("pageOffset", pageOffset);
			params.put("order", order);
			params.put("sort", sort);
			List<Object> list = session.selectList(clz.getName()+".find", params);
			pager=new Pager();
			pager.setDatas(list);
			pager.setPageOffset(pageOffset);
			pager.setPageSize(pageSize);
			int totalRecord=session.selectOne(clz.getName()+".count", params);
			pager.setTotalRecord(totalRecord);
			
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			MyBatisUtil.closeSession(session);
		}
		return pager;
	}
}
