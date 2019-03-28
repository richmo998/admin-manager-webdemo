package com.wonhigh.i18n.ms.common.utils;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 签名工具
 */
public class SignUtil {

	/**
	 * 计算签名
	 *
	 * @param secret APP密钥
	 * @param method HttpMethod
	 * @param path
	 * @param bodys
	 * @return 签名后的字符串
	 */
	public static String sign(String secret, String method, String path, Map<String, String> bodys) {
		try {
			return new String(DigestUtils.md5Hex(secret + buildStringToSign(method, path, bodys) + secret).getBytes(
					"UTF-8"), "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 构建待签名字符串
	 * @param method
	 * @param path
	 * @param headers
	 * @param querys
	 * @param bodys
	 * @param signHeaderPrefixList
	 * @return
	 */
	private static String buildStringToSign(String method, String path, Map<String, String> bodys) {
		StringBuilder sb = new StringBuilder();

		sb.append(method.toUpperCase());
		sb.append(buildResource(path, null, bodys));

		return sb.toString();
	}

	/**
	 * 构建待签名Path+Query+BODY
	 *
	 * @param path
	 * @param querys
	 * @param bodys
	 * @return 待签名
	 */
	private static String buildResource(String path, Map<String, String> querys, Map<String, String> bodys) {
		StringBuilder sb = new StringBuilder();

		if (!StringUtils.isBlank(path)) {
			sb.append(path);
		}
		Map<String, String> sortMap = new TreeMap<String, String>();
		if (null != querys) {
			for (Map.Entry<String, String> query : querys.entrySet()) {
				if (!StringUtils.isBlank(query.getKey())) {
					sortMap.put(query.getKey(), query.getValue());
				}
			}
		}

		if (null != bodys) {
			for (Map.Entry<String, String> body : bodys.entrySet()) {
				if (!StringUtils.isBlank(body.getKey())) {
					sortMap.put(body.getKey(), body.getValue());
				}
			}
		}

		StringBuilder sbParam = new StringBuilder();
		for (Map.Entry<String, String> item : sortMap.entrySet()) {
			if (!StringUtils.isBlank(item.getKey())) {
				sbParam.append(item.getKey());
				if (!StringUtils.isBlank(item.getValue())) {
					sbParam.append(item.getValue());
				}
			}
		}
		if (0 < sbParam.length()) {
			sb.append(sbParam);
		}

		return sb.toString();
	}

}
