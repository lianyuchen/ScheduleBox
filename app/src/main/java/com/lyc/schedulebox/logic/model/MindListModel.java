package com.lyc.schedulebox.logic.model;

import java.util.List;

/**
 * Created by lianyuchen on 16/4/6.
 */
public class MindListModel extends BaseModel{

    private ObjEntity obj;

    public ObjEntity getObj() {
        return obj;
    }

    public void setObj(ObjEntity obj) {
        this.obj = obj;
    }

    public static class ObjEntity {
        /**
         * mindContent : 公关部男男女女男男女女
         * mindId : 18
         * mindPubTime : 2016-04-12 10:29:15.0
         * userBean : {"userId":3,"userName":"admin","userPhote":"IMG_20160412_164013.jpeg"}
         */

        private List<ListEntity> list;

        public List<ListEntity> getList() {
            return list;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public static class ListEntity {
            private String mindContent;
            private int mindId;
            private String mindPubTime;
            /**
             * userId : 3
             * userName : admin
             * userPhote : IMG_20160412_164013.jpeg
             */

            private UserBeanEntity userBean;

            public String getMindContent() {
                return mindContent;
            }

            public void setMindContent(String mindContent) {
                this.mindContent = mindContent;
            }

            public int getMindId() {
                return mindId;
            }

            public void setMindId(int mindId) {
                this.mindId = mindId;
            }

            public String getMindPubTime() {
                return mindPubTime;
            }

            public void setMindPubTime(String mindPubTime) {
                this.mindPubTime = mindPubTime;
            }

            public UserBeanEntity getUserBean() {
                return userBean;
            }

            public void setUserBean(UserBeanEntity userBean) {
                this.userBean = userBean;
            }

            public static class UserBeanEntity {
                private int userId;
                private String userName;
                private String userPhote;

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
            }
        }
    }
}
