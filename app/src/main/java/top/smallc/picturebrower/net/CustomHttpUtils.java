package top.smallc.picturebrower.net;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;

import top.smallc.picturebrower.net.utils.BaseResponse;
import top.smallc.picturebrower.net.utils.HttpBaseUtils;
import top.smallc.picturebrower.net.utils.HttpUtils;
import top.smallc.picturebrower.tools.DateFormatUtils;
import top.smallc.picturebrower.tools.Utils;

public class CustomHttpUtils extends HttpUtils implements HttpBaseUtils.OnHttpResultListener{

    public Context context;
    public CustomHttpUtils(Context context){
        this.context = context;
        setOnHttpResultListener(this);
    }

    public static CustomHttpUtils getInstance(Context context){
        return new CustomHttpUtils(context);
    }

    @Override
    public BaseResponse getResponseForGet(String urls, Map<String, String> headers, Map<String, Object> paramMap, int timeout) {
        return !isNet(context)?baseResponse:super.getResponseForGet(urls, headers, paramMap, timeout);
    }

    @Override
    public BaseResponse getResponseForPost(String urls, Map<String, String> headers, Map<String, Object> paramMap, int timeout) {
        return !isNet(context)?baseResponse:super.getResponseForPost(urls, headers, paramMap, timeout);
    }

    @Override
    public void onFinish(BaseResponse response) {
        switch (response.getStatusCode()) {
            case 200:
                parseResult(response,response.getContent());
                break;
            case BaseResponse.RET_AUTHEN_FEILD:
            case BaseResponse.RET_SERVER_REFUSED:
                response.setRetCode(BaseResponse.RET_HTTP_STATUS_ERROR);
                break;
                default:
                    response.setRetCode(response.getStatusCode());
                    break;
        }
    }

    /**
     * 解析服务器返回的result字段
     *
     * @param baseResponse 自定义的Response，对HttpRespone做了进一步的封装
     * @param content      服务端传回的数据
     */
    private void parseResult(BaseResponse baseResponse, String content) {
        try {
            JSONObject obj = new JSONObject(content); // 用返回的json串生成JSONObject
            int result = obj.getInt("result"); // 获取result的值，1表示成功，其它表示失败
            switch (result) {
                case BaseResponse.RET_HTTP_STATUS_OK:
                    // 成功
                    baseResponse.setContent(content);
                    baseResponse.setRetCode(BaseResponse.RET_HTTP_STATUS_OK);
                    break;
                case BaseResponse.RET_RESP_STATUS_ERROR:
                    baseResponse.setRetCode(BaseResponse.RET_HTTP_STATUS_ERROR);
                    baseResponse.setRetInfo(obj.getString("err"));
                    break;
                default:
                    // 失败
                    baseResponse.setRetCode(result);
                    baseResponse.setRetInfo(obj.getString("err"));
                    break;
            }
        } catch (Exception e) {
            baseResponse.setRetCode(BaseResponse.RET_RESULT_STATUS_ERROR);
            baseResponse.setRetInfo("数据解析异常");
            e.printStackTrace();
        }
    }

    /***
     * 上传文件
     *
     * @param urlPath      url
     * @param formFieldMap 参数 map  键值对
     * @param filePaths    文件路径
     * @return
     */
    public BaseResponse uploadFile(String urlPath, Map<String, Object> formFieldMap, String[] filePaths, final int timeout, final int maxCount) {
        BaseResponse baseResponse = new BaseResponse();
        final  UploadIMgForMultipartUtility http;
        try {
            URL url = new URL(urlPath+"?imei="+formFieldMap.get("imei"));
            http = new UploadIMgForMultipartUtility(url, timeout);
            for (String key : formFieldMap.keySet()) {
                http.addFormField(key, formFieldMap.get(key).toString());
            }
            for (int i = 0; i < filePaths.length; i++) {
                File file = new File(filePaths[i] == null ? "":filePaths[i]);
                http.addFilePart("someFile", file, maxCount);
            }
            final byte[] bytes = http.finish();
            String result = new String(bytes);
            baseResponse.setRetCode(BaseResponse.RET_HTTP_STATUS_OK);
            baseResponse.setContent(result);
            parseResult(baseResponse, baseResponse.getContent());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            baseResponse.setRetCode(BaseResponse.RET_HTTP_STATUS_ERROR);
            baseResponse.setRetInfo("未找到文件");
        } catch (UnknownHostException e) {
            e.printStackTrace();
            baseResponse.setRetCode(BaseResponse.RET_HTTP_STATUS_ERROR);
            baseResponse.setRetInfo("本地网络连接失败");
        } catch (IOException e) {
            e.printStackTrace();
            baseResponse.setRetCode(BaseResponse.RET_HTTP_STATUS_ERROR);
            baseResponse.setRetInfo("传输错误");
        }
        return baseResponse;
    }

