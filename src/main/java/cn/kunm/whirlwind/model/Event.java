package cn.kunm.whirlwind.model;

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

    public Event(String code, String fromStatusCode, String toStatusCode, Handler handler) {
        this.code = code;
        this.fromStatus = fromStatusCode;
        this.toStatus = toStatusCode;
        this.handler = handler;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(String fromStatus) {
        this.fromStatus = fromStatus;
    }

    public String getToStatus() {
        return toStatus;
    }

    public void setToStatus(String toStatus) {
        this.toStatus = toStatus;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
