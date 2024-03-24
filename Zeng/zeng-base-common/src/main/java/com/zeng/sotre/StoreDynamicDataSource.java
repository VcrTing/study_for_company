package com.zeng.sotre;

import com.zeng.constant.data.DataSourceConstant;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

// 這裡不加 @component，會在 config 裡面把這個加進 ioc
public class StoreDynamicDataSource extends AbstractRoutingDataSource {

    // 给每个线程安排
    private static final ThreadLocal<String> STORE = ThreadLocal.withInitial(() -> DataSourceConstant.MASTER);

    // 獲取
    public static String getDataSourceName() { return StoreDynamicDataSource.STORE.get(); }

    // 更改
    public static void setDataSourceName(String name) {
        System.out.println("======== 替换数据源 " + name + " 入 Thread Local ========");
        StoreDynamicDataSource.STORE.set(name);
    }

    // 默認
    public static void defName() { StoreDynamicDataSource.STORE.set(DataSourceConstant.MASTER); }

    //
    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSourceName();
    }
}
