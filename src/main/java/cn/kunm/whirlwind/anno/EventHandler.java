package cn.kunm.whirlwind.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Desc: 事件处理器
 * @Date: 2020/10/9
 * @author: kunm
 * @modified_user: kunm
 * @modified_date: 2020/10/9
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface EventHandler {
    String code() default "";
    String fromStatus() default "";
    String toStatus() default "";
}
