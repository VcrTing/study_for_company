package com.zeng.model.form.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@TableName("form")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormSchema implements Serializable {

    public static final long  serialVersionUID =1L;

    @TableId(type = IdType.AUTO)
    private Long formId;
    private String formName;
    private String codeUrl;
    private String jsonObj;

}
