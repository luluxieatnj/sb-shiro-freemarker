package com.xll.sbshiro.entity;

import java.io.Serializable;
import java.util.Date;

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
public class Role implements Serializable {
    private static final long serialVersionUID = -1894163644285296223L;

    private Integer rid;

    private String rname;

    private String code;

    private Integer type;
}
