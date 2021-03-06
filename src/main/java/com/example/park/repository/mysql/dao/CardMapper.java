package com.example.park.repository.mysql.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.park.repository.mysql.entity.CardDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author N.E
 * @since 2021-06-19
 */
@Repository
public interface CardMapper extends BaseMapper<CardDO> {
}