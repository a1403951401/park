package com.example.park.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.park.biz.ICardService;
import com.example.park.repository.mysql.dao.CardMapper;
import com.example.park.repository.mysql.entity.CardDO;
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
public class CardServiceImpl extends ServiceImpl<CardMapper, CardDO> implements ICardService {

}
