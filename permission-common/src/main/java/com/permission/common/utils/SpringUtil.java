package com.permission.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtil implements ApplicationContextAware {
	
    private static ApplicationContext applicationContext;
    
    public void setApplicationContext(ApplicationContext context)
        throws BeansException {
    	SpringUtil.applicationContext = context;
    }
    
    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }
}