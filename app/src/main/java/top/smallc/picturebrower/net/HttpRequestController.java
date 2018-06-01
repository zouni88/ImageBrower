package top.smallc.picturebrower.net;

import android.content.Context;
import android.os.Handler;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import top.smallc.picturebrower.net.api.ApiDetail;
import top.smallc.picturebrower.net.api.ApiList;

/**
 * Http请求的一个总接口，通过该类调用各Api请求服务器。由于网络请求比较耗时，所以需要在线程中作处理。
 */
public class HttpRequestController {

    private static final String TAG = "HttpRequestController";

    private static Handler mHandler = null;

    /**
     * 创建线程池
     */
    private static ThreadPoolExecutor mThreadPoolExecutor = new ThreadPoolExecutor(5,200,0, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024),new ThreadPoolExecutor.AbortPolicy());

    public HttpRequestController() {
    }

    private static void checkHandler() {
        try {
            if (mHandler == null) {
                mHandler = new Handler();
            }
        } catch (Exception e) {
            mHandler = null;
        }
    }

    public static void  getList(final Context context,final int pageNumber,final int pageSize,
                                     final HttpResponseListener<ApiList.ApiListResponse> listener) {
        checkHandler();
        mThreadPoolExecutor.execute(()->{
            ApiList apiList = new ApiList(context, pageNumber,pageSize);
            final ApiList.ApiListResponse response = apiList.getList();
            mHandler.post(() -> {listener.onResult(response);});
        });
    }

    public static void  getDetails(final Context context,final int id,
                                final HttpResponseListener<ApiDetail.ApiDetailResponse> listener) {
        checkHandler();
        mThreadPoolExecutor.execute(()->{
            ApiDetail apiList = new ApiDetail(context, id);
            final ApiDetail.ApiDetailResponse response = apiList.getList();
            mHandler.post(() -> {listener.onResult(response);});
        });
    }

}
