package com.cheng.api.entity;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName("chat_room_message")
@ApiModel(value = "ChatRoomMessage对象", description = "")
public class ChatRoomMessage extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("chat_room_id")
    private Integer chatRoomId;

    @TableField("from_member_id")
    private Integer fromMemberId;

    @TableField("to_member_id")
    private Integer toMemberId;

    @TableField("message")
    private String message;

    @TableField(fill= FieldFill.INSERT)
    private Date createdAt;

    @TableField(fill=FieldFill.UPDATE)
    private Date updatedAt;

    @TableField("deleted_at")
    private Date deletedAt;


}
