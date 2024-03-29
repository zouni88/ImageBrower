package top.smallc.picturebrower.net;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

public class ApiBase {

    public String sign(String appSecret,TreeMap<String,Object> params){
        StringBuilder paramValues = new StringBuilder();
        TreeMap<String,Object> signs = new TreeMap<>();
        for (String key : params.keySet()) {
            signs.put(key,params.get(key));
        }
        signs.put("appSecret",appSecret);
        for (String key : signs.keySet()) {
            paramValues.append(signs.get(key));
        }
        return md5(paramValues.toString());
    }

    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 is unsupported", e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MessageDigest不支持MD5Util", e);
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

}
