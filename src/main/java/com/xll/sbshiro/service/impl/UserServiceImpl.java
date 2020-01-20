package com.xll.sbshiro.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xll.sbshiro.entity.User;
import com.xll.sbshiro.mapper.IUserMapper;
import com.xll.sbshiro.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserServiceImpl extends ServiceImpl<IUserMapper, User> implements IUserService {

<<<<<<< HEAD
    @Override
    public User findByUserName(String userName) {
        // 造假数据  暂时不用dao层
        User user = new User();
        user.setUid(1001);
        user.setUserName("admin");
        user.setPassword("admin");
        return user;
    }
=======
>>>>>>> 51cfdbc7dce40777c53f15085fc80c9bd2181f7e
}