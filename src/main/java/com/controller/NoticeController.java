package com.controller;

import com.entity.Dept;
import com.entity.Notice;
import com.github.pagehelper.PageInfo;
import com.util.PageUtil;
import com.util.ServiceInterface;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 公告控制层
 * Create by HP on 2020/5/20
 * 游魂
 */
@Controller
@RequestMapping("notice")
public class NoticeController extends ServiceInterface {
    @RequestMapping("selectAll")
    @ResponseBody
    public Map selectAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                         @RequestParam(required = false, defaultValue = "5") Integer limit){
        PageInfo<Notice> pageInfo = new PageInfo<>(noticeService.selectAll(page, limit));
        return PageUtil.pack(pageInfo.getTotal(), pageInfo.getList());
    }

    @RequestMapping(value = "selectByParam", method = RequestMethod.POST)
    @ResponseBody
    public Map selectByParam(@RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "5") Integer limit, HttpServletRequest request){
        Notice notice = new Notice();
        notice.setTitle(request.getParameter("key[title]"));
        notice.setContent(request.getParameter("key[content]"));
        PageInfo<Notice> pageInfo = new PageInfo<>(noticeService.selectByParam(notice, page, limit));
        return PageUtil.pack(pageInfo.getTotal(), pageInfo.getList());
    }
}
