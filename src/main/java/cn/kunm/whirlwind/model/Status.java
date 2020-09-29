package cn.kunm.whirlwind.model;

import java.util.List;

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
    private List<Status> allowNextStatus;
    /**
     * 是否一个起始状态
     */
    private boolean isStart;
    /**
     * 是否一个终止状态
     */
    private boolean isEnd;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Status> getAllowNextStatus() {
        return allowNextStatus;
    }

    public void setAllowNextStatus(List<Status> allowNextStatus) {
        this.allowNextStatus = allowNextStatus;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }
}
