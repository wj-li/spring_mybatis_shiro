package com.demo.controller;

import java.awt.image.BufferedImage;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * CommonController.java
 *
 * @author liwenjian
 * @date 2017年7月19日 上午11:50:59
 * @version 1.0.0
 */
@Controller
@RequestMapping("/common")
public class CommonController extends BaseController {
	private Log log = LogFactory.getLog(CommonController.class);

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @param response
	 * @param files
	 *            上传的文件（可多文件）
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody Object upload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam MultipartFile[] files) {
		try {
			return super.success();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("文件公共上传方法异常");
		}
		return super.error();
	}

	/**
	 * 调转到文件上传示例页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toUpload", method = RequestMethod.GET)
	public String toUpload() {
		return "/upload/upload";
	}

	@Resource(name = "imageCaptchaService")
	private com.octo.captcha.service.CaptchaService imageCaptchaService;

	/**
	 * 验证码
	 */
	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	public void image(String captchaId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (StringUtils.isEmpty(captchaId)) {
			captchaId = request.getSession().getId();
		}
		response.addHeader("vincent.li", "vincent.li");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		ServletOutputStream servletOutputStream = null;
		try {
			servletOutputStream = response.getOutputStream();
			BufferedImage bufferedImage = (BufferedImage) imageCaptchaService.getChallengeForID(captchaId);
			ImageIO.write(bufferedImage, "jpg", servletOutputStream);
			servletOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(servletOutputStream);
		}
	}
	
	@RequestMapping("/unauthorized")
	public String unauthorized(){
		return "/common/unauthorized";
	}

}
