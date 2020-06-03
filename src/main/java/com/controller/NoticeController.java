package com.controller;

import com.entity.Notice;
import com.entity.User;
import com.github.pagehelper.PageInfo;
import com.util.PageUtil;
import com.util.ServiceInterface;
import org.apache.shiro.SecurityUtils;
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

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    @ResponseBody
    public Integer insert(HttpServletRequest request){
        Notice notice = new Notice();
        notice.setTitle(request.getParameter("noticeTitle"));
        notice.setContent(request.getParameter("noticeContent"));
        notice.setCreateDate(request.getParameter("createDate"));
        if(null == notice.getCreateDate() || "".equals(notice.getCreateDate())){
            notice.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        notice.setUserId(user.getId());
        Integer res = noticeService.insert(notice);
        return res;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Integer update(HttpServletRequest request){
        Notice notice = new Notice();
        notice.setId(Integer.valueOf(request.getParameter("noticeId")));
        notice.setTitle(request.getParameter("noticeTitle"));
        notice.setContent(request.getParameter("noticeContent"));
        notice.setCreateDate(request.getParameter("createDate"));
        String userId = request.getParameter("userId");
        if(null != userId && !"".equals(userId)){
            notice.setUserId(Integer.valueOf(userId));
        }
        Integer res = noticeService.update(notice);
        return res;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Integer delete(HttpServletRequest request){
        Integer id = Integer.valueOf(request.getParameter("id"));
        Integer res = noticeService.delete(id);
        return res;
    }
}
