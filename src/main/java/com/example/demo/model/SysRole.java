package com.example.demo.model;



import lombok.Data;

/**
 * 角色的实体类 继承公共实体类
 */
@Data
public class SysRole extends BaseEntity<Integer>{
    private String name;
    private String description;

    @Override
    public String toString() {
        return "SysRole{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
