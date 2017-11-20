package com.zsm.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.zsm.springmvc.mdel.User;
import com.zsm.springmvc.service.IUserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;


/**
 * @Author: zsm.
 * @Description:
 * @Date:Created in 2017/11/5 23:26.
 * @Modified By:
 */
@Controller
@RequestMapping("/post/")
public class PostController
{
    @Autowired
    private IUserService userService;

    /**
     * 1.通过HttpServletRequest接收
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "finduser", method = RequestMethod.POST)
    public void findUserByName(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String name = request.getParameter("username");
        JSONObject json = BaseController.readValueFromRequest(request);
        sop(json);
        User user = userService.findUserByName(json.getString("username"));
        BaseController.wirteValueToResponse(response, user);
    }

    /**
     * 2.POST可以访问
     *
     * @param params
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "finduser1", method = RequestMethod.POST)
    public void findUserByName1(@RequestBody String params, HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String name1 = request.getParameter("username");
        String param = URLDecoder.decode(params, "utf-8");
        sop(param);
        String name = JSONObject.fromObject(param).getString("username");
        User user = userService.findUserByName(name);
        //设置响应消息头编码
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(user));
    }

    /**
     * 3.post方式提交,形参名称必须一致
     *
     * @param username
     * @param password
     * @param request
     * @param response
     */
    @RequestMapping(value = "finduser2", method = RequestMethod.POST)
    public void findUserByName2(String username, String password, HttpServletRequest request,
                                HttpServletResponse response)
    {
        String name = request.getParameter("username");
        JSONObject json = BaseController.readValueFromRequest(request);
        sop(json);
        System.out.println("username is:" + username);
        System.out.println("password is:" + password);
        User user = userService.findUserByName(name);
    }

    /**
     * 4.用注解@RequestParam绑定请求参数到方法入参
     *
     * @param username
     * @param request
     * @param response
     */
    @RequestMapping(value = "finduser3", method = RequestMethod.POST)
    public void findUserByName3(@RequestParam("username") String username, @RequestParam("pwd") String pwd,
                                HttpServletRequest request, HttpServletResponse response)
    {
        String name = request.getParameter("username");
        System.out.println("username is:" + username);
        System.out.println("pwd is:" + pwd);
        User user = userService.findUserByName(name);
    }

    public void findUserByName4(User user, HttpServletRequest request, HttpServletResponse response)
    {
        String name = request.getParameter("username");
        String n = user.getUser_name();
        User ur = userService.findUserByName(name);
        sop(user);
    }

    public static void sop(Object obj)
    {
        System.out.println(obj);
    }
}
