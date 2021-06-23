package com.example.park.repository.mysql.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
@TableName("comment")
public class CommentDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @JsonProperty("card_id")
    private Integer cardId;

    private String details;

    private Integer like;

    private LocalDateTime time;
}
