package com.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * (Menu)实体类
 *
 * @author makejava
 * @since 2020-06-02 11:14:14
 */
@Data
public class Menu implements Serializable {
    private static final long serialVersionUID = -19338646958955793L;

    private Integer id;
    /**
    * 名称
    */
    private String name;
    /**
    * url
    */
    private String location;
    /**
    * 父级id
    */
    private Integer perentid;
    /**
     * 子级菜单
     */
    private List<Menu> childs;

}