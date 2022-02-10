package com.sugar.lost.service.impl;

import com.sugar.lost.entity.Category;
import com.sugar.lost.mapper.CategoryMapper;
import com.sugar.lost.service.CategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
