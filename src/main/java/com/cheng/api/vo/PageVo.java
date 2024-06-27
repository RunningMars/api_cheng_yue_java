package com.cheng.api.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

@Data
public class PageVo<T> {

    List<T> list;

    Long total;

    public PageVo(Page<T> page){
        this.setList(page.getRecords());
        this.setTotal(page.getTotal());
    }
}
