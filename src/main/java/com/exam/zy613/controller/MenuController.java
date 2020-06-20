package com.exam.zy613.controller;


import com.exam.zy613.service.MenuService;
import com.exam.zy613.util.LayUiTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author howie
 * @Description  菜单操作
 * @date 2020/6/20 13:53
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    /**
     * @author howie
     * @Description  查询所有菜单
     * @date 2020/6/20 13:53
     * @return list
     */
    @RequestMapping("/findAllMenus")
    @ResponseBody
    public List<LayUiTree> findMenus(){
        List<LayUiTree> layUiTrees = menuService.selectMenuByName(null);
        return layUiTrees;
    }
}

