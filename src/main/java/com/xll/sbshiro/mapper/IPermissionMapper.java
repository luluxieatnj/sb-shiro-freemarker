package com.xll.sbshiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xll.sbshiro.entity.Permission;
import com.xll.sbshiro.entity.User;

import java.util.List;

public interface IPermissionMapper extends BaseMapper<Permission> {

    List<Permission> getListByRid(Integer rid);

    List<Permission> getListByUname(String uname);
}
