package com.ycl.web;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ycl.annotation.Auth;
import com.ycl.model.User;

public class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public final static String redirPath="redirect:";
	/**
	 * 错误处理机制：错误容器，提供子类获取错误的口子，判断错误容器内是否有错的方法。
	 */
	private Map<String,String>errors=new HashMap<String,String>();
	protected Map<String,String> getErrors(){
		return errors;
	}
	protected boolean hasErrors() {
		if(!errors.isEmpty()||errors.size()>0)return true;
		return false;
	}
	/**
	 * 定向机制：重定向的方法，出错后如何定向
	 * */
	protected String redirectPath(String path) {
		return redirPath+path;
	}
	protected String handleException(Exception e,HttpServletRequest req) {
		req.setAttribute("errorMsg", e.getMessage());
		return "inc/error.jsp";
	}
	
	/**
	 * 权限验证
	 * */
	public int checkAuth(HttpServletRequest req,Method m,HttpServletResponse resp) {
		User loginUser=(User) req.getSession().getAttribute("loginUser");
		//超管具有所有权限，无条件放行
		if(loginUser!=null &&loginUser.getType()==1) {
			return 0;
		}
		//方法上指定了权限的
		if(m.isAnnotationPresent(Auth.class)) {
			Auth a=m.getAnnotation(Auth.class);
			String v=a.value();
			if(v.equals("any")) {
				return 0;
			}
			if(v.equals("user")) {
				if(loginUser==null) {
					return 1;
				}else {
					return 0;
				}
			}
		//没有指定权限的（也就是只有超管可以访问）
		}else {
			if(loginUser==null) {
				return 1;
			}
			if(loginUser.getType()!=1) {
				return 2;
			}
		}
		return 0;
	}
	/**
	 * 真正的服务方法
	 * */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		errors.clear();
		req.setAttribute("errors", errors);
		if()
		
	}

}
