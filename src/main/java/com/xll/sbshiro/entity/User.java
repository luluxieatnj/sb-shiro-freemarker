package com.xll.sbshiro.entity;

import java.io.Serializable;
import lombok.Data;


/**
 * 用户表
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String userName;

    private String password;

}
