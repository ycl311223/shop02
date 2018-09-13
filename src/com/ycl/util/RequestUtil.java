package com.ycl.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
					boolean b=validate
				}
			}
		}
	}

	private static boolean checkFile(String ext) {
		for(String s:allowFile) {
			if(s.equals(ext)) {
				return true;
			}
		}
		return false;
	}
	
}
