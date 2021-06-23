package com.example.park;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan(value = {"com.example.park.repository.mysql.dao"})
class ParkApplicationTests {

    @Test
    void contextLoads() {
    }

}
