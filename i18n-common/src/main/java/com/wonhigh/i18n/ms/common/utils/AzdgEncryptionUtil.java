package com.wonhigh.i18n.ms.common.utils;

import java.security.MessageDigest;


import com.yougou.logistics.base.common.utils.EncryptionUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Azdg加密码算法
 * 
 * @author wang.w
 * @date 2014-6-20 上午11:16:39
 * @version 0.9.1 
 * @copyright richmo998
 */
public class AzdgEncryptionUtil {

	/**
	 * 
	 * 加密算法
	 * 
	 * */
	public static String encrypt(String txt, String key) {
		String encrypt_key = "0f9cfb7a9acced8a4167ea8006ccd098";
		int ctr = 0;
		String tmp = "";
		int i;
		for (i = 0; i < txt.length(); i++) {
			ctr = (ctr == encrypt_key.length()) ? 0 : ctr;
			tmp = tmp + encrypt_key.charAt(ctr) + (char) (txt.charAt(i) ^ encrypt_key.charAt(ctr));
			ctr++;
		}
		return EncryptionUtils.base64Encode(key(tmp, key));
	}

	/**
	 * 
	 * 解密算法
	 */
	public String decrypt(String cipherText, String key) {
		// base64解码
		cipherText = EncryptionUtils.base64Decode(cipherText);
		cipherText = key(cipherText, key);
		String tmp = "";
		for (int i = 0; i < cipherText.length(); i++) {
			int c = cipherText.charAt(i) ^ cipherText.charAt(i + 1);
			String x = "" + (char) c;
			tmp += x;
			i++;
		}
		return tmp;
	}

	public static String key(String txt, String encrypt_key) {
		encrypt_key = strMD5(encrypt_key);
		
		if(StringUtils.isEmpty(encrypt_key)){
			encrypt_key="";
		}
		
		int ctr = 0;
		String tmp = "";
		for (int i = 0; i < txt.length(); i++) {
			ctr = (ctr == encrypt_key.length()) ? 0 : ctr;
			int c = txt.charAt(i) ^ encrypt_key.charAt(ctr);
			String x = "" + (char) c;
			tmp = tmp + x;
			ctr++;
		}
		return tmp;
	}

	public static final String strMD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;

	}
}
