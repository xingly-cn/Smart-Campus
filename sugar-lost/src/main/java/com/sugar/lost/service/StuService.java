package com.sugar.lost.service;

import com.sugar.lost.entity.Stu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 方糖
 * @since 2022-02-10
 */
public interface StuService extends IService<Stu> {

    String login(String username, String password);
}
