package cn.kunm.whirlwind;

import cn.kunm.whirlwind.model.Context;
import cn.kunm.whirlwind.model.Event;
import cn.kunm.whirlwind.model.Handler;
import cn.kunm.whirlwind.model.Status;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @Desc: 状态机
 * @Date: 2020/9/29
 * @author: kunm
 * @modified_user: kunm
 * @modified_date: 2020/9/29
 */
public class StatusMachine {

    private final ReentrantLock lock = new ReentrantLock();
    /**
     * 同时发起多个事件时,会带有锁定,锁定超过超时时间,则报错
     */
    private Integer timeout = 3;

    /**
     * 所有状态
     */
    private Set<Status> allStatus;

    private Status currentStatus;

    private Context context;

    public StatusMachine(Set<Status> allStatus) {
        currentStatus = checkAllStatus(allStatus);
        this.allStatus = allStatus;
    }

    /**
     * 校验所有状态是否符合条件
     *
     * @param allStatus 所有存在的状态
     * @return 初始状态集合
     */
    private Status checkAllStatus(Set<Status> allStatus) {
        Status start = null;
        Set<String> statusCodes = allStatus.stream().map(Status::getCode).collect(Collectors.toSet());
        if (statusCodes.size() != allStatus.size()) {
            throw new IllegalArgumentException("each state requires a unique code");
        }
        Set<Status> startStatus = allStatus.stream().filter(status -> status.isStart()).collect(Collectors.toSet());
        if (Objects.isNull(startStatus) || startStatus.isEmpty() || startStatus.size() != 1) {
            throw new IllegalArgumentException("start status is null or multiple");
        }
        for (Status status : startStatus) {
            start = status;
            break;
        }
        return start;
    }

    /**
     * 触发事件
     *
     * @param event 被触发的事件
     */
    public void sendEvent(Event event) throws IllegalAccessException, InterruptedException {
        if (lock.tryLock(timeout, TimeUnit.SECONDS)) {
            try {
                String code = event.getCode();
                Handler handler = event.getHandler();
                Status fromStatus = event.getFromStatus();
                Status toStatus = event.getToStatus();
                check(code, handler, fromStatus, toStatus);
                setContext(event, fromStatus, toStatus);
                handler.execute(this.context);
                this.currentStatus = toStatus;
            } catch (Exception e) {
                lock.unlock();
                throw e;
            }
        }
    }

    /**
     * 设置处理器上下文
     * @param event 触发的事件
     * @param fromStatus 触发状态
     * @param toStatus 目标状态
     */
    private void setContext(Event event, Status fromStatus, Status toStatus) {
        this.context = new Context();
        this.context.setEvent(event);
        this.context.setFromStatus(fromStatus);
        this.context.setToStatus(toStatus);
    }

    /**
     * 检查事件和状态流转是否正确
     *
     * @param code       事件code
     * @param handler    处理器实例
     * @param fromStatus 触发状态
     * @param toStatus   目标状态
     * @throws IllegalAccessException
     */
    private void check(String code, Handler handler, Status fromStatus, Status toStatus) throws IllegalAccessException {
        if (code == null || code.length() < 1) {
            throw new IllegalAccessException("you must set event code");
        }
        if (Objects.isNull(handler)) {
            throw new IllegalAccessException("event handler is null:" + code);
        }
        if (Objects.isNull(fromStatus) || Objects.isNull(toStatus)) {
            throw new IllegalAccessException("event from status or target event is null:" + code);
        }
        if (fromStatus.isEnd()) {
            throw new IllegalAccessException("from status is end status:" + fromStatus.getCode());
        }
        if (toStatus.isStart()) {
            throw new IllegalAccessException("target status is start status:" + toStatus.getCode());
        }
        List<Status> allowNextStatus = fromStatus.getAllowNextStatus();
        if (!allowNextStatus.stream().map(Status::getCode).collect(Collectors.toSet()).contains(toStatus.getCode())) {
            throw new IllegalAccessException("event:" + code + ",target status not allow,");
        }
    }

    public Set<Status> getAllStatus() {
        return allStatus;
    }

    public Status getCurrentStatus() {
        return currentStatus;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
