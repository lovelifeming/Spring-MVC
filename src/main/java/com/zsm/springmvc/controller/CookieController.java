package com.zsm.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Author: zsm.
 * @Description:
 * @Date:Created in 2017/12/3 18:34.
 * @Modified By:
 */
@Controller
@RequestMapping("/cookie/")
public class CookieController
{
    @RequestMapping("execute")
    public void execute(HttpServletRequest request, HttpServletResponse response)
    {
        Cookie username = new Cookie("username", "zengsm");
        Cookie pwd = new Cookie("pwd", "123");
        username.setPath(request.getContextPath() + "/");
        pwd.setPath(request.getContextPath() + "/");
        //设置Cookie有效时间,Cookie失效的时间，单位秒.默认为–1,关闭浏览器即失效;如果为0，表示删除该Cookie
        username.setMaxAge(0);
        pwd.setMaxAge(0);
        //设置Cookie域名
        username.setDomain(".zsm.com");
        pwd.setDomain(".zsm.com");

        response.addCookie(username);
        response.addCookie(pwd);

    }

}
