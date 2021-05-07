package com.example.park.biz.impl;

import com.example.park.biz.IUserService;
import com.example.park.repository.mysql.dao.UserMapper;
import com.example.park.repository.mysql.entity.UserDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userDao;

    @Override
    public UserDo get(String username, String password) {
        return userDao.selectAllByUsernameAndPassword(username, password);
    }
}
