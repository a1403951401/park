package com.example.park.repository.mysql.dao;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import java.time.LocalDate;

import com.example.park.repository.mysql.entity.CardDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author N.E
 * @since 2021-06-19
 */
public interface CardMapper extends BaseMapper<CardDO> {
    List<CardDO> selectAllByNameAndCostAndRentableAfterAndRentableBefore(@Param("name") String name, @Param("cost") BigDecimal cost, @Param("rentableAfter") LocalDate rentableAfter, @Param("rentableBefore") LocalDate rentableBefore);
}
