package com.cheng.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author rdg
 * @since 2024-06-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("chat_room")
@ApiModel(value = "ChatRoom对象", description = "")
public class ChatRoom extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("chat_room_name")
    private String chatRoomName;

    @TableField("created_at")
    private Date createdAt;

    @TableField("updated_at")
    private Date updatedAt;




}
