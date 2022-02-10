package com.sugar.lost.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sugar.lost.entity.Category;
import com.sugar.lost.mapper.CategoryMapper;
import com.sugar.lost.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 方糖
 * @since 2022-02-10
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    public boolean editCategory(Category category) {
        String id = category.getId();
        int success = baseMapper.updateById(category);
        return id.isEmpty() ? false : success > 0 ? true : false ;
    }
}
