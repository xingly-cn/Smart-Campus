package com.sugar.lost.service;

import com.sugar.lost.entity.Msg;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 方糖
 * @since 2022-02-10
 */
public interface MsgService extends IService<Msg> {

    boolean readMsg(String msgId, HttpServletRequest request);
}
