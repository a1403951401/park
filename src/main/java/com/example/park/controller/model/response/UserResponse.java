package com.example.park.controller.model.response;

import com.example.park.repository.mysql.entity.UserDO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserResponse {
    private List<UserDO> user;

    private Integer count;
}
