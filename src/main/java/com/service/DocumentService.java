package com.service;

import com.entity.Document;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocumentService {
    // 查询所有文档信息
    List<Document> selectAll();

    // 分页查询
    List<Document> selectByPage(@Param("start") int start, @Param("pageSize") int pageSize);

    // 根据id查询
    List<Document> selectOne(int id);

    // 不定项查询
    List<Document> selectByParam(Document document);

    // 新增
    int insert(Document document);

    // 修改
    int update(Document document);

    // 删除
    int delete(int id);
}
