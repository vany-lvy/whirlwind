package cn.kunm.whirlwind.model;

/**
 * @Desc: 处理器
 * @Date: 2020/9/29
 * @author: kunm
 * @modified_user: kunm
 * @modified_date: 2020/9/29
 */
public interface Handler {

    /**
     * 处理器的执行逻辑
     * @param context 上下文
     */
    void execute(Context context);

}
