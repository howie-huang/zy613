package com.exam.zy613.mapper;

import com.exam.zy613.entity.Menu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author howie-huang
 * @since 2020-06-13
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据登录名查询权限
     * @param username
     * @return list
     */
    List<Menu> selectMenuByName(String username);
}
