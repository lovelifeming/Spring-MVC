package com.zsm.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.zsm.springmvc.mdel.User;
import com.zsm.springmvc.pojo.UserModel;
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
    public void findUserByName(HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        JSONObject json = BaseController.readValueFromRequest(request);
        sop(json);
        User user = userService.findUserByName(json.getString("username"));
        BaseController.wirteValueToResponse(response, user);
    }

    /**
     * 2.POST访问,RequestBody
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
    @ResponseBody
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

    /**
     * 5.
     *
     * @param user
     * @param request
     * @param response
     */
    @RequestMapping(value = "finduser4", method = RequestMethod.POST)
    @ResponseBody
    public void findUserByName4(UserModel user, HttpServletRequest request, HttpServletResponse response)
    {
        String name = request.getParameter("username");
        String n = user.getUsername();
        String p = user.getPassword();
        User ur = userService.findUserByName(name);
        sop(user);
    }

    /**
     * 6.RequestBody获取Bean对象
     *
     * @param user
     * @param request
     * @param response
     */
    @RequestMapping(value = "finduser5", method = RequestMethod.POST)
    public void findUserByName5(@RequestBody User user, HttpServletRequest request, HttpServletResponse response)
    {
        String name = request.getParameter("username");
        String name1 = user.getUser_name();
        User user1 = userService.findUserByName(name);
        sop(user);
    }

    /**
     * 7.使用@ModelAttribute注解获取POST请求的FORM表单数据
     *
     * @param user
     * @param request
     * @param response
     */
    @RequestMapping(value = "finduser6", method = RequestMethod.POST)
    public void findUserByName6(@ModelAttribute("user") UserModel user, HttpServletRequest request,
                                HttpServletResponse response)
    {
        String username = user.getUsername();
        String password = user.getPassword();
        String name = request.getParameter("username");
        User user1 = userService.findUserByName(name);
        sop(user);
    }

    public static void sop(Object obj)
    {
        System.out.println(obj);
    }
}
