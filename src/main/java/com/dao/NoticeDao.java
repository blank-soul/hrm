package com.dao;

import com.entity.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeDao {
    // 查询所有公告信息
    List<Notice> selectAll();

    // 根据id查询
    List<Notice> selectOne(int id);

    // 不定项查询
    List<Notice> selectByParam(Notice notice);

    // 新增
    int insert(Notice notice);

    // 修改
    int update(Notice notice);

    // 删除
    int delete(int id);
}
