package com.example.park.repository.mysql.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.park.entity.User;
import com.example.park.repository.mysql.entity.UserDo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<UserDo> {
    UserDo selectAllByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}




