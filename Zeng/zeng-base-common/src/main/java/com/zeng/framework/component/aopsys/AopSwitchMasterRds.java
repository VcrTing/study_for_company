package com.zeng.framework.component.aopsys;


import com.zeng.framework.component.datasource.CompSourceRdsService;
import com.zeng.framework.constant.data.DataSourceConstant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AopSwitchMasterRds implements Ordered {

    @Autowired
    CompSourceRdsService sourceRdsService;

    @Pointcut("@annotation(com.zeng.framework.anno.SwitchRds)")
    public void rds() { }

    @Around("rds()")
    public Object doing(ProceedingJoinPoint jp) throws Throwable {
        Object proceed = null;
        try {
            sourceRdsService.switchRdsBySourceName(DataSourceConstant.MASTER);
            log.info("------------- 切换为主数据源 -------------");
            proceed = jp.proceed();
        } finally {
            log.warn("AopSwitchMasterRds 需要移除数据源 => ThreadLocal.remove()，但是没有移除");
        }
        return proceed;
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
