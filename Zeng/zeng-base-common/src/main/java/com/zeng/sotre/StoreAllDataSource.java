package com.zeng.sotre;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StoreAllDataSource {

    // 使用线程安全的 map 存 单例 bean 数据
    public Map<Object, Object> dataSourceMap = new ConcurrentHashMap<>(1000);

    public void setBean(String name, DataSource source) {
        dataSourceMap.put(name, source);
    }

    public DataSource getBean(String name) {

        Object o = dataSourceMap.get(name);
        return (DataSource) o;
    }
}
