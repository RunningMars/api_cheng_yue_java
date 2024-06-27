package com.cheng.api.dto;

import lombok.Data;

@Data
public class MemberQuery extends PageInfo{

    private String id;

    private String keyWord;

    private String mobile;

    private String realName;

    private Integer sex;

    private Integer ageMinRequest;
    private Integer ageMaxRequest;
    private Integer heightMinRequest;
    private Integer heightMaxRequest;
    private Integer educationBackgroundCodeRequest;
    private String annualIncomeRequest;
    private Integer annualIncomeMinRequest;
    private String assetCarRequest;
    private Object assetHouseRequest;
    private Object maritalStatusRequest;
    private String wantChildRequest;
    private String isFavorite;
    private String isThumbsUp;

}
