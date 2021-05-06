package com.example.park.biz;


import com.example.park.entity.User;
import com.example.park.repository.mysql.entity.UserDo;

import java.util.List;

public interface IUserService {
    UserDo get(String username, String password);
}