package cn.tedu.ttms.attachment.dao;

import java.util.List;

import cn.tedu.ttms.attachment.entity.Attachment;

public interface AttachmentDao {
	
	/**
	 * 查询全部附件信息
	 * @return
	 */
	List<Attachment> queryAllAttachment();
	
	/**
	 * 根据ID查询附件信息
	 * @return
	 */
	Attachment queryAttachmentById(Integer id);
	
	/**
	 * 附件上传
	 * @param attachment
	 * @return
	 */
	int saveAttachment(Attachment attachment);
	
	/**
	 * 根据附件摘要查询附件信息
	 * @param fileDisgest
	 * @return
	 */
	Attachment queryAttachmentByFileDisgest(String fileDisgest);
}
