package com.sugar.lost.service.impl;

import com.sugar.base.utils.JwtUtils;
import com.sugar.lost.entity.Msg;
import com.sugar.lost.mapper.MsgMapper;
import com.sugar.lost.service.MsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 方糖
 * @since 2022-02-10
 */
@Service
public class MsgServiceImpl extends ServiceImpl<MsgMapper, Msg> implements MsgService {

    @Override
    public boolean readMsg(String msgId, HttpServletRequest request) {
        String stuId = JwtUtils.getMemberIdByJwtToken(request);
        Msg msg = baseMapper.selectById(msgId);
        if (msg == null || !msg.getStuid().equals(stuId)) {
            return false;
        }
        int i = baseMapper.deleteById(msgId);
        return i == 1 ? true : false;
    }

    @Override
    public Object getInfo(String msgId, HttpServletRequest request) {
        String stuId = JwtUtils.getMemberIdByJwtToken(request);
        Msg msg = baseMapper.selectById(msgId);
        if (msg == null || !msg.getStuid().equals(stuId)) {
            return "非法权限,您的IP已被记录.";
        }
        return msg;
    }
}
