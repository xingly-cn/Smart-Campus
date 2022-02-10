package com.sugar.lost.service.impl;

import com.sugar.lost.entity.Msg;
import com.sugar.lost.mapper.MsgMapper;
import com.sugar.lost.service.MsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
