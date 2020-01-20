package com.xll.sbshiro.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xll.sbshiro.entity.User;
import com.xll.sbshiro.mapper.IUserMapper;
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
public class UserServiceImpl extends ServiceImpl<IUserMapper, User> implements IUserService {


}