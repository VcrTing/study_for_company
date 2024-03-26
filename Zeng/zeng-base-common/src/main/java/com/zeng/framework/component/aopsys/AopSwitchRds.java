package com.zeng.framework.component.aopsys;

import com.zeng.framework.anno.SwitchRds;
import com.zeng.framework.component.datasource.CompSourceRdsService;
import com.zeng.utils.sys.UtilSysAnnotation;
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
public class AopSwitchRds implements Ordered {

    @Autowired
    CompSourceRdsService sourceRdsService;

    @Pointcut("@annotation(com.zeng.framework.anno.SwitchRds)")
    public void rds() { }

    @Around("rds()")
    public Object doing(ProceedingJoinPoint jp) throws Throwable {

        SwitchRds anno = UtilSysAnnotation.getSwitchRds(jp);
        if (anno == null) throw new RuntimeException("AOP 動態數據元 切換失敗，未找到 註解");
        String code = anno.tenantCode();
        String source = anno.dataSource();

        Object proceed = null;
        try {

            // 只根據名稱進行切換 數據元
            sourceRdsService.switchRdsBySourceName(source);
            log.info("------------- 切换为" + source + "数据源 -------------");

            /*
            if (StringUtils.hasLength(source)) {

            }
            else if (StringUtils.hasLength(code)) {

            } else {
                System.out.println("數據元切換錯誤");
            }
            */
            // 執行
            proceed = jp.proceed();
        } finally {
            log.warn("AopSwitchRds 需要移除数据源 => ThreadLocal.remove()，但是没有移除");
        }
        return proceed;
    }

    @Override
    public int getOrder() { return Integer.MIN_VALUE; }
}
