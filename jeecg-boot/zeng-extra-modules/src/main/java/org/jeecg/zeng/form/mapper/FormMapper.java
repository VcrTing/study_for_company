package org.jeecg.zeng.form.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.jeecg.modules.form.entity.Form;
import org.jeecg.zeng.model.entity.form.Form;
import org.springframework.stereotype.Repository;

@Mapper
public interface FormMapper extends BaseMapper<Form>{

}
