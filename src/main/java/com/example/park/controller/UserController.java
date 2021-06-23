package com.example.park.controller;


import com.baomidou.kaptcha.Kaptcha;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.example.park.biz.IUserService;
import com.example.park.controller.model.request.UserUpdateModel;
import com.example.park.controller.model.response.Response;
import com.example.park.repository.mysql.entity.UserDO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author N.E
 * @since 2021-06-19
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private IUserService userService;

    @Autowired
    private Kaptcha kaptcha;


//    @ApiOperation("用户注册")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "path"),
//            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "body"),
//            @ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "String", paramType = "body"),
//            @ApiImplicitParam(name = "license_id", value = "驾照号码", required = true, dataType = "String", paramType = "body"),
//            @ApiImplicitParam(name = "is_admin", value = "是否管理员", required = true, dataType = "Integer", paramType = "body"),
//            @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String", paramType = "body"),
//            @ApiImplicitParam(name = "code", value = "验证码", required = true, dataType = "String", paramType = "body")
//    })
//    @PutMapping("/user/{username}")
//    public Response userUpdate(@PathVariable String username, @RequestBody UserUpdateModel user) {
//        try {
//            kaptcha.validate(user.getCode(), 600);
//        } catch (KaptchaIncorrectException | KaptchaNotFoundException | NullPointerException e) {
//            return Response.error("验证码有误");
//        }
//        UserDO u = userService.selectOneByUsername(username);
//        if (u == null) {
//            return Response.error("用户不存在");
//        }
//        if (user.getPassword() != null && !user.getPassword().equals("")) {
//            u.setPassword(user.getPassword());
//        }
//
//        if (userService.updateById(u)) {
//            return Response.success(u);
//        }
//
//        return Response.error("更新失败");
//    }

}

