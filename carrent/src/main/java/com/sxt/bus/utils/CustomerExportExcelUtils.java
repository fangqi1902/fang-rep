package com.sxt.bus.utils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.sxt.bus.domain.Customer;


/**
 * 导出用户数据
 * 
 * @author LJH
 * 
 */
public class CustomerExportExcelUtils extends BaseStyle {

	/**
	 * 
	 * @param cus 客户数数据
	 * @param fileName
	 *            文件名
	 * @param response
	 *            响应对象
	 */
	public static void exportCustomerEexcel(List<Customer> cus, String fileName,
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
		HSSFSheet sheet = workbook.createSheet("客户列表");
		// 设置默认列宽
		sheet.setDefaultColumnWidth(22);
		/************** 写数据开始 ******************/
		int row = 0;
		// 第一行
		HSSFRow row1 = sheet.createRow(row);
		CellRangeAddress region = new CellRangeAddress(0, 0, 2,4);
		sheet.addMergedRegion(region);
		HSSFCell row1_cell0 = row1.createCell(2);
		HSSFCellStyle titleStyle = createTitleStyle(workbook, 25);
		row1_cell0.setCellStyle(titleStyle);
		row1_cell0.setCellValue("客户数据");
		// 第二行
		row++;
		HSSFRow row2 = sheet.createRow(row);
		// 合并
		CellRangeAddress region2 = new CellRangeAddress(1, 1, 2, 4);
		sheet.addMergedRegion(region2);
		HSSFCell row2_cell0 = row2.createCell(2);
		HSSFCellStyle secoundTileStyle = createSecoundTitleStyle(workbook, 20);
		row2_cell0.setCellStyle(secoundTileStyle);
		row2_cell0.setCellValue("总条数:" + cus.size() + "  导出时间:"
				+ (new Date().toLocaleString()));
		// 第三行
		row++;
		HSSFRow row3 = sheet.createRow(row);
		HSSFCellStyle tableTitleStyle = createTableTitleStyle(workbook, 18);
		String[] titles = {"身份证号","客户姓名","客户性别","客户地址","客户电话","客户地址" };
		for (int i = 0; i < titles.length; i++) {
			HSSFCell cell = row3.createCell(i);
			cell.setCellStyle(tableTitleStyle);
			cell.setCellValue(titles[i]);
		}
		// 表体数据
		HSSFCellStyle bodyStyle = createTableBodyStyle(workbook, 15);
		for (int i = 0; i < cus.size(); i++) {
			Customer customer = cus.get(i);
			row++;// 加一行
			HSSFRow rowBody = sheet.createRow(row);
			// 创建第一个单元格
			HSSFCell cell1 = rowBody.createCell(0);
			cell1.setCellStyle(bodyStyle);
			cell1.setCellValue(customer.getIdentity());

			HSSFCell cell2 = rowBody.createCell(1);
			cell2.setCellStyle(bodyStyle);
			cell2.setCellValue(customer.getCustname());

			HSSFCell cell3 = rowBody.createCell(2);
			cell3.setCellStyle(bodyStyle);
			cell3.setCellValue(customer.getSex()==1?"男":"女");

			HSSFCell cell4 = rowBody.createCell(3);
			cell4.setCellStyle(bodyStyle);
			cell4.setCellValue(customer.getAddress());
			
			HSSFCell cell5 = rowBody.createCell(4);
			cell5.setCellStyle(bodyStyle);
			cell5.setCellValue(customer.getPhone());
			
			
			HSSFCell cell6= rowBody.createCell(5);
			cell6.setCellStyle(bodyStyle);
			cell6.setCellValue(customer.getCareer());
		}

		/************** 写数据结束 ******************/

		// 6,把文档导出
		try {
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
