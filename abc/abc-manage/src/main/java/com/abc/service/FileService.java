package com.abc.service;

import org.springframework.web.multipart.MultipartFile;

import com.abc.vo.ImageVO;

public interface FileService {
	 ImageVO updateFile(MultipartFile uploadFile);

}
