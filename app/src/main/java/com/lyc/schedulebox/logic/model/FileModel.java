package com.lyc.schedulebox.logic.model;

/**
 * Created by lianyuchen on 16/4/12.
 */
public class FileModel extends BaseModel{

    /**
     * fileName : 138325_meitu_2.jpg
     * filePath : /usr/local/apache-tomcat-7.0.59/webapps/ScheduleBoxServer_war/user/photo
     * fileSize : 5956
     */

    private ObjEntity obj;

    public ObjEntity getObj() {
        return obj;
    }

    public void setObj(ObjEntity obj) {
        this.obj = obj;
    }

    public static class ObjEntity {
        private String fileName;
        private String filePath;
        private int fileSize;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public int getFileSize() {
            return fileSize;
        }

        public void setFileSize(int fileSize) {
            this.fileSize = fileSize;
        }
    }
}
