package cn.tedu.ttms.attachment.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.attachment.entity.Attachment;

public interface AttachmentService {
	
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
	void saveAttachment(String title,MultipartFile mFile);
}
