package com.sugar.lost.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sugar.base.utils.JwtUtils;
import com.sugar.lost.entity.Stu;
import com.sugar.lost.mapper.StuMapper;
import com.sugar.lost.service.StuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 方糖
 * @since 2022-02-10
 */
@Service
public class StuServiceImpl extends ServiceImpl<StuMapper, Stu> implements StuService {


    @Override
    public String login(String username, String password) {
        if (username == null || password == null) {
            return "登陆失败.";
        }
        QueryWrapper<Stu> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        Stu stu = baseMapper.selectOne(wrapper);
        if (!stu.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)))) {
            return "密码错误.";
        }
        if (stu.getVerify() == 0) {
            return "账号未认证.";
        }
        stu.setLastlogin(new Date());
        baseMapper.updateById(stu);
        String jwtToken = JwtUtils.getJwtToken(stu.getId(), stu.getName());
        return jwtToken;
    }
}
