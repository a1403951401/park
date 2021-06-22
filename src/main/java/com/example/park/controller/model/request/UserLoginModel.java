package com.example.park.controller.model.request;

import lombok.Data;

@Data
public class UserLoginModel {

    private String username;

    private String password;

    private String code;

}
