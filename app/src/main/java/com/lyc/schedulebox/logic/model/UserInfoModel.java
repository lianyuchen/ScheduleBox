package com.lyc.schedulebox.logic.model;

/**
 * Created by lianyuchen on 16/3/28.
 */
public class UserInfoModel extends BaseModel {

    /**
     * userId : 1
     * userName : lianyuchen
     * userPwd : 123456
     * userUUID : 363b36c4-ecf1-4792-aadb-467833f90694
     */

    private ObjEntity obj;

    public ObjEntity getObj() {
        return obj;
    }

    public void setObj(ObjEntity obj) {
        this.obj = obj;
    }

    public static class ObjEntity {
        private int userId;
        private String userName;
        private String userPwd;
        private String userUUID;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPwd() {
            return userPwd;
        }

        public void setUserPwd(String userPwd) {
            this.userPwd = userPwd;
        }

        public String getUserUUID() {
            return userUUID;
        }

        public void setUserUUID(String userUUID) {
            this.userUUID = userUUID;
        }
    }
}
