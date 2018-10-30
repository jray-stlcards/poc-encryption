package com.acs.util;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import javax.servlet.http.HttpServletRequest;

public class Encrytion {
    /**
     * Creates encrypted password for properties file
     * @param encryptor
     * @param str
     * @return
     */
    public static String getEncryptedString(StandardPBEStringEncryptor encryptor, String str){
        return encryptor.encrypt(str);
    }

    /**
     * Creates the encryptor
     * @param encryptPassword
     * @return
     */
    public static StringEncryptor stringEncryptor(String encryptPassword) {
        final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
        encryptor.setPassword(encryptPassword);
        return encryptor;
    }

    public static String getIP(HttpServletRequest request){
        String ip = null;
        System.out.println(request.getRemoteAddr());
        for (String header : IP_HEADERS) {
            ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                System.out.println(ip);
                return ip;
            }
        }
        return (ip == null ? request.getRemoteAddr() : ip);
    }

    public static final String[] IP_HEADERS = {
            "X-MS-Forwarded-Client-IP",
            "HTTP_X_MS_FORWARDED_CLIENT_IP",
            "HTTP_X_FORWARDED",
            "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR",
    };
}
