package com.xll.sbshiro.service;

import com.xll.sbshiro.entity.Permission;

import java.util.List;
import java.util.Set;

public interface IPermissionService {
    List<Permission> findAll();

    Set<String> findByName(String username);

}
