package com.example.park.repository.mysql.dao;

import org.apache.ibatis.annotations.Param;

import com.example.park.repository.mysql.entity.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author N.E
 * @since 2021-06-19
 */
public interface UserMapper extends BaseMapper<UserDO> {
    UserDO selectOneByUsername(@Param("username") String username);

    UserDO selectOneByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    int insertAll(UserDO userDO);
}
