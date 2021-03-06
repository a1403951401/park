package com.example.park.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.park.biz.ICardService;
import com.example.park.controller.model.request.CardModel;
import com.example.park.controller.model.response.CardResponse;
import com.example.park.controller.model.response.Response;
import com.example.park.repository.mysql.entity.CardDO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    @GetMapping("/")
    public Response getCard(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "cost", required = false) BigDecimal cost,
            @RequestParam(name = "rentable_after", required = false) String rentableAfter,
            @RequestParam(name = "rentable_before", required = false) String rentableBefore,
            @RequestParam(name = "current_page", required = false, defaultValue = "1") Integer current,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Integer size
    ) {
        // 分页
        IPage<CardDO> page = cardService.page(new Page<>(current, size));
        // 查询条件
        LambdaQueryWrapper<CardDO> wrapper = new LambdaQueryWrapper<>();
        if (name != null && !name.equals("")) {
            wrapper = wrapper.like(CardDO::getName, name);
        }
        if (cost != null && !cost.equals(BigDecimal.ZERO)) {
            wrapper = wrapper.eq(CardDO::getCost, cost);
        }
        if (rentableAfter != null && !rentableAfter.equals("")) {
            LocalDate afterDate = LocalDate.parse(rentableAfter, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            wrapper = wrapper.ge(CardDO::getRentableAfter, afterDate)
                    .ge(CardDO::getRentableBefore, afterDate);
        }
        if (rentableBefore != null && !rentableBefore.equals("")) {
            LocalDate beforeDate = LocalDate.parse(rentableBefore, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            wrapper = wrapper.le(CardDO::getRentableAfter, beforeDate)
                    .le(CardDO::getRentableBefore, beforeDate);
        }

        return Response.success(new CardResponse(
                cardService.page(page, wrapper).getRecords(),
                cardService.count(wrapper)
        ));
    }


    @ApiOperation("创建车辆信息")
    @PostMapping("/")
    public Response createCard(@RequestBody CardModel card) {
        CardDO c = new CardDO(card.getName(), card.getCost(), card.getRentableAfter(), card.getRentableBefore());
        if (cardService.save(c)) {
            return Response.success(c);
        }
        return Response.error("创建失败");
    }


    @ApiOperation("删除车辆信息")
    @DeleteMapping("/{id}")
    public Response deleteCard(@PathVariable Integer id) {
        if (cardService.removeById(id)) {
            return Response.success("删除成功");
        }
        return Response.error("创建失败");
    }


    @ApiOperation("修改车辆信息")
    @PutMapping("/{id}")
    public Response updateCard(@PathVariable Integer id, @RequestBody CardModel card) {
        CardDO c = cardService.getOne(new LambdaQueryWrapper<CardDO>().eq(CardDO::getId, id));
        if (c == null) {
            return Response.error("车辆信息不存在");
        }
        if (cardService.update(
                new LambdaUpdateWrapper<CardDO>()
                        .eq(CardDO::getId, id)
                        .set(CardDO::getName, card.getName())
                        .set(CardDO::getCost, card.getCost())
                        .set(CardDO::getRentableAfter, card.getRentableAfter())
                        .set(CardDO::getRentableAfter, card.getRentableAfter())
        )) {
            return Response.success("更新成功");
        }
        return Response.error("更新失败");
    }
}

