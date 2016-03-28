package com.lyc.schedulebox.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by lianyuchen on 16/3/11.
 */
public class JsonBean {
    private int code;
    private String codeMsg;
    private Object obj;
    private List<Object> list;
    private Map<Object, Object> map;

    public JsonBean(int code, String codeMsg) {
        super();
        this.code = code;
        this.codeMsg = codeMsg;
    }
    public JsonBean(int code, String codeMsg, Map<Object, Object> map) {
        super();
        this.code = code;
        this.codeMsg = codeMsg;
        this.map = map;
    }
    public JsonBean(int code, String codeMsg, List<Object> list) {
        super();
        this.code = code;
        this.codeMsg = codeMsg;
        this.list = list;
    }
    public JsonBean(int code, String codeMsg, Object obj) {
        super();
        this.code = code;
        this.codeMsg = codeMsg;
        this.obj = obj;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getCodeMsg() {
        return codeMsg;
    }
    public void setCodeMsg(String codeMsg) {
        this.codeMsg = codeMsg;
    }
    public Object getObj() {
        return obj;
    }
    public void setObj(Object obj) {
        this.obj = obj;
    }
    public List<Object> getList() {
        return list;
    }
    public void setList(List<Object> list) {
        this.list = list;
    }
    public Map<Object, Object> getMap() {
        return map;
    }
    public void setMap(Map<Object, Object> map) {
        this.map = map;
    }
    @Override
    public String toString() {
        return "JsonBean [code=" + code + ", codeMsg=" + codeMsg + ", obj="
                + obj + ", list=" + list + ", map=" + map + "]";
    }

}
