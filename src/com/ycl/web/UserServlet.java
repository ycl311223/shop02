package com.ycl.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ycl.annotation.Auth;
import com.ycl.annotation.ShopDi;
import com.ycl.dao.UserDao;
import com.ycl.model.User;


public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDao userDao;
	
	@ShopDi
    public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public UserServlet() {
    }
    @Auth("any")
	public String loginInput(HttpServletRequest req,HttpServletResponse resp) {
	
		return "user/loginInput.jsp";
	}
    @Auth("any")
   	public String toRegesiterJsp(HttpServletRequest req,HttpServletResponse resp) {
   	
   		return "user/regesiter.jsp";
   	}
    @Auth("any")
   	public String regesiter(HttpServletRequest req,HttpServletResponse resp) {
    	String username=req.getParameter("username");
    	String password=req.getParameter("password");
    	String nickname=req.getParameter("nickname");
    	User u=new User();
    	u.setUsername(username);
    	u.setPassword(password);
    	u.setNickname(nickname);
    	u.setType(0);
    	userDao.add(u);
   		return "user/loginInput.jsp";
   	}
    @Auth("any")
    public String login(HttpServletRequest req,HttpServletResponse resp) {
    	String username=req.getParameter("username");
    	String passowrd=req.getParameter("password");
    	HttpSession session = req.getSession();
    	Map<String, String> errors = this.getErrors();
    	User u = (User) userDao.loadByUserName(User.class, username);
    	if(u==null) {
    		errors.put("errorMsg", "你还没有注册请先注册");
    		req.setAttribute("username", username);
    		return "user/loginInput.jsp";
    	}else {
    		String dbpass=u.getPassword();
    		if(!dbpass.equals(passowrd)) {
    			errors.put("errorMsg", "密码输入错误，请重输");
    			req.setAttribute("username", username);
    			
    			return "user/loginInput.jsp";
    		}
    	}
    	session.setAttribute("loginUser",u );
    	return redirectPath("product.do?method=list");
    }
    @Auth("user")
    public String logout(HttpServletRequest req,HttpServletResponse resp) {
    	HttpSession session = req.getSession();
    	session.removeAttribute("loginUser");
    	return "user/loginInput.jsp";
    }
}
