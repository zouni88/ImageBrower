package top.smallc.picturebrower.view.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Preference的工具类
 */
public class PreferenceUtils {
    // sharedpreference文件名
    private static final String PREFERENCE_FILE = "pb_preference";

    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE,
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void putBoolean(Context context, String preferenceFile, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(preferenceFile, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void putString(Context context, String preferenceFile, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(preferenceFile, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void putLong(Context context, String key, long value) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE,
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static void putInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE,
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE,
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static Boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE,
                Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static boolean getBoolean(Context context, String preferenceFile, String key,
            boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(preferenceFile,Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static String getString(Context context, String preferenceFile, String key,
            String defValue) {
        SharedPreferences sp = context.getSharedPreferences(preferenceFile, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE,
                Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static Long getLong(Context context, String key, long defValue) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE,
                Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }

    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE,
                Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    /**
     * 清空SharedPreference
     * 
     * @param context
     *            上下文
     */
    public static void clear(Context context) {
        //不清理手机号
//        String mobile = getString(context, Constant.PREFERENCE_KEY_PHONE, "");
        //打开记录
        //是否保存退出的状态,用于启动service
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE,Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.clear();
        editor.commit();
//        putString(context, Constant.PREFERENCE_KEY_PHONE, mobile);
    }

}
