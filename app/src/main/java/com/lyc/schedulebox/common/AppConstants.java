package com.lyc.schedulebox.common;

/**
 * Created by lianyuchen on 16/3/11.
 */
public class AppConstants {

    public static String ApkName = "scheduleBox";

    //微信分享appId
    public final static String WX_APP_ID = "wxa77b06b23d872eaf";
    //QQ分享appId
    public final static String QQ_APP_ID = "1105237173";
    /** 当前 DEMO 应用的 APP_KEY,第三方应用应该使用自己的 APP_KEY 替换该 APP_KEY */
    public static final String WEIBO_APP_KEY = "3575297184";

    /**
     * 当前 DEMO 应用的回调页,第三方应用可以使用自己的回调页。 *
     * <p>
     * 注:关于授权回调页对移动客户端应用来说对用户是不可见的,所以定义为何种形式都将不影响,
     * 但是没有定义将无法使用 SDK 认证登录。
     * 建议使用默认回调页:https://api.weibo.com/oauth2/default.html * </p>
     */
    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";

    public static final String SCOPE = "";
    //genymotion模拟器访问本地tomcat的地址：10.0.3.2：8080
    /**
     * 阿里云服务器地址：139.126.46.64
     */
    public final static String WEB_HOST = "http://139.129.46.64:8080/ScheduleBoxServer_war";
    public final static String URL_GET_SCHEDULE_LIST = WEB_HOST + "/SearchScheduleByTimeServlet";
    public final static String URL_GET_USER_INFO = WEB_HOST + "/UserLoginServlet";
    public final static String URL_ADD_SCHEDULE = WEB_HOST + "/SubmitScheduleServlet";
    public final static String URL_GET_MIND_LIST = WEB_HOST + "/SearchMindListServlet";
    public final static String URL_PUB_MIND = WEB_HOST + "/SubmitMindServlet";
    public final static String URL_USER_REGISTER = WEB_HOST + "/UserRegisterServlet";
    public final static String URL_MODIFY_SCHEDULE_STATUS = WEB_HOST + "/ModifyScheduleStatusServlet";
}
