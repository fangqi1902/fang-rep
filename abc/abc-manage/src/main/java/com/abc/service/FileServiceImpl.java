package com.abc.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abc.vo.ImageVO;

@Service
public class FileServiceImpl implements FileService{
	private String localDirPath = "E:/image";
	private String urlPath = "http://image.abc.com/";

	@Override
	public ImageVO updateFile(MultipartFile uploadFile) {
		ImageVO imageVO = new ImageVO();
		String fileName = uploadFile.getOriginalFilename();
		fileName = fileName.toLowerCase();
		if(!fileName.matches("^.+\\.(jpg|png|gif)$")){
			imageVO.setError(1);
			return imageVO;
		}
		try {
			BufferedImage bufferedImage = 
					ImageIO.read(uploadFile.getInputStream());
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();
			if(width == 0 || height == 0) {
				imageVO.setError(1);
				return imageVO;
				}
			String dateDir = 
					new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			String localDir = localDirPath + "/" + dateDir;
			File dirFile = new File(localDir);
			if(!dirFile.exists()) {
				dirFile.mkdirs();
			}
			
			String uuid = UUID.randomUUID().toString().replace("-", "");
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			String realLocalPath = localDir + "/" + uuid + fileType;
			uploadFile.transferTo(new File(realLocalPath));
			//拼接url路径 http://image.jt.com/
			String realUrlPath = urlPath + dateDir + "/" + uuid + fileType;
			
			imageVO.setError(0)
			.setHeight(height)
			.setWidth(width)
			.setUrl(realUrlPath);
		} catch (Exception e) {
			e.printStackTrace();
			imageVO.setError(1);
			return imageVO;
			
		}
		return imageVO;
	}

}
