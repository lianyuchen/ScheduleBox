package com.lyc.schedulebox.utils.logutils;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 将日志输出到sd上的"58daojia"文件夹
 */
public class LogToLocal {

    // 文件帮助类。用户写log日志
    public static String SDPATH = Environment.getExternalStorageDirectory().toString()+ File.separator;
    public static String WUBASDPATH = SDPATH + "58daojia";
    public static String LogFileName = "Log.txt";
    public static DateFormat fmt = new SimpleDateFormat("yyyyMMdd");
    public static DateFormat fmtTime = new SimpleDateFormat(
            "MM-dd HH:mm:ss ");

    public static void writeLog(String content) {
        writeLog(content, "");
    }

    /**
     * 写入日志文件到本地磁盘
     *
     * @param content 输出内容
     * @param childDir 子目录
     */
    public static void writeLog(String content, String childDir) {

        try {
            String dirFilestring = WUBASDPATH + File.separator;
            if("" != childDir){
                dirFilestring = dirFilestring + childDir + File.separator;
            }

            File LogFile = new File(dirFilestring);
            if (!LogFile.exists()) {
                LogFile.mkdirs();
            }

            File logFile = new File(dirFilestring+ File.separator
                    + fmt.format(new Date()) + LogFileName);

            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            FileWriter logWriter = new FileWriter(logFile, true);
            BufferedWriter bw = new BufferedWriter(logWriter);
            bw.newLine();
            bw.write(fmtTime.format(new Date()) + "  :  " + content);
            logWriter.flush();
            bw.close();
            logWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
