package com.xll.sbshiro.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/home")
    @RequiresPermissions("delete")
    public String home() {

        Subject subject = SecurityUtils.getSubject();
        boolean add =subject.isPermitted("add");
        boolean delete = subject.isPermitted("delete");
        boolean update = subject.isPermitted("update");
        boolean select = subject.isPermitted("select");
        log.info("--------------------------------subject.hasRole(add) = {}", add);
        log.info("--------------------------------subject.hasRole(delete) = {}", delete);
        log.info("--------------------------------subject.hasRole(update) = {}", update);
        log.info("--------------------------------subject.hasRole(select) = {}", select);

        return "home";
    }

}
