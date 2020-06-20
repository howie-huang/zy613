package com.exam.zy613.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.exam.zy613.entity.Dept;
import com.exam.zy613.service.DeptService;
import com.exam.zy613.util.LayUIData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @Author howie
 * @Description  部门的操作
 * @Date 2020/6/20 13:48
 */
@Controller
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;
    /**
     * create by: howie
     * description: 查询部门
     * create time: 2020/6/18 13:37
     * 可以进行条件或分页查询
     * @param page
     * @param limit
     * @param searchDeptName
     * @param searchCreateUser
     * @param searchUpdateUser
     * @return layUiData
     */

    @ResponseBody
    @RequestMapping("/selectPage")
    public LayUIData selectPage(Integer page,Integer limit,String searchDeptName,String searchCreateUser,String searchUpdateUser){
        Page<Dept> pages = new Page<>(page,limit);
        Wrapper<Dept> wrapper = new EntityWrapper<>();
        //添加模糊查询的条件
        if(null!=searchDeptName&&!"".equals(searchDeptName)){
            wrapper.like("dept_name",searchDeptName);
        }
        if(null!=searchCreateUser&&!"".equals(searchCreateUser)){
            wrapper.like("create_by",searchCreateUser);
        }
        if(null!=searchUpdateUser&&!"".equals(searchUpdateUser)){
            wrapper.like("update_by",searchUpdateUser);
        }
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
     * @Author howie
     * @Description  保存部门
     * @Date 2020/6/20 13:49
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
     * @Author howie
     * @Description  修改数据，接受参数，更新数据
     * @Date 2020/6/20 13:50
     * @param dept
     * @return object
     */
    @RequestMapping("/updateDept")
    @ResponseBody
    public Object updateDept(Dept dept){
        Map<String ,Object > map = new HashMap<>();
        dept.setUpdateBy("root");
        dept.setUpdateTime(new Date());

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
    /**
     * @Author howie
     * @Description  逻辑删除部门
     * @Date 2020/6/20 13:51
     * @param dept
     * @return object
     */
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
    /**
     * @Author howie
     * @Description  批量删除
     * @Date 2020/6/20 13:52
     * @param list
     * @return object
     */
    @RequestMapping("/deleteMoreDept")
    @ResponseBody
    public Object deleteMoreDept(@RequestBody List<Dept> list){
        Map<String,Object> map = new HashMap<>();
        //逻辑删除只需要id和逻辑删除的数据，其他数据不需要，所以重新定义一个数组
        List<Dept> deptList = new ArrayList<>();
        try {
            for (Dept dept:list){
                Dept deptNew = new Dept();
                deptNew.setDelFlag("1");
                //取出id放入新的数组中
                deptNew.setDeptId(dept.getDeptId());
                deptList.add(deptNew);
            }
            boolean updateMore = deptService.updateBatchById(deptList);
            if(updateMore){
                map.put("code",0);
                map.put("msg","删除成功");
            }else {
                map.put("code",1);
                map.put("msg","删除失败");
            }
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}

