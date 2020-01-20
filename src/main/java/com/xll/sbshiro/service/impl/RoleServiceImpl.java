package com.xll.sbshiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xll.sbshiro.entity.Role;
import com.xll.sbshiro.mapper.IRoleMapper;
import com.xll.sbshiro.service.IRoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<IRoleMapper, Role> implements IRoleService {
}
