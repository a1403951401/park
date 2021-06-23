package com.example.park.repository.mysql.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.park.repository.mysql.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author N.E
 * @since 2021-06-19
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}
