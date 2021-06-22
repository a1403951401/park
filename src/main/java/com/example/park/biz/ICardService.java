package com.example.park.biz;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.park.repository.mysql.entity.CardDO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author N.E
 * @since 2021-06-19
 */
public interface ICardService extends IService<CardDO> {
    List<CardDO> selectAllByNameAndCostAndRentableAfterAndRentableBefore(@Param("name") String name, @Param("cost") BigDecimal cost, @Param("rentableAfter") LocalDate rentableAfter, @Param("rentableBefore") LocalDate rentableBefore);
}
