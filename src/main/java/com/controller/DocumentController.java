package com.controller;

import com.entity.Document;
import com.entity.User;
import com.github.pagehelper.PageInfo;
import com.util.PageUtil;
import com.util.ServiceInterface;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @RequestMapping(value = "unload", method = RequestMethod.POST)
    @RequiresPermissions("document:unload")
    public String unload(MultipartFile upfile, HttpServletRequest request, Document document){
        if(null == upfile || 0 == upfile.getSize()){
            return "redirect:/addDocument.jsp";
        }
        try {
            // 将文件上传到服务器指定目录
            String dir = request.getServletContext().getRealPath("file");
            String savefielname = UUID.randomUUID().toString();
            String filesuffix = upfile.getOriginalFilename().substring(upfile.getOriginalFilename().lastIndexOf("."));
            String savepath = dir+"/"+savefielname+filesuffix;
            upfile.transferTo(new File(savepath));

            // 将文件信息保存到数据库
            User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
            document.setUserId(user.getId());
            document.setTitle(request.getParameter("documentTitle"));
            document.setRemark(request.getParameter("documentRemark"));
            document.setFilename("file/"+savefielname+filesuffix);
            document.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            documentService.insert(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/addDocument.jsp";
    }

    @RequestMapping(value = "download", method = RequestMethod.GET)
    @RequiresPermissions("document:download")
    public void download(HttpServletRequest request, HttpServletResponse response, Integer id){
        List<Document> list = documentService.selectOne(id);
        if(null == list){
            return;
        }

        InputStream in = null;
        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(list.get(0).getFilename(), "UTF-8"));
            String dir = request.getServletContext().getRealPath("");
            in = new FileInputStream(dir+"/"+list.get(0).getFilename());
            int len = 0;
            byte[] buffer = new byte[1024];
            OutputStream out = response.getOutputStream();
            while((len = in.read(buffer)) > 0){
                out.write(buffer, 0, len);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Integer delete(HttpServletRequest request){
        Integer id = Integer.valueOf(request.getParameter("id"));
        Integer res = documentService.delete(id);
        return res;
    }
}
