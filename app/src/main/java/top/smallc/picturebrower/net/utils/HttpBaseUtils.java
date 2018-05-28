package top.smallc.picturebrower.net.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author small.cao
 * @date 2018/4/13
 */
public class HttpBaseUtils {

    public interface OnHttpResultListener{
        void onFinish(BaseResponse response);
    }

    public OnHttpResultListener onHttpResultListener ;
    public void setOnHttpResultListener(OnHttpResultListener onHttpResultListener){
        this.onHttpResultListener = onHttpResultListener;
    }

    public StringBuffer encodeParam(Map<String,Object> paramMap){
        StringBuffer sb = new StringBuffer();
        for (String key : paramMap.keySet()) {
            Object value = paramMap.get(key);
            sb.append("&" + key + "=");
            try {
                String values = URLEncoder.encode(value.toString(), "utf-8");
                sb.append(value == null ? "" : values.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb;
    }

    private final int BUFFER_SIZE = 4 * 1024;

    public String inputStream2String(InputStream is) {
        if (is == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        int len = 0;
        try {
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            sb.append(baos.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
