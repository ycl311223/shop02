package com.ycl.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.ycl.model.SystemContext;
import com.ycl.util.RequestUtil;

@WebFilter("/SystemContextFilter")
public class SystemContextFilter implements Filter {

    public SystemContextFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		try {
			int pageOffset=0;
			int pageSize=15;
			String sort=request.getParameter("sort");
			String order=request.getParameter("order");
			try {
				pageOffset=Integer.parseInt(request.getParameter("pager.offset"));
			} catch (NumberFormatException e) {
	
			}
			SystemContext.setOrder(order);
			SystemContext.setSort(sort);
			SystemContext.setPageOffset(pageOffset);
			SystemContext.setPageSize(pageSize);
			SystemContext.setRealpath(RequestUtil.PATH);
			
			chain.doFilter(request, response);
		} finally {
			SystemContext.removeOrder();
			SystemContext.removeSort();
			SystemContext.removePageSize();
			SystemContext.removePageOffset();
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
