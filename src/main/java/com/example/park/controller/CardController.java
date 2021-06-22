package com.example.park.controller;


import com.example.park.biz.ICardService;
import com.example.park.controller.model.request.GetCardModel;
import com.example.park.controller.model.response.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author N.E
 * @since 2021-06-19
 */
@RestController
@RequestMapping("/api/card")
public class CardController {


    @Autowired
    private ICardService cardService;


    @ApiOperation("查询车辆信息")
    @PostMapping("/get_card")
    public Response token(@RequestBody GetCardModel card) {
        return Response.success(card);
    }
}

