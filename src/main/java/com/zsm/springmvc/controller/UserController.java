package com.zsm.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsm.springmvc.mdel.User;
import com.zsm.springmvc.service.IUserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


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

    @RequestMapping(value = "selectuser.do", method = RequestMethod.POST)
    public void selectUserByNo(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int no = Integer.valueOf(request.getParameter("no"));
        User user = userService.selectUserByNo(no);
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(user);
        response.getWriter().write(result);
        response.getWriter().close();
    }

    @RequestMapping(value = "selectuser1", method = RequestMethod.POST)
    public void selectUserByName1(@RequestBody JSONObject params, HttpSession session, HttpServletResponse response)
        throws IOException
    {
        String name = params.getString("username");
        User user = userService.selectUserByName(name);
        //设置响应消息头编码
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(user));
    }

}
