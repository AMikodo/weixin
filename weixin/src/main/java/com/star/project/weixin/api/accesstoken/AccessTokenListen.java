package com.star.project.weixin.api.accesstoken;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AccessTokenListen implements ServletContextListener{
	private static final Logger log=LoggerFactory.getLogger(AccessTokenListen.class);
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("项目启动了");
		new AccessTokenThread().start();
		
	}

}
