package com.example.park.repository.mysql.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author N.E
 * @since 2021-06-19
 */
@TableName("rent")
public class RentDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "ident", type = IdType.AUTO)
    private Integer ident;

    private Integer userId;

    private Integer cardId;

    private LocalDate rentableBefore;

    private LocalDate rentableAfter;


    public Integer getIdent() {
        return ident;
    }

    public void setIdent(Integer ident) {
        this.ident = ident;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public LocalDate getRentableBefore() {
        return rentableBefore;
    }

    public void setRentableBefore(LocalDate rentableBefore) {
        this.rentableBefore = rentableBefore;
    }

    public LocalDate getRentableAfter() {
        return rentableAfter;
    }

    public void setRentableAfter(LocalDate rentableAfter) {
        this.rentableAfter = rentableAfter;
    }

    @Override
    public String toString() {
        return "RentDO{" +
        "ident=" + ident +
        ", userId=" + userId +
        ", cardId=" + cardId +
        ", rentableBefore=" + rentableBefore +
        ", rentableAfter=" + rentableAfter +
        "}";
    }
}
