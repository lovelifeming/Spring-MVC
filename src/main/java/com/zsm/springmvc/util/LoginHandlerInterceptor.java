package com.zsm.springmvc.util;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;


/**
 * @Author: zengsm.
 * @Description:
 * @Date:Created in 2018/1/12 17:19.
 * @Modified By:
 */
public class LoginHandlerInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception
    {
        /*String url = request.getRequestURI();
        if (url.contains("login"))
        {
            return true;
        }
        HttpSession session = request.getSession();
        String flag = (String)session.getAttribute("isLogin");
        if (flag != null && "true".equals(flag))
        {
            return true;
        }
        //跳转到登录页面
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<script>");
        out.println("window.open ('" + request.getContextPath() + "/login.jsp','_top')");
        out.println("</script>");
        out.println("</html>");
        return false;*/
        
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView)
        throws Exception
    {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex)
        throws Exception
    {

    }
}
