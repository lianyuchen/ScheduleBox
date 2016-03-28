package com.lyc.schedulebox.utils.logutils;


import com.lyc.schedulebox.utils.logutils.utils.SystemUtil;

/**
 * 通用log日志类<br/>
 * 支持以下功能：<br/>
 * <ul>
 * <li>支持全局配置log输出</li>
 * <li>不需要设置tag，可配置前缀</li>
 * <li>支持直接打印数据集合，eg: List、Set、Map、Array...</li>
 * <li>支持将log写到sd卡</li>
 * <li>准确显示调用方法、行，快速定位所在文件位置</li>
 * </ul>
 * Created by zhouyang on 2016/1/7.<br/>
 * 后期可扩展（直接使用新的工具类替换本类中的Logger）。
 */
public final class LogUtils {

    private static Logger logger;

    static {
        logger = new Logger();
    }

    // 允许输出日志
    public static boolean configAllowLog = true;
    // 允许将日志写到sd卡
    public static boolean configAllowLog2SD = true;

    // 配置日志Tag前缀
    public static String configTagPrefix = "";

    /**
     * 初始化配置
     *
     * @param logSwitch       default is false
     * @param log2SdSwitch    default is false
     * @param configTagPrefix log 前缀；default is ”“；可配置为”debug-“等。
     */
    public static void init(boolean logSwitch, boolean log2SdSwitch, String configTagPrefix) {
        // 配置日志是否输出(默认true)
        LogUtils.configAllowLog = logSwitch;
        // 配置日志是否写到sd卡
        LogUtils.configAllowLog2SD = log2SdSwitch;
        // 配置日志前缀
        LogUtils.configTagPrefix = configTagPrefix;

    }

    /**
     * verbose输出
     *
     * @param msg
     * @param args
     */
    public static void v(String msg, Object... args) {
        logger.v(SystemUtil.getStackTrace(), msg, args);
    }

    public static void v(Object object) {
        logger.v(SystemUtil.getStackTrace(), object);
    }


    /**
     * debug输出
     *
     * @param msg
     * @param args
     */
    public static void d(String msg, Object... args) {
        logger.d(SystemUtil.getStackTrace(), msg, args);
    }

    public static void d(Object object) {
        logger.d(SystemUtil.getStackTrace(), object);
    }

    /**
     * info输出
     *
     * @param msg
     * @param args
     */
    public static void i(String msg, Object... args) {
        logger.i(SystemUtil.getStackTrace(), msg, args);
    }

    public static void i(Object object) {
        logger.i(SystemUtil.getStackTrace(), object);
    }

    /**
     * warn输出
     *
     * @param msg
     * @param args
     */
    public static void w(String msg, Object... args) {
        logger.w(SystemUtil.getStackTrace(), msg, args);
    }

    public static void w(Object object) {
        logger.w(SystemUtil.getStackTrace(), object);
    }

    /**
     * error输出
     *
     * @param msg
     * @param args
     */
    public static void e(String msg, Object... args) {
        logger.e(SystemUtil.getStackTrace(), msg, args);
    }

    public static void e(Object object) {
        logger.e(SystemUtil.getStackTrace(), object);
    }

    /**
     * assert输出
     *
     * @param msg
     * @param args
     */
    public static void wtf(String msg, Object... args) {
        logger.wtf(SystemUtil.getStackTrace(), msg, args);
    }

    public static void wtf(Object object) {
        logger.wtf(SystemUtil.getStackTrace(), object);
    }


    /**
     * 打印json
     *
     * @param json
     */
    public static void json(String json) {
        logger.json(SystemUtil.getStackTrace(), json);
    }

    /**
     * 将日志写到sd'卡的文件里
     *
     * @param content
     */
    public static void log2SD(String content) {
        if (LogUtils.configAllowLog2SD) {
            log2SD(content, "");
        }
    }

    /**
     * 将日志写到sd'卡的文件里
     *
     * @param content
     * @param childDir 子目录 push
     */
    public static void log2SD(String content, String childDir) {
        if (LogUtils.configAllowLog2SD) {
            LogToLocal.writeLog(content, childDir);
        }
    }
}
