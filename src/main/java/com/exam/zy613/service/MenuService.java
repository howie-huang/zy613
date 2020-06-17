package com.exam.zy613.service;

import com.exam.zy613.entity.Menu;
import com.baomidou.mybatisplus.service.IService;
import com.exam.zy613.util.LayUiTree;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author howie-huang
 * @since 2020-06-13
 */
public interface MenuService extends IService<Menu> {
    /**
     * 根据登录名查询权限
     * @param username
     * @return list
     */
    List<LayUiTree> selectMenuByName(String username);
}
