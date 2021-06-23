package com.example.park.controller.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserUpdateModel {

    private String password;

    private String code;

    private String name;

    @JsonProperty("license_id")
    private String licenseId;

    @JsonProperty("is_admin")
    private Integer isAdmin;

    private String phone;

}
