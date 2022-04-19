package com.sugar.lost.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sugar.base.result.R;
import com.sugar.base.utils.RedisUtil;
import com.sugar.lost.entity.Category;
import com.sugar.lost.entity.Stu;
import com.sugar.lost.service.StuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 方糖
 * @since 2022-02-10
 */
@RestController
@RequestMapping("/lost/stu")
@CrossOrigin
@Api(tags = "学生管理")
public class StuController {

    @Resource
    private StuService stuService;

    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/getList")
    @ApiOperation("学生列表")
    public R getList(long cur, long size) {
        Page<Stu> page = new Page<>(cur,size);
        QueryWrapper<Stu> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("verify");
        wrapper.orderByDesc("lastlogin");
        stuService.page(page,wrapper);
        long total = page.getTotal();
        return R.ok().data("stuList",page.getRecords()).data("total",total);
    }

    @GetMapping("/getInfo")
    @ApiOperation("学生信息")
    public R getInfo(HttpServletRequest request) {
        String stuId = request.getHeader("stuId");
        return R.ok().data("stu",stuService.getById(stuId));
    }

    @PostMapping("/del")
    @ApiOperation("删除学生")
    public R del(String stuId) {
        return R.ok().data("success",stuService.removeById(stuId));
    }

    @PostMapping("/edit")
    @ApiOperation("编辑学生")
    public R edit(@RequestBody Stu stu) {
        return R.ok().data("success",stuService.updateById(stu));
    }

    @PostMapping("/regist")
    @ApiOperation("注册学生")
    public R add(@RequestBody Stu stu) {
        QueryWrapper<Stu> wrapper = new QueryWrapper<>();
        wrapper.eq("id",stu.getId());
        int count = stuService.count(wrapper);
        if (count > 0) return R.error().message("账号已存在.");
        return R.ok().data("success",stuService.save(stu));
    }

    @PostMapping("/sendCode")
    @ApiOperation("发送认证码")
    public R sendCode(HttpServletRequest request) {
        String stuId = request.getHeader("stuId");
        String code_Redis = (String) redisUtil.get(stuId);
        if (code_Redis != null) return R.ok().message("发送太快了,请于" + redisUtil.getExpire(stuId) + "s后再试.");
        String code = String.valueOf(UUID.randomUUID()).replaceAll("-","");
        redisUtil.set(stuId,code,300);
        return R.ok().message("验证码发送成功,请于5分钟内完成验证.");
    }

    @PostMapping("/verify")
    @ApiOperation("学生认证")
    public R verify(String code,HttpServletRequest request) {
        String stuId = request.getHeader("stuId");
        if (code == null || !redisUtil.get(stuId).equals(code)) return R.error().message("认证失败.");
        Stu stu = stuService.getById(stuId);
        stu.setVerify(1);
        redisUtil.del(stuId);
        boolean b = stuService.updateById(stu);
        return b ? R.ok().message("认证成功") : R.ok().message("认证失败");
    }

    @PostMapping("/login")
    @ApiOperation("学生登陆")
    public R login(String username,String password) {
        return R.ok().data("token",stuService.login(username,password));
    }




}

