package com.xll.sbshiro.entity;

import java.io.Serializable;
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
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer pid;

    private String pname;

    private String code;

    private String url;

    private String des;
}
