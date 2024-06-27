package com.cheng.api.dto.query;

import com.cheng.api.entity.MemberImage;
import com.cheng.api.entity.MemberRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class EditMemberQuery {

    @ApiModelProperty(value="id",notes="id")
    private Integer id;

    @ApiModelProperty(value="user_id",notes="user 表 id")
    private Integer userId;

    @ApiModelProperty(value="手机号",notes="") //swagger
    @NotBlank(message = "用户手机号不能为空") //验证
    @Length(min=11,max=14,message="手机号应为11位") //验证
    private String mobile;

    @ApiModelProperty("电子邮箱")
    private String email;

    @ApiModelProperty(value="密码",notes="")
    private String password;

    @ApiModelProperty(value="性别",notes="1为男性 2为女性")
    private String sex;

//    @ApiModelProperty(value="年龄",notes="")
//    private String age;

    @ApiModelProperty(value="姓名",notes="")
    private String realName;

    @ApiModelProperty(value="昵称",notes="用于个人首页展示和聊天互动的昵称")
    private String nickName;

    private String province;

    private String city;

    private String area;

    private String height;

    private String weight;

    private String bodySize;

    private String school;

    private String wechatNo;

    private String wechatMobile;

    private String educationBackground;

    private Integer educationBackgroundCode;

    private String birthYear;

    private String birthMonth;

    private String birthDay;

    private String annualIncome;

    private String annualIncomeMin;

    private String annualIncomeMax;

    private String monthlyIncome;

    private String assetMoney;

    private String assetHouse;

    private String assetCar;

    private String maritalStatus;

    private String childStatus;

    private String vocation;

    private String job;

    private String aboutMe;

    private String interest;

    private String hopeYou;

    private String questionAnswer;

    private String identificationNo;

    private String identificationName;

    private String identificationValidDate;

    private String profilePhoto;

    private String uploadProfilePhoto;

    private String aboutFamily;

    private String brotherSister;

    private String wantChild;

    private String wantMarry;

    private String ethnic;

    private String constellation;

    private String selfIntroduction;

    private String matingRequirement;

    private String aboutSmoke;

    private String aboutDrink;

    private Integer isAudit;

    private List<MemberImage> memberImages;

    private MemberRequest memberRequest;

}
