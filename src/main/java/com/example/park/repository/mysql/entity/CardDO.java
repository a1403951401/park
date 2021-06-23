package com.example.park.repository.mysql.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author N.E
 * @since 2021-06-19
 */
@Data
@AllArgsConstructor
@TableName("card")
public class CardDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private BigDecimal cost;

    @JsonProperty("rentable_before")
    private LocalDate rentableBefore;

    @JsonProperty("rentable_after")
    private LocalDate rentableAfter;

    public CardDO(String name, BigDecimal cost, LocalDate rentableBefore, LocalDate rentableAfter) {
        this.name = name;
        this.cost = cost;
        this.rentableBefore = rentableBefore;
        this.rentableAfter = rentableAfter;
    }
}
