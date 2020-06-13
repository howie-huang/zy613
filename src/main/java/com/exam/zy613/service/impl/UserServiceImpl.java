package com.exam.zy613.service.impl;

import com.exam.zy613.entity.User;
import com.exam.zy613.mapper.UserMapper;
import com.exam.zy613.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author howie-huang
 * @since 2020-06-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
