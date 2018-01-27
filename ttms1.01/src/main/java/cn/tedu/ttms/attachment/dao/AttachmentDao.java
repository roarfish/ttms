package cn.tedu.ttms.attachment.dao;

import java.util.List;

import cn.tedu.ttms.attachment.entity.Attachment;

public interface AttachmentDao {
	
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
	int saveAttachment(Attachment attachment);
	
	/**
	 * ���ݸ���ժҪ��ѯ������Ϣ
	 * @param fileDisgest
	 * @return
	 */
	Attachment queryAttachmentByFileDisgest(String fileDisgest);
}
