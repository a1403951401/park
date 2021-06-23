package com.example.park.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.park.biz.ICardService;
import com.example.park.repository.mysql.dao.CardMapper;
import com.example.park.repository.mysql.entity.CardDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author N.E
 * @since 2021-06-19
 */
@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, CardDO> implements ICardService {

    @Autowired
    private CardMapper cardMapper;
}
