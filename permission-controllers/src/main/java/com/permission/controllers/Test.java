package com.permission.controllers;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.web.bind.annotation.RequestMapping;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class<?> clazz = Class.forName("com.permission.controllers.HomeController");
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			Boolean isAnnotation= method.isAnnotationPresent(RequestMapping.class);
			if (isAnnotation) {			
				System.out.println(method.getName());
				Annotation p = method.getAnnotation(RequestMapping.class); 
                Method m = p.getClass().getDeclaredMethod("value", null);
                String[] strs = (String[])m.invoke(p, null);
                System.out.println(strs[0]);
			}

		}
	}
}
