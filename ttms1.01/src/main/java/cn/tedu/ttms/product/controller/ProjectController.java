package cn.tedu.ttms.product.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.service.ProjectService;
/**
 * 页面控制层
 * @author zhoup
 *
 */
@Controller
@RequestMapping("/project/")
public class ProjectController {
	/**
	 * 注入实习方法
	 */
	@Resource
	private ProjectService projectservice;
	/**
	 * 主页面
	 * @return
	 */
	@RequestMapping("listUI")
	public String listUI(){
		return "product/project_list";
	}
	/**
	 * 弹出编辑页面
	 * @return
	 */
	@RequestMapping("editUI")
	public String editUI(){
		return "product/project_edit";
	}
	
	/**
	 * 查询全部Project信息
	 * @return
	 */
	@RequestMapping("doQueryAllProject")
	@ResponseBody
	public JsonResult doQueryAllProject(){
		List<Object> list=projectservice.queryAllProject();
		return new JsonResult(list);
	}
	
	/**
	 * 分页查询Project信息
	 * @param pageobject 封装的分页对象
	 * @return
	 */
	@RequestMapping("doQueryPeojectByPage")
	@ResponseBody
	public JsonResult doQueryProjectByPage(String name,String valid,int pageCurrent){
		Map<String,Object> map=projectservice.queryProjectByPage(name,valid,pageCurrent);
		return new JsonResult(map);
	}
	
	/**
	 * 新增项目
	 * @param project 封装的新增项目信息
	 * @return
	 */
	@RequestMapping("doAddProject")
	@ResponseBody
	public JsonResult doAddProject(Project project){
		System.out.println(y);
		y++;
		System.out.println(y);
		projectservice.addProject(project);
		return new JsonResult();
	}
	int x=0;
	int y=0;
	/**
	 * 更新项目
	 * @param project 封装的修改项目信息
	 * @return
	 */
	@RequestMapping("doUpdateProject")
	@ResponseBody
	public JsonResult doUpdateProject(Project project){
		System.out.println(x);
		x++;
		System.out.println(x);
		projectservice.updateProject(project);
		return new JsonResult();
	}
	/**
	 * 根据id查询项目信息
	 * @param id
	 * @return
	 */
	@RequestMapping("doGetProjectById")
	@ResponseBody
	public JsonResult doGetProjectById(int id){
		Project project=projectservice.queryProjectById(id);
		return new JsonResult(project);
	}
	/**
	 * 修改项目启用状态
	 * @return
	 */
	@RequestMapping("doSetValid")
	@ResponseBody
	public JsonResult doSetValid(int valid,String ids){
		String[] id=ids.split(",");
		for(int i=1;i<id.length;i++){
			projectservice.updateProject_valid(valid, id[i]);
		}
		return new JsonResult();
	}
}
