package com.example.park.controller;


import com.baomidou.kaptcha.Kaptcha;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.park.biz.IUserService;
import com.example.park.controller.model.response.Response;
import com.example.park.controller.model.response.UserResponse;
import com.example.park.repository.mysql.entity.UserDO;
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
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private IUserService userService;

    @Autowired
    private Kaptcha kaptcha;


    @ApiOperation("查询用户")
    @GetMapping("/")
    public Response getUser(
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "license_id", required = false) String licenseId,
            @RequestParam(name = "is_admin", required = false) String isAdmin,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "current_page", required = false, defaultValue = "1") Integer current,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Integer size
    ) {
        // 分页
        IPage<UserDO> page = userService.page(new Page<>(current, size));
        // 查询条件
        LambdaQueryWrapper<UserDO> wrapper = new LambdaQueryWrapper<>();
        if (username != null && !username.equals("")) {
            wrapper = wrapper.like(UserDO::getUsername, username);
        }
        if (name != null && !name.equals("")) {
            wrapper = wrapper.like(UserDO::getName, name);
        }
        if (licenseId != null && !licenseId.equals("")) {
            wrapper = wrapper.like(UserDO::getLicenseId, licenseId);
        }
        if (phone != null && !phone.equals("")) {
            wrapper = wrapper.like(UserDO::getPhone, phone);
        }

        return Response.success(new UserResponse(
                userService.page(page, wrapper).getRecords(),
                userService.count(wrapper)
        ));
    }


    @ApiOperation("删除用户信息")
    @DeleteMapping("/{id}")
    public Response deleteUser(@PathVariable Integer id) {
        if (userService.removeById(id)) {
            return Response.success("删除成功");
        }
        return Response.error("创建失败");
    }


}

