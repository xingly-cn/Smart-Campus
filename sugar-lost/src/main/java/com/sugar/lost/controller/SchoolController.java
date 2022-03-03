package com.sugar.lost.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sugar.base.result.R;
import com.sugar.lost.entity.Category;
import com.sugar.lost.entity.School;
import com.sugar.lost.service.SchoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/lost/school")
@CrossOrigin
@Api(tags = "学校管理")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @GetMapping("getList")
    @ApiOperation("学校列表")
    public R getList(long cur, long size) {
        Page<School> page = new Page<School>(cur,size);
        QueryWrapper<School> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("createtime");
        schoolService.page(page,wrapper);
        long total = page.getTotal();
        return R.ok().data("schoolList",page.getRecords()).data("total",total);
    }

    @GetMapping("getInfo")
    @ApiOperation("学校信息")
    public R getInfo(String schoolId) {
        return R.ok().data("school",schoolService.getById(schoolId));
    }

    @PostMapping("add")
    @ApiOperation("添加学校")
    public R add(@RequestBody School school) {
        return R.ok().data("success",schoolService.save(school));
    }







}

