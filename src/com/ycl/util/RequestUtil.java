package com.ycl.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FilenameUtils;

import com.ycl.annotation.ValidateForm;
import com.ycl.annotation.ValidateType;

public class RequestUtil {
	public final static String PATH="F:/eclipse-workspace2/shop02";
	public final static String[] allowFile= {"jpg","png","bmp","gif"};
	
	public static void uploadFile(String fname,String fieldName,byte[] fs,HttpServletRequest req) throws IOException  {
		FileOutputStream fos=null;
		try {
			if (fs.length > 0) {
				//获取文件名，包含后缀,应该是传进来的是带路径的，
				String fn = FilenameUtils.getName(fname);
				String ext = FilenameUtils.getExtension(fname);
				boolean b = checkFile(ext);
				if (b) {
					fos = new FileOutputStream(PATH + "/img/" + fn);
					fos.write(fs, 0, fs.length);
				} else {
					Map<String, String> errors = (Map<String, String>) req.getAttribute("errors");
					errors.put("errorMsg", "图片格式必须为jpg,png,bmp,gif之一");
				}
			} 
		} finally {
			if(fos!=null)fos.close();
		}
	}
	
	public static boolean validate(Class clz,HttpServletRequest req) {
		Field[] fs=clz.getDeclaredFields();
		boolean isValidate=true;
		Map<String,String>errors=(Map<String, String>) req.getAttribute("errors");
		for(Field f:fs) {
			if(f.isAnnotationPresent(ValidateForm.class)) {
				ValidateForm vf=f.getAnnotation(ValidateForm.class);
				ValidateType vt=vf.type();
				if(vt==ValidateType.NotNull) {
					boolean b=validateNoteNull(f,req);
					if(!b) {
						errors.put(f.getName(), vf.errorMsg());
						return false;
					}
				}
				if(vt==ValidateType.Length) {
					boolean b=validateLength(f,req);
					if(!b) {
						errors.put(f.getName(), vf.errorMsg());
						return false;
					}
				}
				if(vt==ValidateType.Number) {
					boolean b=vlidateNumber(f,req);
					if(!b) {
						errors.put(f.getName(), vf.errorMsg());
						return false;
					}
				}
			}
		}
		return true;
	}

	private static boolean vlidateNumber(Field f, HttpServletRequest req) {
		String fname=f.getName();
		try {
			Double.parseDouble(req.getParameter(fname));
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	private static boolean validateLength(Field f, HttpServletRequest req) {
		String fname=f.getName();
		String fvalue=req.getParameter(fname);
		if(fvalue==null||"".equals(fvalue)) {
			return false;
		}
		if(fvalue.length()<6) {
			return false;
		}
		return true;
	}

	private static boolean validateNoteNull(Field f, HttpServletRequest req) {
		String fname=f.getName();
		//表示表单中没有要验证的值
		if(!req.getParameterMap().containsKey(fname)) {
			return true;
		}
		String fvalue=req.getParameter(fname);
		if(fvalue==null || "".equals(fvalue)) {
			return false;
		}
		return true;
	}

	private static boolean checkFile(String ext) {
		for(String s:allowFile) {
			if(s.equals(ext)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 根据请求域中的参数及传进来的Class生成对应的对象
	 * */
	public static Object createObjFromParams(Class clz,HttpServletRequest req){
		Map<String,String[]> params=req.getParameterMap();
		Set<String> keySet = params.keySet();
		Object o=null;
		try {
			o=clz.newInstance();
			for(String k:keySet) {
				String[] v=params.get(k);
				if(v.length>1) {
					BeanUtils.copyProperty(o, k, v);
				}else {
					BeanUtils.copyProperty(o, k, v[0]);
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return o;
	}
	
}
