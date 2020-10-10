package cn.kunm.whirlwind.model;

import cn.kunm.whirlwind.StatusMachine;

/**
 * @Desc: 上下文
 * @Date: 2020/9/29
 * @author: kunm
 * @modified_user: kunm
 * @modified_date: 2020/9/29
 */
public class Context {
    private StatusMachine statusMachine;
    private Status fromStatus;
    private Status toStatus;
    private Event event;

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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public StatusMachine getStatusMachine() {
        return statusMachine;
    }

    public void setStatusMachine(StatusMachine statusMachine) {
        this.statusMachine = statusMachine;
    }
}
