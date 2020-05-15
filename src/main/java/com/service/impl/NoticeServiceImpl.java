package com.service.impl;

import com.entity.Notice;
import com.dao.NoticeDao;
import com.service.NoticeService;
import com.util.MyBatisUtil;

import java.util.List;

/**
 * Create by HP on 2020/5/13
 * 游魂
 */
public class NoticeServiceImpl implements NoticeService {
    NoticeDao noticeDao = MyBatisUtil.getMapper(Notice.class);

    @Override
    public List<Notice> selectAll() {
        return noticeDao.selectAll();
    }

    @Override
    public List<Notice> selectByPage(int start, int pageSize) {
        return noticeDao.selectByPage(start, pageSize);
    }

    @Override
    public List<Notice> selectOne(int id) {
        return noticeDao.selectOne(id);
    }

    @Override
    public List<Notice> selectByParam(Notice notice) {
        return noticeDao.selectByParam(notice);
    }

    @Override
    public int insert(Notice notice) {
        return noticeDao.insert(notice);
    }

    @Override
    public int update(Notice notice) {
        return noticeDao.update(notice);
    }

    @Override
    public int delete(int id) {
        return noticeDao.delete(id);
    }

    public static void main(String[] args) {

    }
}
