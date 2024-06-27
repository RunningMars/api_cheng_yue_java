package com.cheng.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@TableName("chat_room_member")
@ApiModel(value = "ChatRoomMember对象", description = "")
public class ChatRoomMember extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("chat_room_id")
    private Integer chatRoomId;

    @TableField("member_id")
    private Integer memberId;

    @ApiModelProperty("0:已读,1:未读")
    @TableField("is_new_to_read")
    private Boolean isNewToRead;

    @TableField("created_at")
    private Date createdAt;

    @TableField("updated_at")
    private Date updatedAt;


}
