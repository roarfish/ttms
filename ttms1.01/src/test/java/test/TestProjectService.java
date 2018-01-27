package test;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.entity.Team;

public class TestProjectService extends TestBase{

	@Test
	public void testQueryALl(){
		List<Object> list=projectservice.queryAllProject();
		System.out.println(list);
	}
	
	@Test
	public void testQueryByPage(){
		PageObject page=new PageObject();
		page.setStartIndex(0);
		String name="泸州";
		String valid="1";
		int pageCurrent=0;
		Map<String,Object> list=projectservice.queryProjectByPage(name,valid,pageCurrent);
		System.out.println(list);
	}
	
	@Test
	public void testAddProject(){
		Project project=projectservice.queryProjectById(1);
		System.out.println(project);
		project.setName("方山一日游");
		projectservice.addProject(project);
	}
	
	@Test
	public void testUpdateProject(){
		Project project=projectservice.queryProjectById(4);
		project.setName("中国七日游");
		projectservice.updateProject(project);
	}
	
	@Test
	public void testQueryAllTeams(){
		List<Team> list=teamservice.queryAllTeam();
		System.out.println(list);
	}
	
	@Test 
	public void testQueryForTree(){
		List<Map<String,Object>> list=productservice.findObjects();
		System.out.println(list.get(0).get("name"));
	}
}
