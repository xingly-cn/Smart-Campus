package com.sugar.lost.service.impl;

import com.sugar.lost.entity.Category;
import com.sugar.lost.entity.Good;
import com.sugar.lost.mapper.GoodMapper;
import com.sugar.lost.service.CategoryService;
import com.sugar.lost.service.GoodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements GoodService {

    @Autowired
    private CategoryService categoryService;

    @Override
    public boolean editGood(Good good) {
        String id = good.getId();
        int success = baseMapper.updateById(good);
        return id.isEmpty() ? false : success > 0 ? true : false ;
    }

    @Override
    public boolean saveGood(Good good) {
        baseMapper.insert(good);
        String categoryId = good.getCategory();
        Category category = categoryService.getById(categoryId);
        category.setCount(category.getCount() + 1);
        boolean update = categoryService.updateById(category);
        return update;
    }

    @Override
    public boolean findGood(String goodId) {
        Good good = baseMapper.selectById(goodId);
        if (good.getTags() == 1L) {
            return false;
        }
        good.setTags(1L);
        baseMapper.updateById(good);
        String categoryId = good.getCategory();
        Category category = categoryService.getById(categoryId);
        category.setFind(category.getFind() + 1);
        boolean update = categoryService.updateById(category);
        return update;
    }
}
