package test;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.ttms.product.service.ProductTypeService;
import cn.tedu.ttms.product.service.ProjectService;
import cn.tedu.ttms.product.service.TeamService;
import cn.tedu.ttms.product.service.impl.ProductTypeServiceImpl;
import cn.tedu.ttms.product.service.impl.ProjectServiceImpl;
import cn.tedu.ttms.product.service.impl.TeamServiceImpl;

public class TestBase {
	protected ClassPathXmlApplicationContext ctx;
	protected ProjectService projectservice;
	protected TeamService teamservice;
	protected ProductTypeService productservice;
	
	@Before
	public void init(){
		ctx=new ClassPathXmlApplicationContext("spring-mvc.xml","spring-mybatis.xml");
		projectservice=ctx.getBean("projectServiceImpl", ProjectServiceImpl.class);
		teamservice=ctx.getBean("teamServiceImpl", TeamServiceImpl.class);
		productservice=ctx.getBean("productTypeServiceImpl", ProductTypeServiceImpl.class);
	}
	@After
	public void after(){
		ctx.close();
	}
}
