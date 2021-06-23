package com.example.park.controller;


import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.security.token.SSOToken;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.park.biz.ICardService;
import com.example.park.biz.IRentService;
import com.example.park.biz.IUserService;
import com.example.park.controller.model.response.RentResponse;
import com.example.park.controller.model.response.Response;
import com.example.park.repository.mysql.entity.CardDO;
import com.example.park.repository.mysql.entity.RentDO;
import com.example.park.repository.mysql.entity.UserDO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author N.E
 * @since 2021-06-19
 */
@RestController
@RequestMapping("/api/rent")
public class RentController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICardService cardService;

    @Autowired
    private IRentService rentService;

    @Autowired
    protected HttpServletRequest request;

    @ApiOperation("查询订单")
    @GetMapping("/")
    public Response getUser(
            @RequestParam(name = "current_page", required = false, defaultValue = "1") Integer current,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Integer size
    ) {
        // 分页
        IPage<RentDO> page = rentService.page(new Page<>(current, size));

        return Response.success(new RentResponse(
                rentService.page(page).getRecords(),
                rentService.count()
        ));
    }

    @ApiOperation("租用车辆")
    @PostMapping("/{id}")
    public Response createCard(@PathVariable Integer id) {
        SSOToken token = SSOHelper.getSSOToken(request);

        CardDO card = cardService.getOne(new LambdaQueryWrapper<CardDO>().eq(CardDO::getId, id));
        if (card == null) {
            return Response.error("车辆信息不存在");
        }
        UserDO user = userService.getOne(new LambdaQueryWrapper<UserDO>().eq(UserDO::getUsername, token.getId()));
        RentDO r = new RentDO(user.getId(), card.getId(), card.getCost(), card.getRentableAfter(), card.getRentableBefore());
        if (rentService.save(r)) {
            return Response.success(r);
        }
        return Response.error("租车失败");
    }
}

