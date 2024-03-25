package com.zeng.utils.sys;

import com.zeng.framework.anno.SwitchRds;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

public final class UtilSysAnnotation {

    public static SwitchRds getSwitchRds(ProceedingJoinPoint jp) {
        // 類上獲取
        SwitchRds anno = jp.getTarget().getClass().getAnnotation(SwitchRds.class);

        if (anno == null) {
            Signature signature = jp.getSignature();
            if (signature instanceof MethodSignature) {
                // 方法上獲取
                MethodSignature ms = (MethodSignature) signature;
                return ms.getMethod().getAnnotation(SwitchRds.class);
            } else {
                System.out.println("這不能方法 aop sign");
            }

            // 默認
            return null;
        }
        return anno;
    }

}
