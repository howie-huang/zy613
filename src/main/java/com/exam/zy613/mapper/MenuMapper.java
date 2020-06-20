package com.exam.zy613.mapper;

import com.exam.zy613.entity.Menu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author howie
 * @description  菜单dao层接口
 * @date 2020/6/20 13:54
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * @author howie
     * @description  selectMenuByName 根据登陆名查询菜单
     * @date 2020/6/20 14:11
     * @param loginName
     * @return list<Menu>
     */
    List<Menu> selectMenuByName(String loginName);
}
