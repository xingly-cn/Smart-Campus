package com.sugar.lost.service;

import com.sugar.lost.entity.Good;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 方糖
 * @since 2022-02-10
 */
public interface GoodService extends IService<Good> {

    boolean editGood(Good good);

    boolean saveGood(Good good);

    boolean findGood(String goodId);
}
