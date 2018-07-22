package com.zsm.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Locale;


/**
 * @Author: zsm.
 * @Description:
 * @Date:Created in 2017/12/3 21:13.
 * @Modified By:
 */
@Controller
@RequestMapping("/session/")
public class SessionController
{
    @RequestMapping("execute")
    public void execute(HttpServletRequest request, HttpServletResponse response)
        throws UnsupportedEncodingException
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

    /**
     * 国际化设置语言
     *
     * @param language 语言
     * @param country  国家
     * @return
     */
    @RequestMapping("setLanguage")
    public String setLanguage(HttpServletRequest request, HttpServletResponse response, String language, String country)
    {
        Locale locale = new Locale(language, country);
        request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
        return "index";
    }

}
