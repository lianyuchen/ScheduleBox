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
         * mindContent : 阿里斯顿我aksjd; 阿斯达斯fkeiug是看电视
         * mindId : 6
         * mindPubTime : 2016-04-06 01:24:21.0
         * userBean : {"userId":24}
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
             * userId : 24
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

                public int getUserId() {
                    return userId;
                }

                public void setUserId(int userId) {
                    this.userId = userId;
                }
            }
        }
    }
}
