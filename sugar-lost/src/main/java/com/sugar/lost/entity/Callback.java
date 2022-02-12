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
@TableName("lost_callback")
@ApiModel(value="Callback对象", description="")
public class Callback implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "反馈ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "学生ID")
    private String stuid;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "0 未处理 1 处理")
    private Integer status;

    @ApiModelProperty(value = "反馈时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createtime;

    @ApiModelProperty(value = "0 未删除 1 删除")
    @TableLogic
    private Integer isDelete;


}
