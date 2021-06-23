package com.example.park.repository.mysql.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@NoArgsConstructor
@TableName("rent")
public class RentDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "ident", type = IdType.AUTO)
    private Integer ident;

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("card_id")
    private Integer cardId;

    @JsonProperty("rentable_before")
    private LocalDate rentableBefore;

    @JsonProperty("rentable_after")
    private LocalDate rentableAfter;

    private BigDecimal cost;

    public RentDO(Integer userId, Integer cardId, BigDecimal cost, LocalDate rentableBefore, LocalDate rentableAfter){
        this.userId = userId;
        this.cardId = cardId;
        this.cost = cost;
        this.rentableBefore = rentableBefore;
        this.rentableAfter = rentableAfter;
    }

}
