package cn.tedu.ttms.product.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 所封装团的项目信息
 * @author zhoup
 *
 */
public class Team implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7361107991130539917L;
	//团目id
	private Integer id;
	//团名
	private String name;
	//所属项目id
	private Integer projectId;
	//所属项目名称
	private String projectName;
	//启用状态
	private Integer valid;
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	//详细描述
	private String note;
	//创建时间
	private Date createdTime;
	//修改时间
	private Date modifiedTime;
	//创建者
	private String createdUser;
	//修改者
	private String modifiedUser;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", projectId=" + projectId + ", projectName=" + projectName
				+ ", valid=" + valid + ", note=" + note + ", createdTime=" + createdTime + ", modifiedTime="
				+ modifiedTime + ", createdUser=" + createdUser + ", modifiedUser=" + modifiedUser + "]";
	}
	
}
