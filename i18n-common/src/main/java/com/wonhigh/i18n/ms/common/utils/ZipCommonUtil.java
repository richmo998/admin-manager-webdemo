package com.wonhigh.i18n.ms.common.utils;

import com.yougou.logistics.base.common.exception.ServiceException;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 压缩工具类
 * 
 * @author wang.w
 * @date 2014-6-17 下午7:26:51
 * @version 0.9.1 
 * @copyright richmo998
 */
public class ZipCommonUtil {

	/**
	 * 压缩对象
	 * @param entity
	 * @return
	 * @throws IOException
	 */
	public static byte[] compress(Object entity) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		GZIPOutputStream zipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
		ObjectOutputStream oos = new ObjectOutputStream(zipOutputStream);
		oos.writeObject(entity);
		//flush很重要
		oos.flush();
		oos.close();
		zipOutputStream.close();
		byteArrayOutputStream.close();
		return byteArrayOutputStream.toByteArray();
	}

	/**
	 * 解压缩对象流
	 * @param bytes
	 * @return
	 * @throws IOException
	 */
	public static Object deCompress(byte[] bytes)  throws ServiceException {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
		GZIPInputStream gis=null;
		ObjectInputStream ois=null;
		try {
			gis = new GZIPInputStream(byteArrayInputStream);
			ois = new ObjectInputStream(gis);		
			Object object = ois.readObject();
			
			
			byteArrayInputStream.close();
			return object;
		} catch (ClassNotFoundException | IOException e) {
			throw new ServiceException(e);
		}finally{
				try {
					
					if(gis!=null){
						gis.close();
					}
					
					if(ois!=null){
						ois.close();
					}
					
				} catch (IOException e) {
					throw new ServiceException(e);
				}
			
		}
		
		
	}
	

}
