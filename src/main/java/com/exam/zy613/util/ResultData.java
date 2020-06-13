package com.exam.zy613.util;

/**
 * ResultData 实体，用于判断true或false，并返回数据
 * @author 31515
 */
public class ResultData {
    private boolean state;
    private String message;
    private Object data;

    public ResultData(boolean state, String message, Object data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
