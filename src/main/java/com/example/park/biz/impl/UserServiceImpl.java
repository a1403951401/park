package com.example.park.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.park.biz.IUserService;
import com.example.park.repository.mysql.dao.UserMapper;
import com.example.park.repository.mysql.entity.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author N.E
 * @since 2021-06-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements IUserService {

    @Autowired
    private UserMapper userDao;


    @Override
    public UserDO selectOneByUsername(@Param("username") String username) {
        return userDao.selectOneByUsername(username);
    }

    @Override
    public UserDO selectOneByUsernameAndPassword(@Param("username") String username, @Param("password") String password) {
        return userDao.selectOneByUsernameAndPassword(username, password);
    }

    @Override
    public int insertAll(UserDO userDO) {
        return userDao.insertAll(userDO);
    }
}
