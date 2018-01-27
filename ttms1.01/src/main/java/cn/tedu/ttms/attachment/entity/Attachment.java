package cn.tedu.ttms.attachment.entity;

import java.io.Serializable;
import java.util.Date;

public class Attachment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3051561709627104625L;
	private Integer id;
	private String title;
	private String fileName;
	private String contentType;
	private String filePath;
	private String fileDisgest;
	private Integer athType;
	private Integer belongId;
	private String createdUser;
	private Date createdTime;
	private String modifiedUser;
	private Date modifiedTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileDisgest() {
		return fileDisgest;
	}
	public void setFileDisgest(String fileDisgest) {
		this.fileDisgest = fileDisgest;
	}
	public Integer getAthType() {
		return athType;
	}
	public void setAthType(Integer athType) {
		this.athType = athType;
	}
	public Integer getBelongId() {
		return belongId;
	}
	public void setBelongId(Integer belongId) {
		this.belongId = belongId;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	
	
}
