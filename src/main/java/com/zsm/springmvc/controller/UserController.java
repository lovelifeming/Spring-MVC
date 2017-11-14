package com.zsm.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsm.springmvc.mdel.User;
import com.zsm.springmvc.service.IUserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * @Author: zsm.
 * @Description:
 * @Date:Created in 2017/11/5 23:26.
 * @Modified By:
 */
@Controller
@RequestMapping("/user/")
public class UserController
{
    @Autowired
    private IUserService userService;

    /**
     * 通过HttpServletRequest接收，post方式和get方式都可以
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "finduser")//, method = RequestMethod.POST)
    public void findUserByNo(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map<String, String[]> temp = request.getParameterMap();
        Iterator it = temp.keySet().iterator();
        while (it.hasNext())
        {
            String tp = (String)it.next();
            sop(tp);
            String[] str = temp.get(tp);
            for (String s : str)
            {
                sop(s);
            }
        }
        //int no = Integer.valueOf(request.getParameter("no"));
        User user = userService.findUserByNo(2);
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(user);
        response.getWriter().write(result);
        response.getWriter().close();
    }

    @RequestMapping(value = "finduser1")//, method = RequestMethod.POST)
    public void findUserByName1(@RequestBody String params, HttpSession session, HttpServletResponse response)
        throws IOException
    {
        String param= URLDecoder.decode(params,"utf-8");
        String name = JSONObject.fromObject(params).getString("username");
        User user = userService.findUserByName(name);
        //设置响应消息头编码
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(user));
    }

    /**
     * 直接把表单的参数写在Controller相应的方法的形参中，适用于get方式提交,形参名称必须一致
     *
     * @param username
     * @param password
     * @param request
     * @param response
     */
    @RequestMapping("finduser2")
    public void findUserByName2(String username, String password, HttpServletRequest request,
                                HttpServletResponse response)
    {
        Enumeration er = request.getParameterNames();
        StringBuilder sb = new StringBuilder();

        while (er.hasMoreElements())
        {
            Object temp = er.nextElement();
            sb.append(temp);
            sop(temp);
        }

        System.out.println("username is:" + username);
        System.out.println("password is:" + password);
    }

    /**
     * 通过@PathVariable获取路径中的参数
     *
     * @param username
     * @param request
     * @param response
     */
    @RequestMapping(value = "finduser3/{username}")//, method = RequestMethod.GET)
    public void findUserByName3(@PathVariable String username, HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("username is:" + username);
    }

    /**
     * 用注解@RequestParam绑定请求参数到方法入参
     *
     * @param username
     * @param request
     * @param response
     */
    @RequestMapping(value = "finduser4")//, method = RequestMethod.GET)
    public void findUserByName4(@RequestParam("username") String username, @RequestParam("pwd") String pwd,
                                HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("username is:" + username);
        System.out.println("pwd is:" + pwd);
    }

    public static void sop(Object obj)
    {
        System.out.println(obj);
    }
}
