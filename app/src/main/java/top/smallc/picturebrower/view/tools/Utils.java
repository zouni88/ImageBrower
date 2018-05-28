package top.smallc.picturebrower.view.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * @author small.cao
 * @date 2018/5/19
 */
public class Utils {


    private static Toast mToast = null; // Toast

    /**
     * Toast
     *
     * @param context 上下文
     * @param msg     提示内容
     */
    public static void toast(Context context, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    /**
     * Toast
     *
     * @param resId 字串资源id
     */
    public static void toast(Context context, int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(resId);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }



}
