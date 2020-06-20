package com.exam.zy613.service.impl;

import com.exam.zy613.entity.Menu;
import com.exam.zy613.mapper.MenuMapper;
import com.exam.zy613.service.MenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.exam.zy613.util.LayUiTree;
import com.exam.zy613.util.MenuTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
   @Autowired
   private MenuMapper menuMapper;
    /**
     * 根据登录名查询权限
     * 实现接口
     * @param loginName
     * @return list
     */
    @Override
    public List<LayUiTree> selectMenuByName(String loginName) {
        List<Menu> list = menuMapper.selectMenuByName(loginName);
        return MenuTree.menuTranTree(list);
    }
}
