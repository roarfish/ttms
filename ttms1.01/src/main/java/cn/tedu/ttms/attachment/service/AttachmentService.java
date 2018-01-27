package cn.tedu.ttms.attachment.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.attachment.entity.Attachment;

public interface AttachmentService {
	
	/**
	 * ��ѯȫ��������Ϣ
	 * @return
	 */
	List<Attachment> queryAllAttachment();
	
	/**
	 * ����ID��ѯ������Ϣ
	 * @return
	 */
	Attachment queryAttachmentById(Integer id);
	
	/**
	 * �����ϴ�
	 * @param attachment
	 * @return
	 */
	void saveAttachment(String title,MultipartFile mFile);
}
