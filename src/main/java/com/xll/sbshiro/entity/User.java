package com.xll.sbshiro.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户表
 */
@Data
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /* 用户名 */
    private String userName;

    /* 密码  不加密 */
    private String password;

    /* 角色，数据字典 */
    private String role;

    @TableId("uid")
    private Integer uid;

    @TableField("uname")
    private String uname;

    @TableField("pwd")
    private String pwd;

    @TableField("rid")
    private Integer rid;

    @TableField("lastlogin")
    private Date lastlogin;
}
