package com.xll.sbshiro.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * <p>
 * 角色表
 * </p>
 *
 * @author LYH
 * @since 2016-12-28
 */
@Data
@TableName("t_role")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId("rid")
    private Integer rid;

    @TableField("rname")
    private String rname;

    @TableField("code")
    private String code;

    @TableField("type")
    private Integer type;
}
