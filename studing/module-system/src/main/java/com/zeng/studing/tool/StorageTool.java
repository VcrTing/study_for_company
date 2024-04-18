package org.jeecg.formality.component;

import java.util.concurrent.ConcurrentHashMap;

// 这是一个内存级别的存储工具，
// 用于在单体项目内，替换 Redis 以达到更高性能和数据可用性

public final class StorageTool {
    // 单例，不允许被别人实例化
    private StorageTool() {
        System.out.println("实例化了 StorageTool，这不科学。");
    }

    // 这是一个只存 String 的 MAP
    private final static ConcurrentHashMap<String, String> STORAGE = new ConcurrentHashMap<>();

    // 操作
    public static void set(String key, String v) { if (key == null || v == null) return; STORAGE.put(key, v); }
    public static String get(String key) { return STORAGE.get(key); }
    public static void remove(String key) { STORAGE.remove(key); }
}
