package com.sxt.bus.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.util.CellRangeAddress;

import com.sxt.bus.domain.Customer;
import com.sxt.bus.domain.Rent;


/**
 * 导出客户数据
 * 
 * @author LJH
 * 
 */
public class RentExportExcelUtils extends BaseStyle {

	/**
	 * 
	 * @param 出租单数据
	 * @param fileName
	 *            文件名
	 * @param response
	 *            响应对象
	 */
	public static void exportRentEexcel(Rent rent,Customer customer,String fileName,
			HttpServletResponse response) {

		/**
		 * 文件下载相关设置
		 */
		// 处理文件名
		try {
			fileName = URLEncoder.encode(fileName, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 处理文件类型
		response.setContentType("application/x-download");
		// 设置下载的文件名
		response.addHeader("Content-Disposition",
				"attachment; filename=\"" + fileName + "\"");
		
		// 1,创建一个工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 2,在工作簿上面创建一个sheet
		HSSFSheet sheet = workbook.createSheet("出租单表");
		// 设置默认列宽
		sheet.setDefaultColumnWidth(30);
		/************** 写数据开始 ******************/
		int row = 0;
		// 第一行
		HSSFRow row1 = sheet.createRow(row);
		CellRangeAddress region = new CellRangeAddress(0, 0, 0,4);
		sheet.addMergedRegion(region);
		HSSFCell row1_cell0 = row1.createCell(0);
		HSSFCellStyle titleStyle = createTitleStyle(workbook, 30);
		row1_cell0.setCellStyle(titleStyle);
		row1_cell0.setCellValue(rent.getIdentity()+"客户的出租单表");
		
		//表体样式
		HSSFCellStyle bodyStyle = createTableBodyStyle(workbook, 25);
		
 		// 第二行  出租单号 二维码
		row++;
		HSSFRow row2 = sheet.createRow(row);
		//设置行高
		row2.setHeightInPoints(150);
		//出租单号
		HSSFCell row2_cell0 = row2.createCell(0);
		
		row2_cell0.setCellStyle(bodyStyle);
		row2_cell0.setCellValue("出租单号");
		
		HSSFCell row2_cell1 = row2.createCell(1);
		row2_cell1.setCellStyle(bodyStyle);
		row2_cell1.setCellValue(rent.getRentid());
		
		//二维码
		HSSFCell cell2 = row2.createCell(2);
		cell2.setCellStyle(bodyStyle);
		cell2.setCellValue("二维码");
		
		//生成有logo的二维码
		InputStream logoStream=RentExportExcelUtils.class.getClassLoader().getResourceAsStream("logo.jpg");
		System.out.println(logoStream);
		BufferedImage image=ZXingCodeEncodeUtils.createZxingCodeLogo(rent.getRentid(), 300, 300, "png", logoStream);
		
		
		
		//生成二维码
		//BufferedImage image = ZXingCodeEncodeUtils.createZxingCodeNormal(rent.getRentid(), 300, 300, "png");
		 //画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）  
		 HSSFPatriarch patriarch = sheet.createDrawingPatriarch(); 
			//anchor主要用于设置图片的属性  
		   /**
		    * 255代表让图片占满单元格
		    * (3,1)开始的列和行
		    * (4,1)结束的列和行
		    */
		 HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 255,(short) 3, 1, (short) 4, 1);     
		 //设置图片按照单元格大小自动缩放
	     anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE); 
	     
	     //内存流
	     ByteArrayOutputStream byteArrayOut=new ByteArrayOutputStream();
	     try {
			ImageIO.write(image, "jpeg", byteArrayOut);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	    patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));   
				
		
		
		// 第三行
		row++;
		HSSFRow row3= sheet.createRow(row);
		//客户身份号
		HSSFCell row3_cell0 = row3.createCell(0);
		row3_cell0.setCellStyle(bodyStyle);
		row3_cell0.setCellValue("客户身份证");
		
		HSSFCell row3_cell1 = row3.createCell(1);
		row3_cell1.setCellStyle(bodyStyle);
		row3_cell1.setCellValue(rent.getIdentity());
		
		//客户姓名
		HSSFCell row3_cell2 = row3.createCell(2);
		row3_cell2.setCellStyle(bodyStyle);
		row3_cell2.setCellValue("客户姓名：");
		
		HSSFCell row3_cell3 = row3.createCell(3);
		row3_cell3.setCellStyle(bodyStyle);
		row3_cell3.setCellValue(customer.getCustname());
		
		// 第四行
		row++;
		HSSFRow row4= sheet.createRow(row);
		//开始时间
		HSSFCell row4_cell0 = row4.createCell(0);
		row4_cell0.setCellStyle(bodyStyle);
		row4_cell0.setCellValue("开始时间");
		
		HSSFCell row4_cell1 = row4.createCell(1);
		row4_cell1.setCellStyle(bodyStyle);
		row4_cell1.setCellValue(rent.getBegindate().toLocaleString());
		
		//结束时间
		HSSFCell row4_cell2 = row4.createCell(2);
		row4_cell2.setCellStyle(bodyStyle);
		row4_cell2.setCellValue("结束时间");
		
		HSSFCell row4_cell3 = row4.createCell(3);
		row4_cell3.setCellStyle(bodyStyle);
		row4_cell3.setCellValue(rent.getReturndate().toLocaleString());
		
		// 第五行
		row++;
		HSSFRow row5= sheet.createRow(row);
		//车辆编号
		HSSFCell row5_cell0 = row5.createCell(0);
		row5_cell0.setCellStyle(bodyStyle);
		row5_cell0.setCellValue("车辆编号");
		
		HSSFCell row5_cell1 = row5.createCell(1);
		row5_cell1.setCellStyle(bodyStyle);
		row5_cell1.setCellValue(rent.getCarnumber());
		
		//出租价格
		HSSFCell row5_cell2 = row5.createCell(2);
		row5_cell2.setCellStyle(bodyStyle);
		row5_cell2.setCellValue("出租价格");
		
		HSSFCell row5_cell3 = row5.createCell(3);
		row5_cell3.setCellStyle(bodyStyle);
		row5_cell3.setCellValue(rent.getPrice());
		
		//空两行
		row++;
		row++;
		
		// 第八行
		row++;
		HSSFRow row8= sheet.createRow(row);
		//打印时间
		HSSFCell row8_cell2= row8.createCell(2);
		row8_cell2.setCellStyle(bodyStyle);
		row8_cell2.setCellValue("打印时间：");
		
		HSSFCell row8_cell3 = row8.createCell(3);
		row8_cell3.setCellStyle(bodyStyle);
		row8_cell3.setCellValue(new Date().toLocaleString());
		
	
		// 第九行
		row++;
		HSSFRow row9= sheet.createRow(row);
		//客户身份号
		HSSFCell row9_cell2 = row9.createCell(2);
		row9_cell2.setCellStyle(bodyStyle);
		row9_cell2.setCellValue("操作人：");
		
		HSSFCell row9_cell3 = row9.createCell(3);
		row9_cell3.setCellStyle(bodyStyle);
		row9_cell3.setCellValue(rent.getOpername());
		
		// 第十行
		row++;
		HSSFRow row10= sheet.createRow(row);
		//客户身份号
		HSSFCell row10_cell2 = row10.createCell(2);
		row10_cell2.setCellStyle(bodyStyle);
		row10_cell2.setCellValue("客户签字：");
		
		
		
		
		/************** 写数据结束 ******************/

		// 6,把文档导出
		try {
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
