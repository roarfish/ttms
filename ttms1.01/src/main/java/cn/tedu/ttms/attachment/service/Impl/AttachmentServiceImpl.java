package cn.tedu.ttms.attachment.service.Impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.attachment.dao.AttachmentDao;
import cn.tedu.ttms.attachment.entity.Attachment;
import cn.tedu.ttms.attachment.service.AttachmentService;
import cn.tedu.ttms.common.exception.ServiceException;

@Service
public class AttachmentServiceImpl implements AttachmentService {
	
	@Resource
	private AttachmentDao dao;

	/**
	 * ��ѯȫ��������Ϣ
	 */
	@Override
	public List<Attachment> queryAllAttachment() {
		return dao.queryAllAttachment();
	}

	/**
	 * ����ID��ѯ������Ϣ
	 */
	@Override
	public Attachment queryAttachmentById(Integer id) {
		if(id==null){
			throw new ServiceException("������ѡ��һ���ļ����в���");
		}
		Attachment a=dao.queryAttachmentById(id);
		if(a==null){
			throw new ServiceException("û���ҵ���Ӧ���ļ�!");
		}
		return a;
	}

	/**
	 * �����ϴ�
	 */
	@Override
	public void saveAttachment(String title,MultipartFile mFile) {
		//��֤������Ч��
		if(title==null || title.trim().length()==0)
			throw new ServiceException("�����븽������!");
		if(mFile==null)
			throw new ServiceException("��ѡ�񸽼������ϴ�!");
		if(mFile.isEmpty())
			throw new ServiceException("�ϴ���������Ϊ��!");
		
		String fileDisgest=null;
		byte[] buf=null;
		//�Ը�������MD5����
		try {
			buf=mFile.getBytes();
			fileDisgest=DigestUtils.md5DigestAsHex(buf);
			System.out.println("fileDisgest="+fileDisgest);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("�ļ�ժҪ����ʧ��");
		}
		//��֤�����Ƿ��ϴ���
		Attachment a=dao.queryAttachmentByFileDisgest(fileDisgest);
		if(a!=null)
			throw new ServiceException("�������ϴ�,�����ٴ��ϴ�");
		//���������ϴ�·��
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		String dateDir=sdf.format(new Date());
		String baseDir="F:/Java_note/TestFile/";
		File uploadDir=new File(baseDir+dateDir);
		if(!uploadDir.exists()){
			uploadDir.mkdirs();
		}
		//�����µĸ�����
		String srcFileName=mFile.getOriginalFilename();
		String destFileName=UUID.randomUUID().toString()+"."+FilenameUtils.getExtension(srcFileName);
		//�����ϴ����ļ�����
		File file=new File(uploadDir, destFileName);
		//ʵ�ָ������ϴ�
		try {
			//ʵ���Ǹ����ĸ���
			mFile.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("�ļ��ϴ�ʧ��!");
		}
		//��������Ϣд�����ݿ�
		Attachment attach=new Attachment();
		attach.setFileName(mFile.getOriginalFilename());
		attach.setTitle(title);
		attach.setContentType(mFile.getContentType());
		attach.setFileDisgest(fileDisgest);
		attach.setFilePath(file.getAbsolutePath());
		attach.setAthType(1);//����û�õ�
		attach.setBelongId(1);//����û�õ�
		int save=dao.saveAttachment(attach);
		if(save<0){
			throw new ServiceException("�ϴ�ʧ��!");
		}
	}
}
