package com.exam.zy613.util;

import com.exam.zy613.entity.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：howie
 * @date ：Created in 2020/6/17 15:32
 * @description：树形菜单工具类
 * @modified By：
 * @version: 1.0
 */
public class MenuTree {
    /**
     * create by: howie
     * description: 把list<Menu>转化为list<LayUiTree>
     * create time: 2020/6/17 15:35
     * 
     * @param list
     * @return list
     */
    
    public static List<LayUiTree> menuTranTree(List<Menu> list){
        List<LayUiTree> layUiTrees = new ArrayList<>();
        for (Menu menu:list){
            //如果父ID为0，则说明是一级菜单
            if (menu.getParentId()==0){
                //讲menu转成为tree
                LayUiTree tree = fromMenuToTree(menu);
                LayUiTree layUiTree = setTreeChild(tree,list);
                //找寻子
                layUiTrees.add(layUiTree);
            }
        }
        return layUiTrees;
    }
    /**
     * create by: howie
     * description: 查询子
     * create time: 2020/6/17 15:45
     *
     * @param tree
     * @param menuList
     * @return layUiTree
     */

    public static LayUiTree setTreeChild(LayUiTree tree,List<Menu> menuList){
        List<LayUiTree> children = new ArrayList<>();
        for (Menu menu:menuList){
            if (menu.getParentId()==tree.getId()){
                LayUiTree layUiTree = fromMenuToTree(menu);
                children.add(setTreeChild(layUiTree,menuList));
            }
        }
        tree.setChildren(children);
        return tree;
    }
    /**
     * create by: howie
     * description: 提取共用代码
     * create time: 2020/6/17 17:44
     *
     * @param menu
     * @return LayUiTree
     */

    public static LayUiTree  fromMenuToTree(Menu menu){
        LayUiTree layUiTree = new LayUiTree();
        layUiTree.setId(menu.getMenuId());
        layUiTree.setTitle(menu.getMenuName());
        layUiTree.setUrl(menu.getUrl());
        return layUiTree;
    }
}
