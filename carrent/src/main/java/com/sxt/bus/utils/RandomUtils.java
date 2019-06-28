package com.sxt.bus.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 随机数据及字符串生成工具类
 * @author LJH
 *
 */
public class RandomUtils {

	private static SimpleDateFormat sdf1=new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private static SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat sdf3=new SimpleDateFormat("yyyyMMdd_HHmmss_0SSS");
	private static Random random=new Random();
	/**
	 * 使用时间+四位随机数
	 * 
	 * 
	 */
	public static String createFileNewNameUseTime(String oldName){
		//取出名字的后缀名
		String suffix=oldName.substring(oldName.lastIndexOf("."), oldName.length());
		//生成时间字符串
		String time=sdf1.format(new Date());
		//生成四位随机数
		Integer num=(random.nextInt(9000)+1000);
		return time+num+suffix;
	}
	
	
	
	/**
	 * 根据当前时间得到文件夹的名字
	 */
	public static String getDirNameUseTime(){
		return sdf2.format(new Date());
	}
	
	/**
	 * 使用UUID
	 * @param args
	 */
	public static String createFileNewNameUseUUID(String oldName){
		//取出名字的后缀名
		String suffix=oldName.substring(oldName.lastIndexOf("."), oldName.length());
		//生成uuid
		String uuid=UUID.randomUUID().toString().replace("-", "");
		return uuid+suffix;
	}
	//生成随机单号
	public static String  createRandomStrUseTime(String prefix){
		return prefix+"_"+sdf3.format(new Date())+"_"+(random.nextInt(90000)+10000);
	}
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		String oldName="sdafdsafdsa.dsafdsaf.sdafdsafdsaf.png";
		System.out.println(createFileNewNameUseUUID(oldName));
	}
	
	
	
	
}
