package com.xll.sbshiro.entity;

import java.io.Serializable;
import lombok.Data;


/**
 * 用户表
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /* 用户名 */
    private String userName;

    /* 密码  不加密 */
    private String password;

    /* 角色，数据字典 */
    private String role;

}
