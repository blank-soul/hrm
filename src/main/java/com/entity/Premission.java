package com.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Premission)实体类
 *
 * @author makejava
 * @since 2020-06-03 08:50:27
 */
@Data
public class Premission implements Serializable {
    private static final long serialVersionUID = 650818143532615661L;
    
    private Integer id;
    
    private String name;
    
    private String remark;
}