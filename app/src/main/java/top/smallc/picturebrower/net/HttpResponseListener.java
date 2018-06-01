package top.smallc.picturebrower.net;


import top.smallc.picturebrower.net.utils.BaseResponse;

/**
 * Http请求返回后的回调接口
 */
public interface HttpResponseListener<T extends BaseResponse> {

    public void onResult(T response);

}