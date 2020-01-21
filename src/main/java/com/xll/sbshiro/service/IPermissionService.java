package com.xll.sbshiro.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xll.sbshiro.entity.Permission;

import java.util.List;
import java.util.Set;

public interface IPermissionService extends IService<Permission> {

    /* 根据角色id查询权限列表 */
    List<Permission> getListByRid(Integer rid);

    /* 根据用户名查询权限列表 */
    List<Permission> getListByUname(String uname);
}