    /**
     * 下载、
     */
    public BaseResponse download(String urlStr, String savePath) {
        Log.e("cgq",urlStr);
        //        urlStr = "http://gdown.baidu.com/data/wisegame/24a7491f865059bc/baidushoujizhushou_16788685.apk";
        BaseResponse baseResponse = new BaseResponse();
        String fileName = "";
        if (!TextUtils.isEmpty(urlStr)) {
            String[] urlArray = urlStr.split("/");
            fileName = urlArray[urlArray.length - 1];
        }
        /**
         * 写前准备
         * 1.在AndroidMainfest.xml中进行权限配置
         * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
         * 取得写入SDCard的权限
         * 2.取得SDCard的路径： Environment.getExternalStorageDirectory()
         * 3.检查要保存的文件上是否已经存在
         * 4.不存在，新建文件夹，新建文件
         * 5.将input流中的信息写入SDCard
         * 6.关闭流
         */
        String pathName = savePath + fileName;//文件存储路径
        OutputStream output = null;
        try {
            /**
             * 通过URL取得HttpURLConnection
             * 要网络连接成功，需在AndroidMainfest.xml中进行权限配置
             * <uses-permission android:name="android.permission.INTERNET" />
             */
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(50 * 1000);
            conn.setRequestMethod("GET");
            //取得inputStream，并将流中的信息写入SDCard
            File file = new File(pathName);
            InputStream input = conn.getInputStream();
            if (file.exists()) {
                file.delete();//存在则删除
            }
            //文件保存位置
            File dirs = new File(savePath);
            if (!dirs.exists()) {
                new File(savePath).mkdirs();//新建文件夹
            }
            file.createNewFile();//新建文件
            output = new FileOutputStream(file);
            //读取大文件
            int maxLength = conn.getContentLength();
            int downProgress = 0;
            byte[] buffer = new byte[1024];
            int len = 0;
            int length = 0;
            Log.e(TAG, "start time = " + DateFormatUtils.format(new Date(), DateFormatUtils.PATTERN_FULL));
            while ((len = input.read(buffer)) != -1) {
                length++;
                output.write(buffer, 0, len);
                downProgress += len;
                if (length >= 64) {
                    length = 0;
                }
            }
            Log.e(TAG, "end time " + DateFormatUtils.format(new Date(), DateFormatUtils.PATTERN_FULL));
            output.flush();
            baseResponse.setRetCode(BaseResponse.RET_HTTP_STATUS_OK);//下载成功
            baseResponse.setContent(pathName);
        } catch (MalformedURLException e) {
            baseResponse.setRetCode(BaseResponse.RET_HTTP_STATUS_ERROR);
            baseResponse.setRetInfo("地址无法解析:" + urlStr);
            e.printStackTrace();
        } catch (IOException e) {
            baseResponse.setRetCode(BaseResponse.RET_HTTP_STATUS_ERROR);
            baseResponse.setRetInfo("下载失败，请稍后重试");
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return baseResponse;
    }

    public boolean isNet(Context context){
        if(context != null && !Utils.isNetworkConnected(context)){
            baseResponse.setRetCode(BaseResponse.RET_NET_ERROR);
            baseResponse.setRetInfo("网络不太好,请检查网络设置!");
            return false;
        }
        return true;
    }
}
