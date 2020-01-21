package com.xll.sbshiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xll.sbshiro.entity.Permission;
import com.xll.sbshiro.mapper.IPermissionMapper;
import com.xll.sbshiro.service.IPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PermissionServiceImpl extends ServiceImpl<IPermissionMapper, Permission> implements IPermissionService {

    @Override
    public List<Permission> getListByRid(Integer rid) {
        return baseMapper.getListByRid(rid);
    }

    @Override
    public List<Permission> getListByUname(String uname) {
        return baseMapper.getListByUname(uname);
    }
}
