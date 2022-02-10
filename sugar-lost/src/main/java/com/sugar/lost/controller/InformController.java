package com.sugar.lost.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sugar.base.result.R;
import com.sugar.lost.entity.Inform;
import com.sugar.lost.entity.School;
import com.sugar.lost.service.InformService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 方糖
 * @since 2022-02-10
 */
@RestController
@RequestMapping("/lost/inform")
@CrossOrigin
@Api(tags = "公告管理")
public class InformController {

    @Autowired
    private InformService informService;

    @GetMapping("getList")
    @ApiOperation("公告列表")
    public R getList(long cur, long size) {
        Page<Inform> page = new Page<Inform>(cur,size);
        informService.page(page,null);
        long total = page.getTotal();
        return R.ok().data("informList",page.getRecords()).data("total",total);
    }

    @GetMapping("getInfo")
    @ApiOperation("公告信息")
    public R getInfo(String categoryId) {
        QueryWrapper<Inform> wrapper = new QueryWrapper<>();
        wrapper.eq("categoryid",categoryId);
        return R.ok().data("informList",informService.list(wrapper));
    }

    @PostMapping("add")
    @ApiOperation("添加公告")
    public R add(@RequestBody Inform inform) {
        return R.ok().data("success",informService.save(inform));
    }
}

