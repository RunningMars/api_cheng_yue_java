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
 * @since 2024-06-24
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("member_request")
@ApiModel(value = "MemberRequest对象", description = "")
public class MemberRequest extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("user_id")
    @TableField("member_id")
    private Integer memberId;

    @ApiModelProperty("年龄")
    @TableField("age_min_request")
    private Integer ageMinRequest;

    @ApiModelProperty("年龄")
    @TableField("age_max_request")
    private Integer ageMaxRequest;

    @TableField("city_request")
    private String cityRequest;

    @ApiModelProperty("身高cm")
    @TableField("height_min_request")
    private Integer heightMinRequest;

    @ApiModelProperty("身高cm")
    @TableField("height_max_request")
    private Integer heightMaxRequest;

    @ApiModelProperty("体重kg")
    @TableField("weight_min_request")
    private Integer weightMinRequest;

    @ApiModelProperty("体重kg")
    @TableField("weight_max_request")
    private Integer weightMaxRequest;

    @ApiModelProperty("体型(一般,偏瘦,运动,偏胖,魁梧,壮实)")
    @TableField("body_size_request")
    private String bodySizeRequest;

    @ApiModelProperty("教育背景,最高学历")
    @TableField("education_background_request")
    private String educationBackgroundRequest;

    @ApiModelProperty("年收入W")
    @TableField("annual_income_request")
    private Integer annualIncomeRequest;

    @ApiModelProperty("资产信息,房产(暂无,准备购买,有房)")
    @TableField("asset_house_request")
    private String assetHouseRequest;

    @ApiModelProperty("资产信息,车辆(暂无,准备购买,有车")
    @TableField("asset_car_request")
    private String assetCarRequest;

    @ApiModelProperty("资产信息,车辆(暂无,准备购买,有车")
    @TableField("marital_status_request")
    private String maritalStatusRequest;

    @TableField("child_status_request")
    private String childStatusRequest;

    @ApiModelProperty("岗位")
    @TableField("job_request")
    private String jobRequest;

    @ApiModelProperty("家庭情况")
    @TableField("about_family_request")
    private String aboutFamilyRequest;

    @TableField("brother_sister_request")
    private String brotherSisterRequest;

    @ApiModelProperty("是否要小孩")
    @TableField("want_child_request")
    private String wantChildRequest;

    @ApiModelProperty("合适结婚")
    @TableField("want_marry_request")
    private String wantMarryRequest;

    @ApiModelProperty("是否吸烟")
    @TableField("about_smoke_request")
    private String aboutSmokeRequest;

    @ApiModelProperty("是否喝酒")
    @TableField("about_drink_request")
    private String aboutDrinkRequest;

    @TableField("created_at")
    private Date createdAt;

    @TableField("updated_at")
    private Date updatedAt;

    @TableField("deleted_at")
    private Date deletedAt;


}
