package com.sugar.base.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 数据库时间字段自动填充
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createtime",new Date(),metaObject);
        this.setFieldValByName("updatetime", new Date(), metaObject);
        //this.setFieldValByName("is_delete", 0, metaObject);
        //this.setFieldValByName("is_read", 0, metaObject);
        //this.setFieldValByName("status", 0, metaObject);
        //this.setFieldValByName("look", 0L, metaObject);
        //this.setFieldValByName("verify", 0, metaObject);
        //this.setFieldValByName("tags", 0L, metaObject);
        //this.setFieldValByName("count", 0L, metaObject);
        //this.setFieldValByName("find", 0L, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified", new Date(), metaObject);
        this.setFieldValByName("lastlogin", new Date(), metaObject);
    }
}
