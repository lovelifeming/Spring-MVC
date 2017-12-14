package com.zsm.springmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.UnsupportedEncodingException;


/**
 * @Author: zsm.
 * @Description:
 * @Date:Created in 2017/12/3 21:13.
 * @Modified By:
 */
@RequestMapping("/session/")
public class SessionController
{
    @RequestMapping("execute")
    public void execute(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
    {
        request.setCharacterEncoding("UTF-8");
        //使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession user = request.getSession();
        //将数据存储到session中
        user.setAttribute("name", "zengsm");
        //获取session的Id
        String id = user.getId();
        //判断是是否是新建session
        boolean isnew = user.isNew();
        //手动销毁session
        user.invalidate();
    }

}
