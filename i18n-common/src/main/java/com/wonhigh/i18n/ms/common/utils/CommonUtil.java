package com.wonhigh.i18n.ms.common.utils;

import com.wonhigh.i18n.ms.common.constants.PublicConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 * 
 * @author wang.w
 * @date 2014-6-16 上午10:29:32
 * @version 0.9.1 
 * @copyright richmo998
 */
public class CommonUtil {
	private SimpleDateFormat dfDateTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 按要求截取异常信息
	 * @param e 异常
	 * @param maxNum 最大的限制字符输出数
	 * @return
	 */
	public static String splitExceptionStr(Exception e,int maxNum){
		return ExceptionUtils.getFullStackTrace(e).substring(0, maxNum);
	}
	
	/**
	 * 将异常信息转换为字符串
	 * @param e
	 * @return
	 */
	public static String formatException2Str(Exception e){
		return ExceptionUtils.getFullStackTrace(e);
	}
	
	/**
	 * 检查email地址是否正确
	 * @param email
	 * @return
	 */
	public static boolean checkEmailAddr(String email){
		Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");  
 	    Matcher m = p.matcher(email);  
 	    return m.find();
	}
	
	/**
	 * 检查电话号码
	 * @param mobile
	 * @return
	 * 
	 */
	public static boolean checkMobilePhone(String mobile){
		Pattern pattern = Pattern.compile("^\\d{6,20}$"); 
        Matcher matcher = pattern.matcher(mobile);
         if (matcher.matches()) {
            return true;
        }
        return false;
	}
	
	/**
	 * 校验list值
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean checkList(List list){
		return list != null && !list.isEmpty();
	}
	
	public static String formatDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(PublicConstants.DATE_FORMAT_1);
		return sdf.format(date);
	}



	/**
	 * 判断是否是正整数
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		for(int i=str.length();--i>=0;)
		{
		   int chr=str.charAt(i);
		   if(chr<48 || chr>57)
		    return false;
		}
		return true;
	 }


	

	/**
	 * 正则  含有中文
	 * @param str
	 * @return
	 */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
	
    /**
     * 本机ip
     * @return
     */
    public static String getHostIP(){
    	String ip="";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			ip=addr.getHostAddress(); //获取本机ip 
		} catch (UnknownHostException e) {
			return ip;
		}  
        return ip;
    }
    
    /**
     * 本机ip最后一段
     * '192.168.15.25'--'25'
     * @return
     */
    public static String getHostIPLastPart(){
    	String lastPart="";
    	
    	if(!StringUtils.isEmpty(PublicConstants.IMPORT_PART_IP_HOST)){
    		return PublicConstants.IMPORT_PART_IP_HOST;
    	}
    	
		String hostIp=getHostIP(); //获取本机ip
		
		int index=hostIp.lastIndexOf("."); //获取本机ip
		
		lastPart=hostIp.substring(index+1,hostIp.length());
		PublicConstants.IMPORT_PART_IP_HOST=lastPart;
		
        return lastPart;
    }
    
    /**
     * 产生2位随机数(100-99)
     * @return 2位随机数
     */
    public static Integer getTowRandom(){
    	
        Random random = new Random();
        String fourRandom = random.nextInt(100) + "";
        int randLength = fourRandom.length();
        
        if(randLength<2){
	        for(int i=1; i<=2-randLength; i++)
	            fourRandom = "0" + fourRandom  ;
     	}
        
        if(Integer.parseInt(fourRandom)<100){
        	fourRandom="1"+fourRandom.substring(1,fourRandom.length());
        }
        
        return Integer.parseInt(fourRandom);
   }

    /**
     * 产生2位随机数--不重复
     * @return 2位随机数
     */
    public static synchronized Integer getNoRepeatRandomFourNumber(){
    	Integer randomNum;
    	
    	do{
    		randomNum=getTowRandom();
    	}while(PublicConstants.NO_REPEAT_RANDOM_FOUR_SET.contains(randomNum));

    	PublicConstants.NO_REPEAT_RANDOM_FOUR_SET.add(randomNum);
        return  randomNum;
    }
	
	/**
	 * 获取UUID32
	 * @return
	 */
	public static String getUUID32(){
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		return uuid;
	}
	


	
	/**
	 * 隐藏敏感内容
	 *  [^\u4e00-\u9fa5]{4}
	 * @param message
	 * @return
	 */
	public static String displaySafeContent(String message){
		return message.replaceAll("[^\u4e00-\u9fa5]{5,15}", "*****");
	}
	
}
