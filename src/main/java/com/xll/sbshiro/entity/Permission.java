package com.xll.sbshiro.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * <p>
 * 资源表
 * </p>
 *
 * @author LYH
 * @since 2016-12-28
 */
@Data
@TableName("t_permission")
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId("pid")
    private Integer pid;

    @TableField("pname")
    private String pname;

    @TableField("code")
    private String code;

    @TableField("url")
    private String url;

    @TableField("des")
    private String des;
}
