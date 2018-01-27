package cn.tedu.ttms.product.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * ����װ�ŵ���Ŀ��Ϣ
 * @author zhoup
 *
 */
public class Team implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7361107991130539917L;
	//��Ŀid
	private Integer id;
	//����
	private String name;
	//������Ŀid
	private Integer projectId;
	//������Ŀ����
	private String projectName;
	//����״̬
	private Integer valid;
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	//��ϸ����
	private String note;
	//����ʱ��
	private Date createdTime;
	//�޸�ʱ��
	private Date modifiedTime;
	//������
	private String createdUser;
	//�޸���
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
