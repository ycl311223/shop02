package com.ycl.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;

public class MultiPartWrapper extends HttpServletRequestWrapper {
	private Map<String,String[]>params=null;
	private final static String[] allowFile= {"jpg","png","bmp","gif"};
	private final static String PATH="F:/eclipse-workspace2/shop02";
	public MultiPartWrapper(HttpServletRequest request) {
		super(request);
		params=new HashMap<String,String[]>();
		setParams(request);
	}
	/**
	 * 将普通表单控件的参数放到参数域中，将文件上传的空间的参数上传到参数域中，将文件上传的文件内容放到请求域中。此处的参数是指表单控件的名称和值的键值对。
	 * */
	private void setParams(HttpServletRequest request) {
		ByteArrayOutputStream baos=null;
		InputStream is=null;
		
		try {
			boolean isMul=ServletFileUpload.isMultipartContent(request);
			if(isMul) {
				ServletFileUpload upload=new ServletFileUpload();
				FileItemIterator itemIterator = upload.getItemIterator(request);
				while(itemIterator.hasNext()) {
					FileItemStream item = itemIterator.next();
					is=item.openStream();
					//先将普通的表单属性放到自己定义的参数域params中
					if(item.isFormField()) {
						String name=item.getFieldName();
						setFormField(name,is);
					//再上传文件上传的文件
					}else {
						baos=new ByteArrayOutputStream();
						int len=0;
						byte[] buf=new byte[1024];
						while((len=is.read(buf))>0) {
							baos.write(buf, 0, len);
						}
						byte[] array = baos.toByteArray();
						request.setAttribute("fs", array);
						params.put(item.getFieldName(), new String[] {item.getName()});
					}
					
				}
			}else {
				params=super.getParameterMap();
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 普通表单控件的参数放到参数域中的方法
	 * */
	private void setFormField(String name, InputStream is) throws IOException {
		if(params.containsKey(name)) {
			String[] values=params.get(name);
			values=Arrays.copyOf(values, values.length+1);
			values[values.length-1]=Streams.asString(is);
			params.put(name, values);
		}else {
			params.put(name, new String[] {Streams.asString(is)});
		}
	}
	
	@Override
	public String getParameter(String name) {
		String[] values=params.get(name);
		if(values!=null) {
			return values[0];
		}
		return null;
	}
	@Override
	public String[] getParameterValues(String name) {
		String[] values=params.get(name);
		if(values!=null) {
			return values;
		}
		return null;
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		return params;
	}
}
