package com.zsm.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


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

    @RequestMapping("testredirect")
    public String jumpToTestRedirect()
    {
        return "testredirect";
    }

    @RequestMapping("testredirect1")
    public ModelAndView jumpToTestRedirectAndParam(String message, String status, String data)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", message);
        modelAndView.addObject("status", status);
        modelAndView.addObject("data", data);
        //跳转到testredirect.jsp页面
        modelAndView.setViewName("testredirect");
        return modelAndView;
    }
}
