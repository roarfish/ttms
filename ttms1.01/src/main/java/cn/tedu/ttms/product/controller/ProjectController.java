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
 * ҳ����Ʋ�
 * @author zhoup
 *
 */
@Controller
@RequestMapping("/project/")
public class ProjectController {
	/**
	 * ע��ʵϰ����
	 */
	@Resource
	private ProjectService projectservice;
	/**
	 * ��ҳ��
	 * @return
	 */
	@RequestMapping("listUI")
	public String listUI(){
		return "product/project_list";
	}
	/**
	 * �����༭ҳ��
	 * @return
	 */
	@RequestMapping("editUI")
	public String editUI(){
		return "product/project_edit";
	}
	
	/**
	 * ��ѯȫ��Project��Ϣ
	 * @return
	 */
	@RequestMapping("doQueryAllProject")
	@ResponseBody
	public JsonResult doQueryAllProject(){
		List<Object> list=projectservice.queryAllProject();
		return new JsonResult(list);
	}
	
	/**
	 * ��ҳ��ѯProject��Ϣ
	 * @param pageobject ��װ�ķ�ҳ����
	 * @return
	 */
	@RequestMapping("doQueryPeojectByPage")
	@ResponseBody
	public JsonResult doQueryProjectByPage(String name,String valid,int pageCurrent){
		Map<String,Object> map=projectservice.queryProjectByPage(name,valid,pageCurrent);
		return new JsonResult(map);
	}
	
	/**
	 * ������Ŀ
	 * @param project ��װ��������Ŀ��Ϣ
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
	 * ������Ŀ
	 * @param project ��װ���޸���Ŀ��Ϣ
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
	 * ����id��ѯ��Ŀ��Ϣ
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
	 * �޸���Ŀ����״̬
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
