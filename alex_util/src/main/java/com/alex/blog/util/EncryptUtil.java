package com.alex.blog.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author hushihai
 * @version V1.0, 2018/2/27
 */
public class EncryptUtil
{
    /**
     * @param s:要进行加密的字符串
     * @return 字符串的md5值
     */
    public static String getMd5(String s)
    {
        char hexChar[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        //md5加密算法的加密对象为字符数组，这里是为了得到加密的对象
        byte[] b = s.getBytes();
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(b);
            byte[] b2 = md.digest();// 进行加密并返回字符数组
            char str[] = new char[b2.length << 1];
            int len = 0;
            //将字符数组转换成十六进制串，形成最终的密文
            for (int i = 0; i < b2.length; i++)
            {
                byte val = b2[i];
                str[len++] = hexChar[(val >>> 4) & 0xf];
                str[len++] = hexChar[val & 0xf];
            }
            return new String(str);
        }
        catch (NoSuchAlgorithmException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES 加密
     * 密钥是apiSecret 的前16位
     * 向量是apiSecret 的后16位
     *
     * @param password
     * @param apiSecret
     * @return
     * @throws Exception
     */
    public static String getAesEncrypt(String password, String apiSecret) throws Exception
    {
        String key = apiSecret.substring(0, 16);
        String iv = apiSecret.substring(16);
        String md5Pass = getMd5(password);

        SecretKeySpec keyspec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes("UTF-8"));

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
        byte[] encrypted = cipher.doFinal(md5Pass.getBytes("UTF-8"));

        return bytesToHexString(encrypted);
    }

    public static String bytesToHexString(byte[] bs)
    {
        StringBuffer sb = new StringBuffer();
        String hex = "";
        for (int i = 0; i < bs.length; i++)
        {
            hex = Integer.toHexString(bs[i] & 0xFF);
            if (hex.length() == 1)
            {
                hex = '0' + hex;
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * @param plainText 要加密的字符串
     * @return 加密后的密文
     * @description Base64加密字符串
     * @author Xu Shiqi
     * @date 2018.03.30 15:17
     */
    public static String base64Encode(String plainText) throws UnsupportedEncodingException
    {
        byte[] plainBytes = plainText.getBytes("utf-8");
        Base64 base64 = new Base64();

        byte[] encryptBytes = base64.encode(plainBytes);

        return new String(encryptBytes);
    }

    /**
     * @param encryptText 要解密的密文
     * @return 解密后的原字符串
     * @description Base64解密密文
     * @author Xu Shiqi
     * @date 2018.03.30 15:24
     */
    public static String base64Decode(String encryptText)
    {
        byte[] encryptBytes = encryptText.getBytes();

        Base64 base64 = new Base64();

        byte[] plainBytes = base64.decode(encryptBytes);

        return new String(plainBytes);
    }

//    public static void main(String[] args) throws UnsupportedEncodingException
//    {
//        String str1="MTIz";
//
//        String decryptText=base64Decode(str1);
//
//        System.out.println("密文:"+str1);
//        System.out.println("解密后的原文:"+decryptText);
//    }
}