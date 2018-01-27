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
	 * 查询全部附件信息
	 */
	@Override
	public List<Attachment> queryAllAttachment() {
		return dao.queryAllAttachment();
	}

	/**
	 * 根据ID查询附件信息
	 */
	@Override
	public Attachment queryAttachmentById(Integer id) {
		if(id==null){
			throw new ServiceException("请至少选择一个文件进行操作");
		}
		Attachment a=dao.queryAttachmentById(id);
		if(a==null){
			throw new ServiceException("没有找到对应的文件!");
		}
		return a;
	}

	/**
	 * 附件上传
	 */
	@Override
	public void saveAttachment(String title,MultipartFile mFile) {
		//验证参数有效性
		if(title==null || title.trim().length()==0)
			throw new ServiceException("请输入附件名称!");
		if(mFile==null)
			throw new ServiceException("请选择附件进行上传!");
		if(mFile.isEmpty())
			throw new ServiceException("上传附件不能为空!");
		
		String fileDisgest=null;
		byte[] buf=null;
		//对附件进行MD5加密
		try {
			buf=mFile.getBytes();
			fileDisgest=DigestUtils.md5DigestAsHex(buf);
			System.out.println("fileDisgest="+fileDisgest);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("文件摘要创建失败");
		}
		//验证附件是否上传过
		Attachment a=dao.queryAttachmentByFileDisgest(fileDisgest);
		if(a!=null)
			throw new ServiceException("附件已上传,不能再次上传");
		//构建附件上传路径
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		String dateDir=sdf.format(new Date());
		String baseDir="F:/Java_note/TestFile/";
		File uploadDir=new File(baseDir+dateDir);
		if(!uploadDir.exists()){
			uploadDir.mkdirs();
		}
		//构建新的附件名
		String srcFileName=mFile.getOriginalFilename();
		String destFileName=UUID.randomUUID().toString()+"."+FilenameUtils.getExtension(srcFileName);
		//创建上传的文件对象
		File file=new File(uploadDir, destFileName);
		//实现附件的上传
		try {
			//实质是附件的复制
			mFile.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("文件上传失败!");
		}
		//将附件信息写入数据库
		Attachment attach=new Attachment();
		attach.setFileName(mFile.getOriginalFilename());
		attach.setTitle(title);
		attach.setContentType(mFile.getContentType());
		attach.setFileDisgest(fileDisgest);
		attach.setFilePath(file.getAbsolutePath());
		attach.setAthType(1);//暂且没用到
		attach.setBelongId(1);//暂且没用到
		int save=dao.saveAttachment(attach);
		if(save<0){
			throw new ServiceException("上传失败!");
		}
	}
}
