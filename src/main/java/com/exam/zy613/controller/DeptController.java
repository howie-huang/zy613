package com.exam.zy613.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.exam.zy613.entity.Dept;
import com.exam.zy613.service.DeptService;
import com.exam.zy613.util.LayUIData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author howie-huang
 * @since 2020-06-13
 */
@Controller
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;
    @ResponseBody
    @RequestMapping("/selectPage")
    public LayUIData selectPage(Integer page,Integer limit){
        Page<Dept> pages = new Page<>(page,limit);
        Wrapper<Dept> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",0);
        int pageNum = deptService.selectCount(wrapper);
        Page<Dept> deptPage = deptService.selectPage(pages,wrapper);
        List<Dept> deptList = deptPage.getRecords();
        LayUIData layUIData = new LayUIData();
        layUIData.setCode(0);
        layUIData.setMessage("操作成功");
        layUIData.setCount(pageNum);
        layUIData.setData(deptList);
        return layUIData;
    }
    /**
     * 保存部门
     * @param dept
     * @return
     */
    @RequestMapping("/saveDept")
    @ResponseBody
    public Object saveDept(Dept dept){
        Map<String ,Object > map = new HashMap<>();
        dept.setCreateBy("root");
        dept.setCreateTime(new Date());
        boolean insert = deptService.insert(dept);
        if(insert){
            map.put("code",0);
            map.put("msg","保存成功");
        }else {
            map.put("code",1);
            map.put("msg","保存失败");
        }
        return map;
    }
    /**
     * 更新部门信息（controller中的方法作用1：页面跳转，2：收受参数，返回参数
     * 不建议在controller中加入过多的业务逻辑）
     * @param dept
     * @return
     */
    @RequestMapping("/updateDept")
    @ResponseBody
    public Object updateDept(Dept dept){
        Map<String ,Object > map = new HashMap<>();
        dept.setUpdateBy("root");
        dept.setUpdateTime(new Date());
        //此处待优化
        //if(null==dept.getStatus()){
            //dept.setStatus("1");
        //}

        boolean update = deptService.updateById(dept);
        if(update){
            map.put("code",0);
            map.put("msg","修改成功");
        }else {
            map.put("code",1);
            map.put("msg","修改失败");
        }
        return map;
    }
    @RequestMapping("/deleteDept")
    @ResponseBody
    public Object deleteDept(Dept dept){
        Map<String ,Object > map = new HashMap<>();
        //此处是逻辑删除，修改delflag
        dept.setDelFlag("1");
        boolean update = deptService.updateById(dept);
        if(update){
            map.put("code",0);
            map.put("msg","删除成功");
        }else {
            map.put("code",1);
            map.put("msg","删除失败");
        }
        return map;
    }
}

