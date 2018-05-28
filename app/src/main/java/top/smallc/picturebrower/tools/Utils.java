package top.smallc.picturebrower.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.ByteArrayOutputStream;

/**
 * @author small.cao
 * @date 2018/4/2
 */
public class Utils {

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isNetworkConnected(Context context) {
        final ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo netInfo = connManager.getActiveNetworkInfo();
        if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
            return true;
        }
        return false;
    }

}
