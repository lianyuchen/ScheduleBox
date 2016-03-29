package com.lyc.schedulebox.common;

/**
 * Created by lianyuchen on 16/3/11.
 */
public class AppConstants {

    public static String ApkName = "scheduleBox";
    //genymotion模拟器访问本地tomcat的地址：10.0.3.2：8080
    /**
     * 阿里云服务器地址：139.126.46.64
     */
    public final static String WEB_HOST = "http://139.129.46.64:8080/ScheduleBoxServer_war";
    public final static String URL_GET_SCHEDULE_LIST = WEB_HOST + "/SearchScheduleByTimeServlet";
    public final static String URL_GET_USER_INFO = WEB_HOST + "/UserLoginServlet";
}