package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class BaseEntity<ID extends Serializable> implements Serializable {

    private ID id;
    private Date createTime = new Date();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime = new Date();
}

