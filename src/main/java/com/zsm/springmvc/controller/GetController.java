package com.zsm.springmvc.controller;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
     * 通过@PathVariable获取路径中的参数
     *
     * @param username
     * @param request
     * @param response
     */
    @RequestMapping(value = "finduser3/{username}", method = RequestMethod.GET)
    public void findUserByName3(@PathVariable String username, HttpServletRequest request, HttpServletResponse response)
    {
        JSONObject json = BaseController.readValueFromRequest(request);
        System.out.println("username is:" + username);
    }
}
