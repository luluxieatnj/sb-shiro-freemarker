package com.xll.sbshiro.utils;

import java.util.*;

/**
 *  角色和权限   字典
 *    模拟从数据库查询用户的角色和权限
 */
public class RoleUtil {

    // 角色  超级管理员  普通管理员  用户
    private static final List<String> roles = Arrays.asList("sys", "admin", "user");

    // 权限  增删改查
    private static final List<String> pers = Arrays.asList("add", "delete", "update", "select");

    /** 角色与权限对应关系   key为 角色名  value为 权限集合 */
    private static Map<String, Set> persMap = null;
    /** 用户与角色对应关系   key为 用户名   value为角色名 */
    private static Map<String, String> userMap = null;

    static {
        try{
            persMap = new HashMap<>();
            persMap.put("sys", new HashSet(pers));
            persMap.put("admin", new HashSet(Arrays.asList("add", "select")));
            persMap.put("user", new HashSet(Arrays.asList("select")));
            roles.forEach(r -> userMap.put(r,r));
            userMap.put("user01", "user");
            userMap.put("user02", "user");
            userMap.put("user03", "user");
        }catch (Exception e) {
            System.out.println("初始化角色 权限字典失败");
        }
    }

    // 根据用户名 获取角色列表
    public static String getRoleByName(String username) {
        return userMap.get(username);
    }

    public static Set<String> getPersByName(String username) {
        return persMap.get(username);
    }

}
