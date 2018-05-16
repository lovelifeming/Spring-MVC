package com.zsm.springmvc.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 自定义过滤器,在web.xml配置自定义过滤器
 *
 * @Author: zengsm.
 * @Description:
 * @Date:Created in 2018/5/16 22:39.
 * @Modified By:
 */
public class MyFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig)
        throws ServletException
    {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException
    {
        HttpServletResponse resp = (HttpServletResponse)response;
        HttpServletRequest req = (HttpServletRequest)request;
        //获取访问路径的方法名
        String methodName = req.getParameter("method");
        //获取请求拦截路径
        String url = req.getRequestURI();

        url = url + "_" + methodName;
        //移除不要过滤的资源路径
        if (url.contains("login"))
        {
            //放行
            chain.doFilter(req, resp);
            return;
        }
        //获取Session里面登录的用户信息
        Object object = req.getSession().getAttribute("username");
        if (object == null)
        {
            //如果没有登录，重定向到登录页
            resp.sendRedirect(req.getContextPath() + "/testlogin.jsp");
        }
        else
        {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy()
    {

    }
}
