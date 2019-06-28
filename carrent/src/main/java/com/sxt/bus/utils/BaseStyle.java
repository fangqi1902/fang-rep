package com.sxt.bus.utils;

import java.awt.Color;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 基础样式
 * 
 * @author LJH
 * 
 */
public class BaseStyle {

	/**
	 * 标题样式
	 */
	public  static HSSFCellStyle createTitleStyle(HSSFWorkbook workbook, int fontSize) {
		HSSFCellStyle style = baseStyle(workbook);
		// 创建一个字体样式
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) fontSize);//设置字体大小
		font.setBold(true);// 设置字体样式 如加粗，斜体 等
		font.setFontName("华文行楷");
		font.setColor(HSSFFont.COLOR_RED);//设置颜色
		style.setFont(font);
		return style;

	}

	private static HSSFCellStyle baseStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);// 设置水平居中
		style.setVerticalAlignment(VerticalAlignment.CENTER);// 设置垂直居中
		return style;
	}

	/**
	 * 小标题样式
	 */
	public static HSSFCellStyle createSecoundTitleStyle(HSSFWorkbook workbook, int fontSize) {
		HSSFCellStyle style = baseStyle(workbook);
		// 创建一个字体样式
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) fontSize);//设置字体大小
		font.setFontName("黑体");
		font.setColor(HSSFFont.COLOR_NORMAL);//设置颜色
		style.setFont(font);
		return style;

	}

	/**
	 * 表头样式
	 */
	public static HSSFCellStyle createTableTitleStyle(HSSFWorkbook workbook, int fontSize) {
		HSSFCellStyle style = baseStyle(workbook);
		// 创建一个字体样式
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) fontSize);//设置字体大小
		font.setFontName("微软雅黑");
		font.setBold(true);// 设置字体样式 如加粗，斜体 等
		style.setFont(font);
		return style;

	}

	/**
	 * 表体样式
	 */
	public static HSSFCellStyle createTableBodyStyle(HSSFWorkbook workbook, int fontSize) {
		HSSFCellStyle style = baseStyle(workbook);
		return style;

	}
}
