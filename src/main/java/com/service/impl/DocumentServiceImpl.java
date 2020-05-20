package com.service.impl;

import com.entity.Document;
import com.github.pagehelper.PageHelper;
import com.service.DocumentService;
import com.util.DaoInterface;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by HP on 2020/5/13
 * 游魂
 */
@Service
public class DocumentServiceImpl extends DaoInterface implements DocumentService {
    @Override
    public List<Document> selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return documentDao.selectAll();
    }

    @Override
    public List<Document> selectOne(int id) {
        return documentDao.selectOne(id);
    }

    @Override
    public List<Document> selectByParam(Document document) {
        return documentDao.selectByParam(document);
    }

    @Override
    public int insert(Document document) {
        return documentDao.insert(document);
    }

    @Override
    public int update(Document document) {
        return documentDao.update(document);
    }

    @Override
    public int delete(int id) {
        return documentDao.delete(id);
    }

}
