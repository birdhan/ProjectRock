package com.cloud.base.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

public class ApplicationContextHolder
{
  private ApplicationContext ctx = null;
  private static ApplicationContextHolder holder = null;

  public static ApplicationContextHolder getInstance()
  {
    if (holder == null)
      holder = new ApplicationContextHolder();

    return holder;
  }

  public void setCtx(ApplicationContext ctx)
  {
    this.ctx = ctx;
  }

  public Object getBean(String beanId)
  {
    if (this.ctx == null) {
      String[] fn = {"applicationContext.xml"};
      this.ctx = new ClassPathXmlApplicationContext(fn);
    }
    return this.ctx.getBean(beanId);
  }
  
  public String getHQLDialect()
  {
    LocalSessionFactoryBean dialect = (LocalSessionFactoryBean)this.ctx
      .getBean("&sessionFactory");
    return dialect.getHibernateProperties()
      .getProperty("hibernate.dialect");
  }

  public ApplicationContext getCtx()
  {
    return this.ctx;
  }
}