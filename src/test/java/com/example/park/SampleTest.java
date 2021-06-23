package com.example.park;

import com.example.park.repository.mysql.dao.UserMapper;
import com.example.park.repository.mysql.entity.UserDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class SampleTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<UserDO> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

}