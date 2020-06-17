package com.exam.zy613.service.impl;

import com.exam.zy613.entity.Menu;
import com.exam.zy613.entity.User;
import com.exam.zy613.mapper.UserMapper;
import com.exam.zy613.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.exam.zy613.util.LayUiTree;
import com.exam.zy613.util.MenuTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
