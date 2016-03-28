package com.lyc.schedulebox.logic.model;

import java.util.List;

/**
 * Created by lianyuchen on 16/3/11.
 */
public class ScheduleListBean extends BaseModel{

    private ObjEntity obj;

    public void setObj(ObjEntity obj) {
        this.obj = obj;
    }

    public ObjEntity getObj() {
        return obj;
    }

    public static class ObjEntity {
        /**
         * scheduleContent : sdfsdafasdfasdf
         * scheduleEndTime : 2016-03-11 16:28:53.0
         * scheduleId : 1
         * scheduleIsFinished : false
         * scheduleStartTime : 2016-03-09 16:28:51.0
         */

        private List<ListEntity> list;

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public static class ListEntity {
            private String scheduleContent;
            private String scheduleEndTime;
            private int scheduleId;
            private boolean scheduleIsFinished;
            private String scheduleStartTime;

            public void setScheduleContent(String scheduleContent) {
                this.scheduleContent = scheduleContent;
            }

            public void setScheduleEndTime(String scheduleEndTime) {
                this.scheduleEndTime = scheduleEndTime;
            }

            public void setScheduleId(int scheduleId) {
                this.scheduleId = scheduleId;
            }

            public void setScheduleIsFinished(boolean scheduleIsFinished) {
                this.scheduleIsFinished = scheduleIsFinished;
            }

            public void setScheduleStartTime(String scheduleStartTime) {
                this.scheduleStartTime = scheduleStartTime;
            }

            public String getScheduleContent() {
                return scheduleContent;
            }

            public String getScheduleEndTime() {
                return scheduleEndTime;
            }

            public int getScheduleId() {
                return scheduleId;
            }

            public boolean isScheduleIsFinished() {
                return scheduleIsFinished;
            }

            public String getScheduleStartTime() {
                return scheduleStartTime;
            }
        }
    }
}
