package com.example.park.controller;

import com.example.park.biz.IUserService;
import com.example.park.controller.model.request.UserLoginModel;
import com.example.park.repository.mysql.entity.UserDo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IUserService userService;

    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "body"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "body")
    })
    @PostMapping("/login")
    public String login(@RequestBody UserLoginModel user) {
        UserDo u = userService.get(user.getUsername(), user.getPassword());
        return "OK";
    }
}