package com.jason798.comm;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring tool
 * after spring initialed,can be use
 */
public class ApplicationContextHepler implements ApplicationContextAware {

	private static final ApplicationContext[] applicationContext = new ApplicationContext[1];
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ApplicationContextHepler.applicationContext[0] = applicationContext;
	}

	public static <T> T getBean(String name, Class<T> clazz) throws Exception{
		return applicationContext[0].getBean(name, clazz);
	}
	
	public static Object getBeanByName(String name) throws Exception{
		return applicationContext[0].getBean(name);
	}

}
