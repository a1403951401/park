package com.example.park.biz;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.park.repository.mysql.entity.UserDO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author N.E
 * @since 2021-06-19
 */
public interface IUserService extends IService<UserDO> {
    UserDO selectOneByUsername(@Param("username") String username);

    UserDO selectOneByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    int insertAll(UserDO userDO);
}
