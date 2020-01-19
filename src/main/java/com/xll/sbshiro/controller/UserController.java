package com.xll.sbshiro.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xll.sbshiro.entity.User;
import com.xll.sbshiro.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
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

}
