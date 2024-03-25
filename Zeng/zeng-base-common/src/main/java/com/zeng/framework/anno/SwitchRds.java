package com.zeng.framework.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
// 注解策略属性；RetentionPolicy.RUNTIME表示注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在
@Retention(RetentionPolicy.RUNTIME)
public @interface SwitchRds {
    /**
     * 根据数据源bean切换数据源
     * 此处可以切换的数据源在 DataSourceConfig 配置类中
     * 同时指定了tenantCode则这个优先
     */
    String dataSource() default "";

    /**
     * 动态切换-根据租户代码切换数据源
     */
    String tenantCode() default "";
}
