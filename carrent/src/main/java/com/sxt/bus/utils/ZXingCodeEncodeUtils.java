package com.sxt.bus.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.EnumMap;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 生成二维
 * 
 * @author LJH
 * 
 */
public class ZXingCodeEncodeUtils {

	/**
	 * 设置相关生成二维码的信息
	 */
	// 二维码颜色
	private static final int BLACK = 0xFF000000;
	// 二维码背景颜色
	private static final int WHITE = 0xFFFFFFFF;

	// 二维码格式参数
	private static final EnumMap<EncodeHintType, Object> hints = new EnumMap<EncodeHintType, Object>(
			EncodeHintType.class);
	static{
		/*
		 * 二维码的纠错级别(排错率),4个级别： L (7%)、 M (15%)、 Q (25%)、 H (30%)(最高H)
		 * 纠错信息同样存储在二维码中，纠错级别越高，纠错信息占用的空间越多，那么能存储的有用讯息就越少；共有四级； 选择M，扫描速度快。
		 */
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// 二维码边界空白大小 1,2,3,4 (4为默认,最大)
		hints.put(EncodeHintType.MARGIN, 1);
		//设置生成的二维码内容的格式
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		
	}
	

	/**
	 * 生成一个二码维保存到某个路径
	 * 
	 * @param content
	 *            二维码里面的信息
	 * @param width
	 *            图片的宽度
	 * @param height
	 *            图片的高度
	 * @param outPath
	 *            输出路径
	 * @param imageType
	 *            图片类型
	 */
	public static void createZxingCodeNormalSaveToDisk(String content, int width,
			int height, String outPath, String imageType) {
		try {
			// 1、生成二维码
			BitMatrix encode = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			// 2、得到二维码的宽高
			int codeWidth=encode.getWidth();
			int codeHeight=encode.getHeight();
			
			//3、根据二维码的宽高，创建一个空白的图片流对象
			BufferedImage image=new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);
			//4,把encode向image流里面写
			for (int i = 0; i < codeWidth; i++) {
				for (int j = 0; j < codeHeight; j++) {
					// 4、循环将二维码内容定入图片
					image.setRGB(i, j, encode.get(i, j) ? BLACK : WHITE);
				}
			}
			//5、把BufferedImage写入到outPath地址
			File outPutImage = new File(outPath);
			// 如果图片不存在创建图片
			if (!outPutImage.exists()){
				outPutImage.createNewFile();
			}
			// 5、将二维码写入图片
			ImageIO.write(image, imageType, outPutImage);
			System.out.println("生成成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成一个二码码返回一个图片流
	 * @param content
	 * @param width
	 * @param height
	 * @param outPath
	 * @param imageType
	 */
	public static BufferedImage createZxingCodeNormal(String content, int width,
			int height, String imageType) {
		try {
			// 1、生成二维码
			BitMatrix encode = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			// 2、得到二维码的宽高
			int codeWidth=encode.getWidth();
			int codeHeight=encode.getHeight();
			
			//3、根据二维码的宽高，创建一个空白的图片流对象
			BufferedImage image=new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);
			//4,把encode向image流里面写
			for (int i = 0; i < codeWidth; i++) {
				for (int j = 0; j < codeHeight; j++) {
					// 4、循环将二维码内容定入图片
					image.setRGB(i, j, encode.get(i, j) ? BLACK : WHITE);
				}
			}
			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 生成一个二码码返回一个图片流 并具在中间显示logo
	 * @param content
	 * @param width
	 * @param height
	 * @param outPath
	 * @param imageType
	 * @param logoStream 中间的 logo的图片流
	 */
	public static BufferedImage createZxingCodeLogo(String content, int width,
			int height, String imageType,InputStream logoStream) {
		//先生成二维码的图片流
		BufferedImage image=createZxingCodeNormal(content, width, height, imageType);
		if(null==image){
			System.out.println("二维码生成异常");
			return null;
		}
		try {
			// 获取画笔
			Graphics2D g = image.createGraphics();
			if(null==logoStream){
				System.err.println("logo流为空");
				return image;
			}else{
				//把logoStream 转成BufferedImage
				BufferedImage logo=ImageIO.read(logoStream);
				// 设置logo在二维码中的大小，太大，会覆盖二维码，此处20%
				int logoWidth = logo.getWidth(null) > image.getWidth() /5
						? (image.getWidth()  / 5) : logo.getWidth(null);
				int logoHeight = logo.getHeight(null) > image.getHeight() /5
						? (image.getHeight() / 5) : logo.getHeight(null);
				// 设置logo图片放置位置
				// logo的起点
				int x = (image.getWidth() - logoWidth) / 2;
				int y = (image.getHeight() - logoHeight) / 2;
				// 开始合并绘制图片
				g.drawImage(logo, x, y, logoWidth, logoHeight, null);
				//画一个圆角矩形
				g.drawRoundRect(x, y, logoWidth, logoHeight, 15, 15);
				// 设置画笔的粗细
				g.setStroke(new BasicStroke(2));
				// 设置画笔的颜色
				g.setColor(Color.WHITE);
				//画一个白色的矩形
				g.drawRect(x, y, logoWidth, logoHeight);
				g.dispose();//应用
				logo.flush();
				image.flush();
				return image;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		String content="老雷";
		createZxingCodeNormalSaveToDisk(content, 1000, 1000, "D:/laolei.jpg", "jpeg");
	}

}
