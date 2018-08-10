package com.example.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 生成token
 *
 * token包含三部分，第一部分：实体部分（C）；第二部分：头部信息（类型 加密算法）；第三部分（将之前部分的信息加密）
 */
public class TokenGenerateUtils {

    private static final String SECRTE_KEY = "bearbrownfondofbunnycony";
    private static final String HEADER_TYPE_KEY = "typ";
    private static final String HEADER_ALG_KEY = "alg";
    private static final String HEADER_TYPE_VALUE = "JWT";
    private static final String HEADER_ALG_VALUE = "DES3";

    public static String getToken(long userId, String title, String userIcon) {
        String content = getContent(userId, title, userIcon);
        String header = getHeader();
        String origin = content + "." + header;
        String des3 = null;
        try {
            des3 = des3EncodeECB(origin.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return origin + "." + des3;
    }

    private static String getContent(long userId, String title, String userIcon) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userId);
        jsonObject.addProperty("title", title);
        jsonObject.addProperty("userIcon", userIcon);
        String jsonStr = new Gson().toJson(jsonObject);
        try {
            return Base64.encodeBase64String(jsonStr.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getHeader() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(HEADER_TYPE_KEY, HEADER_TYPE_VALUE);
        jsonObject.addProperty(HEADER_ALG_KEY, HEADER_ALG_VALUE);
        String jsonStr = new Gson().toJson(jsonObject);
        try {
            return Base64.encodeBase64String(jsonStr.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 利用java原生的摘要实现SHA256加密
     *
     * @param origin 加密前的报文
     * @return 加密后的报文
     */
    private static String getDigest(String origin) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(origin.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * 将byte转为16进制
     *
     * @param bytes 原始byte[]
     * @return 转换后的String
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuilder stringBuffer = new StringBuilder();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    /**
     * DES3重加密
     *
     * @param data 加密前数据
     * @return 加密后的数据
     * @throws Exception
     */
    private static String des3EncodeECB(byte[] data) throws Exception {

        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(SECRTE_KEY.getBytes("UTF-8"));
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");

        cipher.init(Cipher.ENCRYPT_MODE, deskey);

        return byte2Hex(cipher.doFinal(data));
    }

    /**
     * DES3重解密
     *
     * @param data 加密后的数据
     * @return 加密前数据
     * @throws Exception
     */
    private static String ees3DecodeECB(byte[] data) throws Exception {

        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(SECRTE_KEY.getBytes("UTF-8"));
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");

        cipher.init(Cipher.DECRYPT_MODE, deskey);

        return byte2Hex(cipher.doFinal(data));

    }
}
