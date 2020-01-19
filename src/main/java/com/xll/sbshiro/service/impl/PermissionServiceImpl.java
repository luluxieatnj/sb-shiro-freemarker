package com.xll.sbshiro.service.impl;

import com.xll.sbshiro.entity.Permission;
import com.xll.sbshiro.service.IPermissionService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Override
    public List<Permission> findAll() {
        return null;
    }

    @Override
    public Set<String> findByName(String username) {
        Set<String> set = new HashSet<>();
        set.add("add");
        set.add("delete");
        set.add("update");
        set.add("select");
        return set;
    }
}
