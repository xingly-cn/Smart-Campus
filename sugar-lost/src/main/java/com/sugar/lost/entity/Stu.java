package com.sugar.lost.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 方糖
 * @since 2022-02-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("lost_stu")
@ApiModel(value="Stu对象", description="")
public class Stu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学生ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "学生姓名")
    private String name;

    @ApiModelProperty(value = "学生账号")
    private String username;

    @ApiModelProperty(value = "学生密码")
    private String password;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "所属学校")
    private String schoolname;

    @ApiModelProperty(value = "1 男 2 女")
    private Integer gender;

    @ApiModelProperty(value = "注册时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createtime;

    @ApiModelProperty(value = "最后登陆时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date lastlogin;

    @ApiModelProperty(value = "0 未认证 1 认证")
    private Integer verify;

    @ApiModelProperty(value = "0 未删除 1 删除")
    @TableLogic
    private Integer isDelete;


}
