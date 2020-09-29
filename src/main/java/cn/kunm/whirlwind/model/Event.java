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
     * 原始状态
     */
    private Status fromStatus;
    /**
     * 目标状态
     */
    private Status toStatus;
    /**
     * 处理器
     */
    private Handler handler;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Status getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(Status fromStatus) {
        this.fromStatus = fromStatus;
    }

    public Status getToStatus() {
        return toStatus;
    }

    public void setToStatus(Status toStatus) {
        this.toStatus = toStatus;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
