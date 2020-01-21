package com.xll.sbshiro.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xll.sbshiro.entity.User;
import com.xll.sbshiro.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/{username}")
    @ResponseBody
    public User getUserByName(@PathVariable("username") String username){

        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("uname", username);

        User user = userService.getOne(wrapper);
        return user;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password){
        log.info("username = {}, password = {}", username, password);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            log.error("controller login err = {}", e.getMessage());
            return "login";
        }
        return "home";
    }
    @RequestMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "login";
    }
}
