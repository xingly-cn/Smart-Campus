package com.sugar.lost.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sugar.base.result.R;
import com.sugar.base.utils.JwtUtils;
import com.sugar.base.utils.RedisUtil;
import com.sugar.lost.entity.Category;
import com.sugar.lost.entity.Good;
import com.sugar.lost.entity.School;
import com.sugar.lost.entity.Stu;
import com.sugar.lost.entity.vo.GoodVo;
import com.sugar.lost.service.CategoryService;
import com.sugar.lost.service.GoodService;
import com.sugar.lost.service.SchoolService;
import com.sugar.lost.service.StuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 方糖
 * @since 2022-02-10
 */
@RestController
@RequestMapping("/lost/good")
@CrossOrigin
@Api(tags = "物品管理")
public class GoodController {

    @Resource
    private GoodService goodService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private StuService stuService;

    @Resource
    private SchoolService schoolService;

    @Resource
    private RedisUtil redisUtil;

    @GetMapping("getAllList")
    @ApiOperation("物品列表")
    public R getAllList(long cur, long size, String categoryId, String keyword, HttpServletRequest request) {
        String userId = request.getHeader("userId");
        Page<Good> page = new Page<>(cur,size);
        QueryWrapper<Good> wrapper = new QueryWrapper<>();
        if (userId != null) wrapper.eq("user_id",userId);
        if (keyword != null && !keyword.isEmpty()) wrapper.like("title",keyword);
        if (categoryId != null && !categoryId.isEmpty()) wrapper.eq("category",categoryId);
        wrapper.orderByAsc("tags");
        wrapper.orderByDesc("createtime");
        wrapper.orderByDesc("look");
        goodService.page(page,wrapper);
        long total = page.getTotal();
        return R.ok().data("list",page.getRecords()).data("total",total);
    }

    @GetMapping("getInfo")
    @ApiOperation("物品信息")
    public R getInfo(String goodId) {
        redisUtil.incr("goodId-" + goodId,1);
        Good good = goodService.getById(goodId);
        String categoryId = good.getCategory();
        Category category = categoryService.getById(categoryId);
        good.setCategory(category.getName());
        return R.ok().data("good",good);
    }

    @PostMapping("add")
    @ApiOperation("添加物品")
    public R add(@RequestBody GoodVo goodVo,HttpServletRequest request) {
        String userId = request.getHeader("userId");
        Good good = new Good();
        BeanUtils.copyProperties(goodVo,good);
        good.setUserId(userId);
        Stu stu = stuService.getById(userId);
        good.setName(stu.getName());

        String schoolname = stu.getSchoolname();
        QueryWrapper<School> wrapper = new QueryWrapper<>();
        wrapper.eq("name",schoolname);
        School school = schoolService.getOne(wrapper);
        good.setSchoolid(school.getId());
        return R.ok().data("success",goodService.saveGood(good));
    }

    @PostMapping("del")
    @ApiOperation("删除物品")
    public R del(String goodId) {
        return R.ok().data("success",goodService.removeById(goodId));
    }

    @PostMapping("edit")
    @ApiOperation("编辑物品")
    public R edit(@RequestBody Good good) {
        return R.ok().data("success",goodService.editGood(good));
    }

    //TODO 只有自己发布的物品,才可以标记为已找到. 使用 request 判断 header 的 token 与物品发布者姓名匹配即可
    @PostMapping("find")
    @ApiOperation("物品找到")
    public R find(String goodId) {
        return R.ok().data("success",goodService.findGood(goodId));
    }
}

