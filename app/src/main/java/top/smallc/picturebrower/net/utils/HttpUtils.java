package top.smallc.picturebrower.net.utils;

import android.text.TextUtils;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Map;

import top.smallc.picturebrower.BuildConfig;

/**
 * @author small.cao
 */
public class HttpUtils extends HttpBaseUtils {
    public final String TAG = "HttpUtils";

    public String REQUEST_CONNECT_FAIELD = "连接服务器失败，请稍等重试!";
    public String REQUEST_TIMEOUT = "请求超时";
    public String REQUEST_FAIELD = "服务器请求失败";
    public String REQUEST_IO_EXCEPTION = "IO异常";
    public BaseResponse baseResponse = new BaseResponse();

    public BaseResponse getResponseForGet(String urls, Map<String,String> headers, Map<String, Object> paramMap, int timeout) {
        String param = "";
        if (paramMap != null && paramMap.size() > 0) {
            StringBuffer sb = encodeParam(paramMap);
            param = sb.substring(1, sb.length());
            param = "?" + param;
        }
        return getResponse(urls + param,headers, "", timeout);
    }

    public BaseResponse getResponseForPost(String urls, Map<String,String> headers,Map<String, Object> paramMap, int timeout) {
        String param = "";
        if (paramMap != null && paramMap.size() > 0) {
            StringBuffer sb = encodeParam(paramMap);
            param = sb.substring(1, sb.length());
        }
        if(BuildConfig.DEBUG) {
            Log.e(TAG, "param=" + param);
        }
        return postResponse(urls, headers,param, timeout);
    }

    private BaseResponse getResponse(String urls,Map<String,String> headers, String param, int timeout) {
        return getResponse(urls, headers,param, timeout, 0);
    }

    private BaseResponse postResponse(String urls,Map<String,String> headers, String param, int timeout) {
        return getResponse(urls,headers, param, timeout, 1);
    }

    private BaseResponse getResponse(String urls,Map<String,String> headers, String param, int timeout, int requestType) {
        DataOutputStream os = null;
        InputStream fStream = null;
        try {
            if(BuildConfig.DEBUG){
                Log.e(TAG,"url=" + urls + param);
            }
            URL url = new URL(requestType == 0 ? urls + param : urls);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(timeout);
            /* 允许Input、Output，不使用Cache */
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            /* setRequestProperty */
            con.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");
            for(String head:headers.keySet()){
                if(!TextUtils.isEmpty(headers.get(head))){
                    con.setRequestProperty(head,headers.get(head));
                }
            }
            switch (requestType) {
                //get请求
                case 0:
                    con.setRequestMethod("GET");
                    con.connect();
                    break;
                //post 请求
                case 1:
                    con.setRequestMethod("POST");
                    con.connect();
                    os = new DataOutputStream(con.getOutputStream());
                    os.writeBytes(param);
                    os.flush();
                    os.close();
                    break;
                    default:
                        break;
            }
            baseResponse.setStatusCode(con.getResponseCode());
            try{
                baseResponse.setContent(inputStream2String(con.getInputStream()));
                Log.e(TAG,baseResponse.getContent());
            } catch(Exception e){
            }
            onHttpResultListener.onFinish(baseResponse);
            con.disconnect();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
            baseResponse.setRetCode(BaseResponse.RET_HTTP_STATUS_ERROR);
            baseResponse.setRetInfo(REQUEST_CONNECT_FAIELD);
            Log.e(TAG, REQUEST_CONNECT_FAIELD);
        } catch (SocketTimeoutException e) {
            baseResponse.setRetCode(BaseResponse.RET_HTTP_STATUS_ERROR);
            baseResponse.setRetInfo(REQUEST_TIMEOUT);
            Log.e(TAG, REQUEST_TIMEOUT);
        } catch (ConnectException e) {
            baseResponse.setRetCode(BaseResponse.RET_HTTP_STATUS_ERROR);
            baseResponse.setRetInfo(REQUEST_FAIELD);
            Log.e(TAG, REQUEST_FAIELD);
        } catch (Exception e) {
            baseResponse.setRetCode(BaseResponse.RET_HTTP_STATUS_ERROR);
            baseResponse.setRetInfo(REQUEST_IO_EXCEPTION);
            Log.e(TAG, REQUEST_IO_EXCEPTION);
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (fStream != null) {
                    fStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return baseResponse;
    }



}
