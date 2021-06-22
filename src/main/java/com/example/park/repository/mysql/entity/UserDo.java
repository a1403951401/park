package com.example.park.repository.mysql.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author N.E
 * @since 2021-06-19
 */
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "UserDO{" +
                "id=" + id +
                ", username=" + username +
                ", password=" + password +
                ", name=" + name +
                ", licenseId=" + licenseId +
                ", isAdmin=" + isAdmin +
                ", phone=" + phone +
                "}";
    }
}
