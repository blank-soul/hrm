package com.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 部门类
 * Create by HP on 2020/5/9
 * 游魂
 */
@Data
public class Dept implements Serializable {
    private Integer id;
    private String name;
    private String remark;
    private Integer status;
    private List<Emp> emps;
}
