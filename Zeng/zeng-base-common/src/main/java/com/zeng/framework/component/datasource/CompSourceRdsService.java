package com.zeng.framework.component.datasource;

import com.zeng.framework.constant.data.DataSourceConstant;
import com.zeng.framework.sotre.StoreDynamicDataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CompSourceRdsService {

    // BY CODE
    // public void

    // BY NAME
    public void switchRdsBySourceName(String name) {
        if (!StringUtils.hasLength(name)) name = DataSourceConstant.MASTER;

        String def = StoreDynamicDataSource.getDataSourceName();
        if (def.equals(name)) return;

        // thread local 給每一個線程存一個數據元名稱供後續線程使用
        // 切換數據元名稱，就是改變 thread local 裡面的數據元名字
        StoreDynamicDataSource.setDataSourceName(name);
    }

}
