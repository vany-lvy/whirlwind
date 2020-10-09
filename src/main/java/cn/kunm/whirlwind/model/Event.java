package cn.kunm.whirlwind.model;

import java.util.Objects;

/**
 * @Desc: 事件
 * @Date: 2020/9/29
 * @author: kunm
 * @modified_user: kunm
 * @modified_date: 2020/9/29
 */
public class Event {
    /**
     * 事件code,全局唯一
     */
    private String code;
    /**
     * 原始状态code
     */
    private String fromStatus;
    /**
     * 目标状态code
     */
    private String toStatus;
    /**
     * 处理器
     */
    private Handler handler;
    /**
     * 允许携带额外信息
     */
    private Object extra;

    public Event(String code, String fromStatusCode, String toStatusCode, Handler handler) {
        this.code = code;
        this.fromStatus = fromStatusCode;
        this.toStatus = toStatusCode;
        this.handler = handler;
    }

    public Event(String code, String fromStatusCode, String toStatusCode, Handler handler, Object extra) {
        this.code = code;
        this.fromStatus = fromStatusCode;
        this.toStatus = toStatusCode;
        this.handler = handler;
        this.extra = extra;
    }

    public String getCode() {
        return code;
    }

    public String getFromStatus() {
        return fromStatus;
    }

    public String getToStatus() {
        return toStatus;
    }

    public Handler getHandler() {
        return handler;
    }

    public Object getExtra() {
        return extra;
    }
}
