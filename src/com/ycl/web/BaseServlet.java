package com.ycl.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ycl.annotation.Auth;
import com.ycl.model.User;
import com.ycl.util.DaoUtil;
import com.ycl.util.MultiPartWrapper;
/**
 * 运行逻辑：
 * 	servelt的执行逻辑是一个请求到达servlet，首先是到service方法，在service方法中根据是get或是post,或是什么方法决定去执行doGet,doPost或是什么方法。
 * 
 * 而此处的设计逻辑是，利用了继承（子类没有的方法去父类中找），反射（知道个方法名字或类名就可以调用），在具体的servlet（子类）中不给service方法，而在
 * BaseServlet(父类)中给出service方法，这样到达具体servlet中的请求找不到service方法就会去BaseServlet中调用父类的service方法，注意是用父类
 * 的方法处理自己的事，调用方法的对象还是子类，这样就可以在具体servlet中写方法逻辑，在BaseServlet中的service中进行调用，然后统一进行路由的分配。
 * 
 * 这样做的好处就是将所有的重复的东西都抽取出来放在BaseServlet中写一次就可以让所有的具体servlet都使用，而各个具体servlet的真正不同的也就是他们的业务逻辑，这个放在他们自己中写，这样就
 * 最大化的精简了代码。
 * */
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
		
		try {
			errors.clear();
			req.setAttribute("errors", errors);
			if(ServletFileUpload.isMultipartContent(req)) {
				req=new MultiPartWrapper(req);
			}
			DaoUtil.diDao(this);
			String methodName=req.getParameter("method");
			Method method=this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			int flag=checkAuth(req, method, resp);
			if(flag==1) {
				resp.sendRedirect("user.do?method='loginInput'");
				return;
			}
			if(flag==2) {
				req.setAttribute("errorMsg", "你没有权限进行此项操作！");
				req.getRequestDispatcher("/WEB-INF/inc/error.jsp").forward(req, resp);
				return;
			}
			String path=(String) method.invoke(this,req,resp);
			if(path.startsWith(redirPath)) {
				String rp=path.substring(redirPath.length());
				resp.sendRedirect(rp);
			}else {
				req.getRequestDispatcher("/WEB-INF/"+path).forward(req, resp);
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
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
