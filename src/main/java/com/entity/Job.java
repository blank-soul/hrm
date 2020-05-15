package com.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 职位类
 * Create by HP on 2020/5/12
 * 游魂
 */
@Data
public class Job implements Serializable {
    private Integer id;
    private String name;
    private String remark;
    private Integer status;
    private List<Emp> emps;
}
