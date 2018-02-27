package com.zsm.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Author: zengsm.
 * @Description:
 * @Date:Created in 2018/1/12 10:23.
 * @Modified By:
 */
@Controller
@RequestMapping("/jump/")
public class JumpController
{
    @RequestMapping("result")
    public String jumpToIndex()
    {
        return "result";
    }

    @RequestMapping("redirect")
    public String jumpToRedirect()
    {
        //跳转到testredirect.jsp页面,返回文件名testredirect
        return "testredirect";
    }

    @RequestMapping("redirectandparam")
    public ModelAndView jumpToRedirectAndParam(String message, String status, String data)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", message);
        modelAndView.addObject("status", status);
        modelAndView.addObject("data", data);
        //跳转到testredirect.jsp页面
        modelAndView.setViewName("redirect");
        return modelAndView;
    }

    @RequestMapping("download")
    public String jumpToDownLoad()
    {
        return "testdownload";
    }

    @RequestMapping("refresh")
    public void jumpToRefresh(HttpServletResponse response)
    {
        //3秒后自动跳转到首页
        response.setHeader("Refresh", "3;URL=http://www.baidu.com");
    }

    @RequestMapping("header")
    public void jumpToHeader(HttpServletResponse response)
    {
        response.setHeader("Location", "../index.jsp");
        //设置302状态码，等同于response.setStatus(302)
        response.setStatus(HttpServletResponse.SC_FOUND);
    }

    @RequestMapping("sendredirect")
    public void jumpToSendRedirect(HttpServletResponse response)
        throws IOException
    {
        response.sendRedirect("http://www.baidu.com");
    }
}
