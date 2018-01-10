package com.zsm.springmvc.controller;

import com.zsm.springmvc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @Author: zsm.
 * @Description:
 * @Date:Created in 2017/11/15 23:00.
 * @Modified By:
 */
@Controller
@RequestMapping("/login/")
public class LoginController
{
	@Autowired
	private IUserService userService;
    //response.setHeader("Refresh","5; URL=http://www.baidu.com")：5秒后自动跳转到百度主页

    //response.setStatus(302);  //重定向
    //response.setHeader("Location", "http://www.baidu.com");

    //response.sendRedirect("http://www.baidu.com")
}
