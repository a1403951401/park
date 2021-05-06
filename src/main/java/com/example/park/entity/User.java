package com.example.park.entity;


import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String display_name;
    private String telephone;
    private Boolean enabled;
    private Double created;
    private Double lastModified;


}
