package cn.kunm.whirlwind.model;

import java.util.List;
import java.util.Set;

/**
 * @Desc: 状态
 * @Date: 2020/9/29
 * @author: kunm
 * @modified_user: kunm
 * @modified_date: 2020/9/29
 */
public class Status {
    /**
     * 状态码,必须唯一
     */
    private String code;
    /**
     * 状态描述
     */
    private String desc;
    /**
     * 允许的后续状态集合
     */
    private Set<Status> allowNextStatus;
    /**
     * 是否一个起始状态
     */
    private boolean isStart;
    /**
     * 是否一个终止状态
     */
    private boolean isEnd;

    public Status(String code, boolean isStart, boolean isEnd) {
        this.code = code;
        this.desc = code;
        this.isStart = isStart;
        this.isEnd = isEnd;
    }

    public Status(String code) {
        this.code = code;
        this.desc = code;
        this.isStart = Boolean.FALSE;
        this.isEnd = Boolean.FALSE;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Set<Status> getAllowNextStatus() {
        return allowNextStatus;
    }

    public void setAllowNextStatus(Set<Status> allowNextStatus) {
        if (this.allowNextStatus != null && this.allowNextStatus.size() > 0) {
            throw new IllegalArgumentException("Not allowed to repeat set allowNextStatus value");
        }
        this.allowNextStatus = allowNextStatus;
    }

    public boolean isStart() {
        return isStart;
    }

    public boolean isEnd() {
        return isEnd;
    }

}
