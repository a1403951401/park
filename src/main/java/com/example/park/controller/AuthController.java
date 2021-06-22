package com.example.park.controller;

import com.baomidou.kaptcha.Kaptcha;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.security.token.SSOToken;
import com.example.park.biz.IUserService;
import com.example.park.controller.model.request.UserLoginModel;
import com.example.park.controller.model.request.UserRegisterModel;
import com.example.park.controller.model.response.Response;
import com.example.park.repository.mysql.entity.UserDO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IUserService userService;

    @Autowired
    private Kaptcha kaptcha;

    @Autowired
    protected HttpServletRequest request;

    @Resource
    protected HttpServletResponse response;

    @ApiOperation("获取验证码")
    @GetMapping("/get_code")
    public void render() {
        kaptcha.render();
    }

    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "body"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "body"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, dataType = "String", paramType = "body")
    })
    @PostMapping("/login")
    public Response login(@RequestBody UserLoginModel user) {
        try {
            kaptcha.validate(user.getCode(), 600);
        } catch (KaptchaIncorrectException | KaptchaNotFoundException | NullPointerException e) {
            return Response.error("验证码有误");
        }
        UserDO u = userService.selectOneByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (u == null) {
            return Response.error("用户名密码错误");
        }
        SSOToken token = SSOToken.create()
                .setIp(request)
                .setId(user.getUsername());
        // 设置验证 session
        SSOHelper.setCookie(request, response, token);
        // 设置 username cookie
        Cookie userCookie = new Cookie("name", u.getName());
        userCookie.setPath("/");
        Cookie adminCookie = new Cookie("is_admin", u.getIsAdmin() == 1 ? "true" : "false");
        adminCookie.setPath("/");
        response.addCookie(userCookie);
        response.addCookie(adminCookie);
        return Response.success(u);
    }

    @ApiOperation("用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "body"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "body"),
            @ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "String", paramType = "body"),
            @ApiImplicitParam(name = "license_id", value = "驾照号码", required = true, dataType = "String", paramType = "body"),
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String", paramType = "body"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, dataType = "String", paramType = "body")
    })
    @PostMapping("/register")
    public Response register(@RequestBody UserRegisterModel user) {
        try {
            kaptcha.validate(user.getCode(), 600);
        } catch (KaptchaIncorrectException | KaptchaNotFoundException | NullPointerException e) {
            return Response.error("验证码有误");
        }
        if (userService.selectOneByUsername(user.getUsername()) != null) {
            return Response.error("用户已存在");
        }
        UserDO u = new UserDO(user.getUsername(), user.getPassword(), user.getName(), user.getLicenseId(), user.getPhone());
        if (userService.insertAll(u) == 1) {
            SSOToken token = SSOToken.create()
                    .setIp(request)
                    .setId(user.getUsername());
            // 设置验证 session
            SSOHelper.setCookie(request, response, token);
            // 设置 username cookie
            Cookie userCookie = new Cookie("name", u.getName());
            userCookie.setPath("/");
            Cookie adminCookie = new Cookie("is_admin", u.getIsAdmin() == 1 ? "true" : "false");
            adminCookie.setPath("/");
            response.addCookie(userCookie);
            response.addCookie(adminCookie);
            return Response.success(u);
        }
        return Response.error("注册失败");
    }

    @ApiOperation("查询当前用户信息")
    @GetMapping("/token")
    public Response token() {
        SSOToken token = SSOHelper.getSSOToken(request);
        if (null != token) {
            return Response.success(token.getId());
        }
        return Response.error("暂未登录");
    }

    @ApiOperation("退出登录")
    @GetMapping("/logout")
    public Response logout() {
        SSOHelper.clearLogin(request, response);
        Cookie userCookie = new Cookie("name", null);
        Cookie adminCookie = new Cookie("is_admin", null);
        userCookie.setPath("/");
        userCookie.setMaxAge(0);
        adminCookie.setPath("/");
        adminCookie.setMaxAge(0);
        response.addCookie(userCookie);
        response.addCookie(adminCookie);
        return Response.success("注销成功");
    }
}