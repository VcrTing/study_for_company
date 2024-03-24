package com.zeng.component.aopsys;

import com.zeng.anno.aop.SwitchRds;
import com.zeng.component.datasource.CompSourceRdsService;
import com.zeng.utils.sys.UtilSysAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Aspect
@Component
public class AopSwitchRds implements Ordered {

    @Autowired
    CompSourceRdsService sourceRdsService;

    @Pointcut("@annotation(com.zeng.anno.aop.SwitchRds)")
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
            System.out.println("出錯了應該刪掉上一個數據元定位");
        }
        return proceed;
    }

    @Override
    public int getOrder() { return Integer.MIN_VALUE; }
}
