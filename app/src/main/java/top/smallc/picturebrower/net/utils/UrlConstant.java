package top.smallc.picturebrower.net.utils;

public class UrlConstant {

    public static final int TIMEOUT = 20*1000;

    public static final String HTTP_BASE = "http://www.jinwangansha.com";
//    public static final String HTTP_BASE = "http://192.168.1.113:8999";
    /**
     * 手机验证码登陆
     */
    public static final String URL_LOGIN_PHONE = HTTP_BASE + "/user/phoneLogin";

    /**
     * 微信登录
     */
    public static final String URL_LOGIN_WX = HTTP_BASE + "/user/wxLogin";
    public static final String URL_USER_INFO = HTTP_BASE + "/user/info";

    public static final String URL_MINE_INFO = HTTP_BASE + "/user/mineInfo";
    /**
     * 获取短信验证码
     */
    public static final String URL_SMS_VERICODE = HTTP_BASE + "/short_message/send";

    public static final String URL_GET_STATUS_BY_ID = HTTP_BASE + "/status/getStatusById";

    public static final String URL_GET_STATUS_BY_USERID = HTTP_BASE + "/status/getStatusByUserId";

    public static final String URL_GET_MYSTATUS = HTTP_BASE + "/status/getMyStatus";

    /**
     * 获取首页面动态
     */
    public static final String URL_GET_INDEX = HTTP_BASE + "/status/getStatus";
    /**
     * 获取会员列表
     */
    public static final String URL_GET_VIP_LEVEL_LIST = HTTP_BASE + "/user/leaguer/list";

    //**********发现**********
    /**
     * 发现首页面
     */
    public static final String URL_DISCOVER_NAVS = HTTP_BASE + "/lsm/status/navs";
    /**
     * 获取分类列表通过分类ID
     */
    public static final String URL_DISCOVER_GET_BY_ID = HTTP_BASE + "/lsm/status/statusByNavId";
    /**
     * 获取详情通过ID
     */
    public static final String URL_DISCOVER_GET_DETAIL_BY_ID = HTTP_BASE + "/lsm/status/statusDetailById";
    /**
     * 获取存储地址
     */
    public static final String URL_DISCOVER_GET_SKY_DRIVER_ADDRESS = HTTP_BASE + "/lsm/status/downloadLinkById";
    /**
     * 根据用户ID获取套图详情
     */
    public static final String URL_DISCOVER_GET_BY_DETAIL_BY_USERID = HTTP_BASE + "/lsm/status/statusByUserId";
    //***************积分******************
    /**
     * 获取积分记录
     */
    public static final String URL_POINT_RECORD = HTTP_BASE + "/user/integral/list";
    //***************会员*******************
    /**
     * 积分兑换会员
     */
    public static final String URL_EXCHANGE_VIP_BY_POINT = HTTP_BASE + "/user/leaguer/exchangeLeaguerByIntegral";

    public static final String URL_EXCHAGE_VIP_BY_RMB = HTTP_BASE + "";

    public static final String URL_PAY_WECHAT = HTTP_BASE + "/wx/leaguerOrder";

    public static final String URL_PAY_QUERY_RESULT = HTTP_BASE + "/wx/checkLeaguerOrder";

    public static final String URL_PAY_BUY_RECORD = HTTP_BASE + "/wx/minePay";

    //***************分享*****************
    /**
     * 获取分享链接
     */
    public static final String URL_SHARD = HTTP_BASE + "/share/url";

}
