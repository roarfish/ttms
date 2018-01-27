package cn.tedu.ttms.attachment.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.attachment.entity.Attachment;
import cn.tedu.ttms.attachment.service.AttachmentService;
import cn.tedu.ttms.common.web.JsonResult;

@Controller
@RequestMapping("attachment")
public class AttachmentController {
	
	@Resource
	private AttachmentService attachmentservice;
	
	@RequestMapping("attachmentUI")
	public String listUI(){
		return "attachment/attachment";
	}
	
	/**
	 * 查询全部附件信息
	 * @return
	 */
	@RequestMapping("doQueryAllAttachment")
	@ResponseBody
	public JsonResult doQueryAllAttachment(){
		return new JsonResult(attachmentservice.queryAllAttachment());
	}
	
	/**
	 * 附件下载
	 * @param id
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("doDownload")
	@ResponseBody
	public byte[] doQueryAttachmentById(Integer id,HttpServletResponse response) throws IOException{
		Attachment a=attachmentservice.queryAttachmentById(id);
		response.setContentType("appliction/octet-stream");
		String fileName=URLEncoder.encode(a.getFileName(), "utf-8");
		response.setHeader("Content-disposition", "attachment;filename="+fileName);
		Path path=Paths.get(a.getFilePath());
		return Files.readAllBytes(path);
	}
	
	/**
	 * 附件上传
	 * @return
	 */
	@RequestMapping("doUpload")
	@ResponseBody
	public JsonResult doUpload(String title,MultipartFile mFile){
		attachmentservice.saveAttachment(title, mFile);
		return new JsonResult();
	}
}
