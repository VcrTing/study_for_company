package com.zeng.utils.sys;

import org.apache.shiro.crypto.hash.Md5Hash;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.security.SecureRandom;

public class UtilPassword {

    public static final String ALGORITHM = "PBEWithMD5AndDES"; //加密算法
    public static final String Salt = "63293188"; //密钥
    private static final int ITERATIONCOUNT = 1000;

    public static byte[] getSalt() throws Exception {
        // 实例化安全随机数
        SecureRandom random = new SecureRandom();
        // 产出盐
        return random.generateSeed(8);
    }

    public static byte[] getStaticSalt() {
        // 产出盐
        return Salt.getBytes();
    }

    private static Key getPBEKey(String password) {
        // 实例化使用的算法
        SecretKeyFactory keyFactory;
        SecretKey secretKey = null;
        try {
            keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            // 设置PBE密钥参数
            PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
            // 生成密钥
            secretKey = keyFactory.generateSecret(keySpec);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return secretKey;
    }

    public static String encrypt(String plaintext, String pwd, String salt) {
        /*
        Key key = getPBEKey(password);
        byte[] encipheredData = null;
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt.getBytes(), ITERATIONCOUNT);
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
            //update-begin-author:sccott date:20180815 for:中文作为用户名时，加密的密码windows和linux会得到不同的结果 gitee/issues/IZUD7
            encipheredData = cipher.doFinal(plaintext.getBytes("utf-8"));
            //update-end-author:sccott date:20180815 for:中文作为用户名时，加密的密码windows和linux会得到不同的结果 gitee/issues/IZUD7
        } catch (Exception e) {
        }
        return bytesToHexString(encipheredData);

         */
        return new Md5Hash(pwd, salt, 3).toHex();
    }

    public static String decrypt(String ciphertext, String password, String salt) {

        Key key = getPBEKey(password);
        byte[] passDec = null;
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt.getBytes(), ITERATIONCOUNT);
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);
            passDec = cipher.doFinal(hexStringToBytes(ciphertext));
        }
        catch (Exception e) {
            // TODO: handle exception
        }
        return new String(passDec);
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static void main(String[] args) {
        String salt = "qiong"; // "SxDV33or";
        System.out.println(encrypt("qiong", "123", salt));
    }

}