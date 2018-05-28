package top.smallc.picturebrower.net.utils;

import android.content.Context;


/**
 * API response的基类
 */
public class BaseResponse {

    public static final int RET_RESP_STATUS_ERROR = 0;

    // 访问网络成功 {http : 200}
    public static final int RET_HTTP_STATUS_OK = 1;
    // 访问网络异常(result = 0)
    public static final int RET_HTTP_STATUS_ERROR = -1;
    //服务器异常 (result = 500)
    public static final int RET_HTTP_SERVER_ERROR = 500;

    public static final int RET_UN_LOGIN = -2;

    //服务器拒绝
    public static final int RET_SERVER_REFUSED = 403;
    //身份验证失败
    public static final int RET_AUTHEN_FEILD = 401;
    //无权限查看
    public static final int RET_NO_PERMISSION = 409;

    // 返回数据是空(result != 0)
    public static final int RET_RESULT_STATUS_ERROR = -2;
    // 用户凭证过期(result:462)
    public static final int RET_RESULT_CREDENTIALS_EXPIRED = -3;
    // 返回结果result_code
    public static final int RET_RESULT_CODE = -1;
    // 获取缓存数据成功
    public static final int RET_CACHE_STATUS_OK = 1;
    // 获取缓存数据失败
    public static final int RET_CACHE_STATUS_ERROR = -4;
    //设备未绑定
    public static final int RET_UN_BIND = 1001;
    //设备不存在
    public static final int RET_DEVICE_NONE = -1;

    public static final int RET_NET_ERROR = 10001;


    private int statusCode;
    private int retCode;


    private String retInfo;
    private String content;

    public int getRetCode() {
        return retCode;
    }

    public String getRetInfo() {
        return retInfo;
    }

    public String getContent() {
        return content;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statuCode) {
        this.statusCode = statuCode;
    }

    public void setRetInfo(String retInfo) {
        this.retInfo = retInfo;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "BaseResponse [retCode=" + retCode + ", retInfo=" + retInfo + ", content=" + content
                + "]";
    }

    public void unusual(final Context mContext){
//        PreferenceUtils.clear(mContext);
//        ((MyApplication) (((BaseActivity)mContext).getApplication())).clearActivity();
//        Intent intent = new Intent(mContext, WXLoginActivity.class);
//        mContext.startActivity(intent);
        /*AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("您的账号在另外一台设备登录，请尽快修改您的密码！");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((MyApplication) ((BaseActivity)context).getApplication()).exit();
            }
        });
        builder.show();*/
    }

}
