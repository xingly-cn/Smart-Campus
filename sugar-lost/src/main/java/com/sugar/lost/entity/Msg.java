package com.sugar.lost.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("lost_msg")
@ApiModel(value="Msg对象", description="")
public class Msg implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "消息ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "物品ID")
    private String goodid;

    @ApiModelProperty(value = "学生ID")
    private String stuid;

    @ApiModelProperty(value = "发送者ID")
    private String from;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "发送时间")
    private Date createtime;

    @ApiModelProperty(value = "0 未读 1 已读")
    private Integer isRead;

    @ApiModelProperty(value = "0 未删除 1 删除")
    private Integer isDelete;


}
