package com.example.demo.base.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 抽成一个类存放公共的分页参数
 */
@Data
public class PageTableRequest implements Serializable {

    private Integer page;  // 当前页
    private Integer limit; // 每页条数
    private Integer offset; //每页起始位置

    public void countOffset(){
        if(null == this.page || null == this.limit){
            this.offset = 0;
            return;
        }
        this.offset = (this.page - 1) * limit;
    }
}
