package com.example.park.biz.impl;

import com.example.park.repository.mysql.entity.RentDO;
import com.example.park.repository.mysql.dao.RentMapper;
import com.example.park.biz.IRentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author N.E
 * @since 2021-06-19
 */
@Service
public class RentServiceImpl extends ServiceImpl<RentMapper, RentDO> implements IRentService {

}
