package com.wonhigh.i18n.ms.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by user on 2015/7/21.
 */
public class Base64Utils {
    private static final Logger logger = LoggerFactory.getLogger(Base64Utils.class);

    public static String encrytor (String encrytorString) {
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            return encoder.encode(encrytorString.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static String decryptor (String decryptorString) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            return new String(decoder.decodeBuffer(decryptorString));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
//        String text = "123";
//        String etext = Base64Utils.encrytor(text);
//        String dtext = Base64Utils.decryptor(etext);
//
//        System.out.println(text);
//        System.out.println(etext);
//        System.out.println(dtext);
//        System.out.println(dtext.length());
//        System.out.println(etext.length());
    }
}
