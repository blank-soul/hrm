package com.dao;

import com.entity.Document;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocumentDao {
    // 查询所有文档信息
    List<Document> selectAll();

    // 根据id查询
    List<Document> selectOne(int id);

    // 不定项查询
    List<Document> selectByParam(Document document);

    // 新增
    int insert(Document document);

    // 新增
    int batchInsert(List<Document> list);

    // 修改
    int update(Document document);

    // 删除
    int delete(int id);
}
