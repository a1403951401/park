package com.example.park.repository.mysql.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user")
public class UserDo implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("display_name")
    private String display_name;

    @TableField(value = "telephone")
    private String telephone;

    @TableField(value = "enabled")
    private Boolean enabled;

    @TableField("created")
    private Double created;

    @TableField("last_modified")
    private Double lastModified;
}
