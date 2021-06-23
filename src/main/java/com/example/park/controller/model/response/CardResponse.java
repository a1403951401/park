package com.example.park.controller.model.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.park.repository.mysql.entity.CardDO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CardResponse {
    private List<CardDO> card;

    private Integer count;
}
