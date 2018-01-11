package com.zsm.springmvc.controller;

import com.zsm.springmvc.pojo.UserModel;
import com.zsm.springmvc.util.ControllerUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;


/**
 * @Author: zsm.
 * @Description:
 * @Date:Created in 2017/11/15 22:59.
 * @Modified By:
 */
@Controller
@RequestMapping("/get/")
public class GetController
{

    /**
     * 无请求参数
     */
    @RequestMapping(value = "find", method = RequestMethod.GET)
    public String findUser()
    {
        System.out.println("test request");
        //跳转到testredirecrt.jsp页面
        return "testredirecrt";
    }

    /**
     * 1.通过请求携带数据直接获取参数，http://localhost:8080/get/finduser?username="admin"&password="123456"
     *
     * @param username
     * @param password
     */
    @RequestMapping(value = "finduser", method = RequestMethod.GET)
    public void findUserByName(String username, String password)
    {
        System.out.println("username " + username);
        System.out.println("password " + password);
    }

    /**
     * 2.通过@PathVariable获取路径中的参数,http://localhost:8080/get/finduser1/admin/123456
     *
     * @param username
     * @param password
     */
    @RequestMapping(value = "finduser1/{username}/{password}", method = RequestMethod.GET)
    public ModelAndView findUserByName1(@PathVariable String username, @PathVariable String password)
    {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("username " + username);
        System.out.println("password " + password);

        modelAndView.addObject("message", "验证成功");
        //跳转到testredirecrt.jsp页面
        modelAndView.setViewName("testredirecrt");
        return modelAndView;
    }

    /**
     * 3.通过HttpServletRequest 获取参数,http://localhost:8080/get/finduser2?username="admin"&password="123456"
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "finduser2", method = RequestMethod.GET)
    public void findUserByName2(HttpServletRequest request, HttpServletResponse response)
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username is:" + username);
        System.out.println("password is:" + password);
    }

    /**
     * 4.通过RequestParam 获取参数,http://localhost:8080/get/finduser3?username="admin"&password="123456"
     *
     * @param username
     * @param password
     */
    @RequestMapping(value = "finduser3", method = RequestMethod.GET)
    public void findUserByName3(@RequestParam(value = "username", required = false) String username,
                                @RequestParam("password") String password)
    {
        System.out.println("username is:" + username);
        System.out.println("password is:" + password);
    }

    /**
     * 5.通过Bean转化为对象 获取参数,http://localhost:8080/get/finduser3?username="admin"&password="123456"
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "finduser4", method = RequestMethod.GET)
    public void findUserByName4(UserModel user, HttpServletRequest
        request, HttpServletResponse response)
    {
        System.out.println("username is:" + user.getUsername());
        System.out.println("password is:" + user.getPassword());
    }

    /**
     * 6.通过HttpServletRequest  getQueryString获取参数，http://localhost:8080/get/finduser5?username="admin"&password="123456"
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "finduser5", method = RequestMethod.GET)
    public void findUserByName5(HttpServletRequest request, HttpServletResponse response)
        throws UnsupportedEncodingException
    {
        //getQueryString获取到请求参数字符串：username="admin"&password="123456"
        String param = URLDecoder.decode(request.getQueryString(), "UTF-8");
        Map map = ControllerUtil.convertURLParamToMap(param);
        System.out.println("username is:" + map.get("username"));
        System.out.println("password is:" + map.get("password"));
    }
}
