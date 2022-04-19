package com.sugar.lost.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sugar.base.result.R;
import com.sugar.lost.entity.Callback;
import com.sugar.lost.entity.Category;
import com.sugar.lost.service.CallbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 方糖
 * @since 2022-02-10
 */
@RestController
@RequestMapping("/lost/callback")
@CrossOrigin
@Api(tags = "反馈管理")
public class CallbackController {

    @Resource
    private CallbackService callbackService;

    @GetMapping("/getList")
    @ApiOperation("反馈列表")
    public R getList(long cur, long size) {
        Page<Callback> page = new Page<>(cur,size);
        QueryWrapper<Callback> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("status");
        wrapper.orderByDesc("createtime");
        callbackService.page(page,wrapper);
        long total = page.getTotal();
        return R.ok().data("callbackList",page.getRecords()).data("total",total);
    }

    @GetMapping("getInfo")
    @ApiOperation("反馈信息")
    public R getInfo(String callbackId) {
        return R.ok().data("category",callbackService.getById(callbackId));
    }

    @PostMapping("add")
    @ApiOperation("添加反馈")
    // TODO 根据token 解析学生信息
    public R add(@RequestBody Callback callback, HttpServletRequest request) {
        String token = request.getHeader("token");
        System.out.println(token);
        callback.setStuid(token);
        callback.setStatus(0);
        callback.setIsDelete(0);
        return R.ok().data("success",callbackService.save(callback));
    }

    @PostMapping("del")
    @ApiOperation("删除反馈")
    public R del(String callbackId) {
        return R.ok().data("success",callbackService.removeById(callbackId));
    }
}

