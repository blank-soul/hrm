package com.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2020-06-03 08:47:06
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = -75186933925209560L;
    
    private Integer id;
    
    private String name;
    
    private String remark;
}