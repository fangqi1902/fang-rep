package com.abc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.abc.service.FileService;
import com.abc.vo.ImageVO;

@RestController
public class FileController {
	@Autowired
	private FileService fileService;
	@RequestMapping("/pic/upload")
	public ImageVO fileUpload(MultipartFile uploadFile) {
		return fileService.updateFile(uploadFile);
	}
	

}
