package com.zsm.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.zsm.springmvc.mdel.User;
import com.zsm.springmvc.pojo.UserModel;
import com.zsm.springmvc.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Post请求总共分为两种方式传输数据：
 * 一种是以表单From的方式提交数据；contentType:application/x-www-form-urlencoded
 * 一种是请求体RequestBody的方式提交数据；application/json;charset=utf-8
 *
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
    private UserService userService;

    /**
     * 1.通过HttpServletRequest接收，请求头设置为contentType:application/x-www-form-urlencoded
     * postRequest(url,JSON.stringify(param),"application/x-www-form-urlencoded",function () { });
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "finduser", method = RequestMethod.POST)
    public void findUserByName(HttpServletRequest request, HttpServletResponse response)
    {
        JSONObject json = BaseController.readValueFromRequest(request);
        sop(json);
        User user = userService.findUserByName(json.getString("username"));
        BaseController.writeValueToResponse(response, user);
    }

    /**
     * 2.POST访问,HttpServletRequest   getParameter,application/x-www-form-urlencoded,数据以表单提交，不能转为JSON格式
     * postRequest(url,param,"application/x-www-form-urlencoded",function () { });
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "finduser1", method = RequestMethod.POST)
    public void findUserByName1(HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        sop(username);
        sop(password);
        User user = userService.findUserByName(username);
        response.getWriter().print(JSON.toJSONString(user));
    }

    /**
     * 3.post方式提交,形参名称必须一致，application/x-www-form-urlencoded,数据以表单提交，不能转为JSON格式
     * postRequest(url,param,"application/x-www-form-urlencoded",function () { });
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
     * 4.用注解@RequestParam绑定请求参数到方法入参,application/x-www-form-urlencoded,数据以表单提交，不能转为JSON格式
     * postRequest(url,param,"application/x-www-form-urlencoded",function () { });
     *
     * @param username
     * @param request
     * @param response
     */
    @RequestMapping(value = "finduser3", method = RequestMethod.POST)
    public void findUserByName3(@RequestParam("username") String username, @RequestParam("password") String password,
                                HttpServletRequest request, HttpServletResponse response)
    {
        String name = request.getParameter("username");
        System.out.println("username is:" + username);
        System.out.println("pwd is:" + password);
        User user = userService.findUserByName(name);
    }

    /**
     * 5.用对象映射接收参数，application/x-www-form-urlencoded,数据以表单提交，不能转为JSON格式
     * postRequest(url,param,"application/x-www-form-urlencoded",function () { });
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
     * 6.使用@ModelAttribute注解获取POST请求的FORM表单数据，数据以表单提交，不能转为JSON格式
     * postRequest(url, param, "application/x-www-form-urlencoded", function () { });
     *
     * @param user
     * @param request
     * @param response
     */
    @RequestMapping(value = "finduser5", method = RequestMethod.POST)
    public void findUserByName5(@ModelAttribute("user") UserModel user, HttpServletRequest request,
                                HttpServletResponse response)
    {
        String username = user.getUsername();
        String password = user.getPassword();
        String name = request.getParameter("username");
        User user1 = userService.findUserByName(name);
        sop(user);
    }

    /**
     * 7.RequestBody获取Bean对象,application/json;charset=utf-8，数据必须转换为JSON格式
     * postRequest(url,JSON.stringify(param),"application/json;charset=utf-8",function () { });
     *
     * @param user
     * @param request
     * @param response
     */
    @RequestMapping(value = "finduser6", method = RequestMethod.POST)
    public void findUserByName6(@RequestBody UserModel user, HttpServletRequest request, HttpServletResponse response)
    {
        String name = user.getUsername();
        User user1 = userService.findUserByName(name);
        sop(user.getUsername() + user.getPassword());
    }

    /**
     * 8.Restful风格POST请求，application/json;charset=utf-8，数据必须转换为JSON格式
     * postRequest(url +"7/admin/123456", JSON.stringify(params), "application/json;charset=utf-8", function () { });
     *
     * @param username
     * @param password
     */
    @RequestMapping(value = "finduser7/{username}/{password}", method = RequestMethod.POST)
    public void findUserByName7(@PathVariable("username") String username, @PathVariable("password") String password,
                                HttpServletRequest request)
    {
        sop(username);
        sop(password);
    }

    public static void sop(Object obj)
    {
        System.out.println(obj);
    }
}
