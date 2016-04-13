package com.lyc.schedulebox.logic.model;

/**
 * Created by lianyuchen on 16/3/28.
 */
public class UserInfoModel extends BaseModel {


    /**
     * userId : 3
     * userName : admin
     * userPhote : IMG_20160412_213435.jpeg
     * userPwd : admin
     * userUUID : ec454912-7824-4f18-b9ba-3cb25a5b49cc
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
        private String userPhote;
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

        public String getUserPhote() {
            return userPhote;
        }

        public void setUserPhote(String userPhote) {
            this.userPhote = userPhote;
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
