package com.example.park.repository.mysql.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@NoArgsConstructor
@TableName("user")
public class UserDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String name;

    @JsonProperty("license_id")
    private String licenseId;

    @JsonProperty("is_admin")
    private Integer isAdmin;

    private String phone;

    public UserDO(String username, String password, String name, String licenseId, String phone) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.licenseId = licenseId;
        this.phone = phone;
        this.isAdmin = 0;
    }
}
