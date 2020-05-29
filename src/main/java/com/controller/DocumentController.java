package com.controller;

import com.entity.Document;
import com.github.pagehelper.PageInfo;
import com.util.PageUtil;
import com.util.ServiceInterface;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 文件控制层
 * Create by HP on 2020/5/20
 * 游魂
 */
@Controller
@RequestMapping("document")
public class DocumentController extends ServiceInterface {
    @RequestMapping("selectAll")
    @ResponseBody
    public Map selectAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                         @RequestParam(required = false, defaultValue = "5") Integer limit){
        PageInfo<Document> pageInfo = new PageInfo<>(documentService.selectAll(page, limit));
        return PageUtil.pack(pageInfo.getTotal(), pageInfo.getList());
    }

    @RequestMapping(value = "selectByParam", method = RequestMethod.POST)
    @ResponseBody
    public Map selectByParam(@RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "5") Integer limit, HttpServletRequest request){
        Document document = new Document();
        document.setTitle(request.getParameter("key[title]"));
        PageInfo<Document> pageInfo = new PageInfo<>(documentService.selectByParam(document, page, limit));
        return PageUtil.pack(pageInfo.getTotal(), pageInfo.getList());
    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    @ResponseBody
    public Integer insert(HttpServletRequest request){
        Document document = new Document();
        document.setTitle(request.getParameter("documentTitle"));
        document.setFilename(request.getParameter("filename"));
        document.setRemark(request.getParameter("documentRemark"));
        document.setCreateDate(request.getParameter("createDate"));
        if(null == document.getCreateDate() || "".equals(document.getCreateDate())){
            document.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
        String userId = request.getParameter("userId");
        if(null != userId && !"".equals(userId)){
            document.setUserId(Integer.valueOf(userId));
        }
        Integer res = documentService.insert(document);
        return res;
    }
}
