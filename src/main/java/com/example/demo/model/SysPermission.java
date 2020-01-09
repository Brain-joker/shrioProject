package com.example.demo.model;

import lombok.Data;

import java.util.List;

@Data
public class SysPermission extends BaseEntity<Integer>{

    private Integer parentId;   //父目录id
    private String name;         //名字
    private String css;
    private String href;        //链接
    private Integer type;       //类型
    private String permission;  //权限
    private Integer sort;          //排序

    private List<SysPermission> child;  //子目录

    @Override
    public String toString() {
        return "SysPermission{" +
                "parentId=" + parentId +
                ", name='" + name + '\'' +
                ", css='" + css + '\'' +
                ", href='" + href + '\'' +
                ", type=" + type +
                ", permission='" + permission + '\'' +
                ", sort=" + sort +
                ", child=" + child +
                '}';
    }
}
