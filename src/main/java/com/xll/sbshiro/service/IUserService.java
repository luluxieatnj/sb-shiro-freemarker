package com.xll.sbshiro.service;

import com.xll.sbshiro.entity.User;

public interface IUserService {
    User findByUserName(String userName);
}
