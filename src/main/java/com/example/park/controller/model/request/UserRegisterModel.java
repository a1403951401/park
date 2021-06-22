package com.example.park.controller.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Data;

@Data
public class UserRegisterModel {

    private String username;

    private String password;

    private String code;

    private String name;

    @JsonProperty("license_id")
    private String licenseId;

    private String phone;

}
