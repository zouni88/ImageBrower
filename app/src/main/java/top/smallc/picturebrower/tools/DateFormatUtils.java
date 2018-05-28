package top.smallc.picturebrower.tools;

import android.content.Context;
import android.content.res.Resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期格式化的工具类
 */
public class DateFormatUtils {

    // 一分钟的毫秒值
    public static final long ONE_MINUTE = 60 * 1000;

    // 一小时的毫秒值
    public static final long ONE_HOUR = 60 * ONE_MINUTE;

    // 一天的毫秒值
    public static final long ONE_DAY = 24 * ONE_HOUR;

    // 两天的毫秒值
    public static final long TWO_DAY = 2 * ONE_DAY;

    // 一周的毫秒值
    public static final long ONE_WEEK = 7 * ONE_DAY;

    // 一月的毫秒值
    public static final long ONE_MONTH = 30 * ONE_DAY;

    // 一年的毫秒值
    public static final long ONE_YEAR = 12 * ONE_MONTH;

    // 年-月-日 时:分:秒(2014-4-16 19:14:51)
    public static final String PATTERN_FULL = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_FULL_YMDHM = "yyyy-MM-dd HH:mm";
    public static final String PATTERN_MILL = "yyyy-MM-dd HH:mm:ss.SSS";
    // 年-月-日
    public static final String PATTERN_YEAR_MONTH_DAY = "yyyy-MM-dd";
    // 年/月/日
    public static final String PATTERN_YEAR_MONTH_DAY_SLASH = "yyyy/MM/dd";
    // 年/月/日
    public static final String PATTERN_YEAR_MONTH_DAY_SLASH_HAN = "yyyy年MM月dd日";
    // 年-月(2014-4)
    public static final String PATTERN_YEAR_MONTH = "yyyy-M";
    // 年-月(2014)
    public static final String PATTERN_YEAR = "yyyy";
    // 年-月(11)
    public static final String PATTERN_MONTH = "MM";
    // 月日
    public static final String PATTERN_MONTH_DAY = "MM-dd";
    public static final String PATTERN_MONTH_DAY_HOUR_MINUTE = "MM-dd HH:mm";

    public static final String PATTERN_HOUR_MINUTE = "HH:mm";
    public static final String PATTERN_HOUR_MINUTE_SECOND = "HH:mm:ss";
    public static final String PATTERN_MONTH_DAY1 = "M/d";

    /**
     * 将一个日期字串按照指定模式格式化
     *
     * @param date        日期字串
     * @param fromPattern 日期的原始格式
     * @param toPattern   要显示的格式
     * @return 格式化后的字串
     */
    public static String format(String date, String fromPattern, String toPattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(fromPattern, Locale.getDefault());
        try {
            Date d = sdf.parse(date);
            return format(d, toPattern);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将一个日期字串按照指定模式格式化
     *
     * @param date    日期
     * @param pattern 要显示的格式
     * @return 格式化后的字串
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.format(date);
    }


    /**
     * 将一个日期格式的字符串转成Date对象
     *
     * @param date    日期格式的字串
     * @param pattern 字串的格式
     * @return Date对象
     */
    public static Date parse(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前时间和指定时间的间隔
     *
     * @param lastTime 要比较的时间
     * @return 时间间隔
     */
    public static boolean getPassedTime(long lastTime) {
        long currentTime = System.currentTimeMillis();
        long timePassed = currentTime - lastTime;
        if (timePassed >= 7 * ONE_DAY) {
            return true;
        }
        return false;
    }

    /**
     * 获取时间是几周前
     *
     * @param context 上下文
     * @param date    要比较的时间
     * @param netTime 当前的网络时间
     * @return
     */
    public static boolean getPassedWeek(Context context, String date, String netTime) {
        Date netDate = DateFormatUtils.parse(netTime, PATTERN_YEAR_MONTH_DAY);
        Calendar c1 = Calendar.getInstance();
        c1.setTime(netDate);
        c1.set(Calendar.DAY_OF_WEEK, c1.getFirstDayOfWeek() + 1);
        c1.add(Calendar.DAY_OF_MONTH, 7);
        long t1 = c1.getTimeInMillis();
        long t2 = DateFormatUtils.parse(date, PATTERN_YEAR_MONTH_DAY).getTime();
        Resources res = context.getResources();
        String weekStart = DateFormatUtils.format(date, PATTERN_YEAR_MONTH_DAY,
                PATTERN_YEAR_MONTH_DAY_SLASH);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(DateFormatUtils.parse(date, PATTERN_YEAR_MONTH_DAY));
        c2.add(Calendar.DAY_OF_MONTH, 6);
        String weekEnd = DateFormatUtils.format(c2.getTime(), PATTERN_YEAR_MONTH_DAY_SLASH);
        long timePassed = t1 - t2;
        String formatter = "";
        if (timePassed >= ONE_WEEK) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * String 转换 long
     *
     * @param strTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static long stringToLong(String strTime, String formatType) throws ParseException {
        Date date = stringToDate(strTime, formatType);
        if (date == null) {
            return 0;
        } else {
            long currentTime = date.getTime();
            return currentTime;
        }
    }

    public static Date stringToDate(String strTime, String formatType) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = formatter.parse(strTime);
        return date;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate, String pattern)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }


}
