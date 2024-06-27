package com.cheng.api.dto;

import lombok.Data;

@Data
public class PageInfo {

    private Integer pageNum = 1;

    private Integer pageSize = 10;
}
