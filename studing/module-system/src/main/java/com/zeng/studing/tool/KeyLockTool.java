package org.jeecg.common.util.lock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 这是一个内存级别的加锁的工具
// 在你要锁某个 订单的单号，不允许它被别人操作的时候，就可以用这个工具给订单单号加锁
// 似乎是恶汉锁

public final class KeyLockTool {
    private final static Map<String, Object> lock = new ConcurrentHashMap<>();

    // 尝试对key加一个锁，返回加锁成功或失败
    public static boolean lock(String key) {
        Object src = new Object();
        Object o = lock.computeIfAbsent(key, _key -> src);
        return src == o;
    }
    // 解说：controller 里它有三个方法，方法里都有给某订单号加锁的代码，如果这三个方法当下要操作同一个订单号，理论上只有一个方法拿到锁，然后另外两个方法退出逻辑
    public static void unlock(String key) {
        lock.remove(key);
    }
}

