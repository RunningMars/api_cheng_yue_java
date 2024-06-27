package com.cheng.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value="member")
@ApiModel(value="会员",description="会员实体类") //swagger
public class Member extends BaseEntity {

        public static final Map<String,Integer> educationBackgroundMap =
                Map.of( "高中", 1,
                        "中专", 2,
                        "大专", 3,
                        "本科", 4,
                        "硕士", 5,
                        "博士", 6,
                        "博士后", 7
                );

        public static final Map<String,List> annualIncomeMap =
                Map.of( "5w-10w", Arrays.asList(5,10),
                        "10w-15w", Arrays.asList(10,15),
                        "15w-20w", Arrays.asList(15,20),
                        "20w-30w", Arrays.asList(20,30),
                        "30w-50w", Arrays.asList(30,50),
                        "50w以上" , Arrays.asList(50)
                      );

        public Member(Integer id){
           this.id = id;
        }

        @ApiModelProperty(value="id",notes="id")
        @TableId(type= IdType.AUTO)
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
        private Integer sex;

        @ApiModelProperty(value="年龄",notes="")
        private Integer age;

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

        private Integer annualIncomeMin;

        private Integer annualIncomeMax;

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

        @TableField(fill= FieldFill.INSERT)
        private Date createdAt;

        @TableField(fill=FieldFill.INSERT_UPDATE)
        private Date updatedAt;

        //@TableLogic //软删除注解
        private Date deletedAt;


        /*
          public enum IdType {
                AUTO, //数据库id自增
                INPUT, //手动输入
                ID_WORKER, //默认的全局唯一id
                UUID, //全局唯一id  uuid
                NONE;//未设置主键
                **
        }*/
}
