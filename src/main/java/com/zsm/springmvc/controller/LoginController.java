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

}
