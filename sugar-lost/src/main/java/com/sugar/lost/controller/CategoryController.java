package com.sugar.lost.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sugar.base.result.R;
import com.sugar.lost.entity.Category;
import com.sugar.lost.service.CategoryService;
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
@RequestMapping("/lost/category")
@CrossOrigin
@Api(tags = "分类管理")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getList")
    @ApiOperation("分类列表")
    public R getList() {
        QueryWrapper<Category> wrapper = new QueryWrapper<Category>();
        wrapper.orderByDesc("level");
        return R.ok().data("list",categoryService.list(wrapper));
    }

    @GetMapping("getInfo")
    @ApiOperation("分类信息")
    public R getInfo(String categoryId) {
        return R.ok().data("category",categoryService.getById(categoryId));
    }

    @PostMapping("add")
    @ApiOperation("添加分类")
    public R add(@RequestBody Category category) {
        return R.ok().data("success",categoryService.save(category));
    }

    @PostMapping("del")
    @ApiOperation("删除分类")
    public R del(String categoryId) {
        return R.ok().data("success",categoryService.removeById(categoryId));
    }

    @PostMapping("edit")
    @ApiOperation("编辑分类")
    public R edit(@RequestBody Category category) {
        return R.ok().data("success",categoryService.editCategory(category));
    }

}

