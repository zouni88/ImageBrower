package top.smallc.picturebrower.net;

import android.os.Handler;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

}
