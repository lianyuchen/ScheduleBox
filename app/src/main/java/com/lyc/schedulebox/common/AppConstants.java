package com.lyc.schedulebox.common;

/**
 * Created by lianyuchen on 16/3/11.
 */
public class AppConstants {

    public static String ApkName = "scheduleBox";

    //微信分享appId
    public final static String WX_APP_ID = "wxa77b06b23d872eaf";
    //QQ分享appId
    public final static String QQ_APP_ID = "Aqc1105237173";
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
}
