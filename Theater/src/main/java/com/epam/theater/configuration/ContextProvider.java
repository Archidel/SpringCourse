package com.epam.theater.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ContextProvider {
	private static ApplicationContext CONTEXT;

	public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		CONTEXT = applicationContext;
	}

	public static <T> T getBean(Class<T> beanClass) {
		return CONTEXT.getBean(beanClass);
	}

	public static Object getBean(String beanName) {
		return CONTEXT.getBean(beanName);
	}
}
