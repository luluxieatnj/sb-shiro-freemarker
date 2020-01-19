package com.xll.sbshiro.service.impl;


import com.xll.sbshiro.entity.User;
import com.xll.sbshiro.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户表  服务实现类
 * </p>
 *
 * @author LYH
 * @since 2016-12-28
 */
@Service
public class UserServiceImpl implements IUserService {

    @Override
    public User findByUserName(String userName) {
        // 造假数据  暂时不用dao层
        User user = new User();
        user.setUid(1001);
        user.setUserName("admin");
        user.setPassword("admin");
        return user;
    }
}