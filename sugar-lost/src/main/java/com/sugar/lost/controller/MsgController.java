package com.sugar.lost.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sugar.base.result.R;
import com.sugar.base.utils.JwtUtils;
import com.sugar.lost.entity.Msg;
import com.sugar.lost.entity.vo.MsgVo;
import com.sugar.lost.service.MsgService;
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
@RequestMapping("/lost/msg")
@CrossOrigin
@Api(tags = "消息管理")
public class MsgController {

    @Resource
    private MsgService msgService;

    @PostMapping("/send")
    @ApiOperation("发送消息")
    public R sendMsg(@RequestBody MsgVo msgVo,HttpServletRequest request) {
        String fromer = JwtUtils.getMemberIdByJwtToken(request);
        Msg msg = new Msg();
        BeanUtils.copyProperties(msgVo, msg);
        msg.setFromer(fromer);
        return R.ok().data("success",msgService.save(msg));
    }

    @GetMapping("/getList")
    @ApiOperation("接受消息")
    public R getList(HttpServletRequest request) {
        String stuId = JwtUtils.getMemberIdByJwtToken(request);
        QueryWrapper<Msg> wrapper = new QueryWrapper<>();
        wrapper.eq("stuid",stuId);
        wrapper.orderByDesc("createtime");
        return R.ok().data("list",msgService.list(wrapper));
    }

    @GetMapping("/read")
    @ApiOperation("已读消息")
    public R read(String msgId, HttpServletRequest request) {
        return R.ok().data("success",msgService.readMsg(msgId, request));
    }

    @GetMapping("getInfo")
    @ApiOperation("查看消息")
    public R getInfo(String msgId, HttpServletRequest request) {
        return R.ok().data("msg",msgService.getInfo(msgId, request));
    }

}

