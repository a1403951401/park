package com.example.park.repository.mysql.entity;

import java.math.BigDecimal;
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
@TableName("card")
public class CardDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private BigDecimal cost;

    private LocalDate rentableBefore;

    private LocalDate rentableAfter;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
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
        return "CardDO{" +
        "id=" + id +
        ", name=" + name +
        ", cost=" + cost +
        ", rentableBefore=" + rentableBefore +
        ", rentableAfter=" + rentableAfter +
        "}";
    }
}
