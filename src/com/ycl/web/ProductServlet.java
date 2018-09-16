package com.ycl.web;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ycl.annotation.Auth;
import com.ycl.annotation.ShopDi;
import com.ycl.dao.CategoryDao;
import com.ycl.model.Category;
import com.ycl.model.User;

/**
 * Servlet implementation class ProductServlet
 */

public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDao categoryDao;
	@ShopDi
    public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	public ProductServlet() {
        // TODO Auto-generated constructor stub
    }
    @Auth("user")
    public String list(HttpServletRequest req,HttpServletResponse resp) {
    	
    	return "product/list.jsp";
    }
    @Auth("user")
    public String addInput(HttpServletRequest req,HttpServletResponse resp) {
    	
    	List list = categoryDao.list(Category.class, null);
    	req.setAttribute("cs", list);

    	
    	return "product/addInput.jsp";
    }
    @Auth("user")
    public String add(HttpServletRequest req,HttpServletResponse resp) {
    	
    	String name=req.getParameter("name");
    	String price=req.getParameter("price");
    	String stock=req.getParameter("stock");
    	String cid=req.getParameter("cid");
    	String img=req.getParameter("img");
    	String intro=req.getParameter("intro");
    	
    	System.out.println(name);
    	System.out.println(price);
    	System.out.println(stock);
    	System.out.println(cid);
    	System.out.println(img);
    	System.out.println(intro);
    	return redirectPath("product.do?method=list");
    }
}
