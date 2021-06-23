package com.example.park.controller.model.response;

import com.example.park.repository.mysql.entity.RentDO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RentResponse {
    private List<RentDO> rent;

    private Integer count;
}
