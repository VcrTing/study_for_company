package org.jeecg.zeng.model.entity.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("form")
@ApiModel("表单类")
public class Form implements Serializable {

    public static final long  serialVersionUID =1L;

    @TableId(type = IdType.AUTO)
    private Long formId;
    @ApiModelProperty(value = "表单名称")
    private String formName;
    @ApiModelProperty(value = "前端路由")
    private String codeUrl;
    @ApiModelProperty(value = "表单结构")
    private String jsonObj;
}
