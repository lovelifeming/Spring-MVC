package com.zsm.springmvc.controller;

import com.zsm.springmvc.mdel.User;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
	 * 无请求参数
	 */
	@RequestMapping(value = "finduser", method = RequestMethod.GET)
	public String findUserByName()
	{
		System.out.println("username ");
		System.out.println("password ");
		return "testredirecrt";
	}

	/**
	 * 1.通过请求直接获取参数
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
	 * 2.通过@PathVariable获取路径中的参数
	 *
	 * @param username
	 * @param password
	 */
	@RequestMapping(value = "finduser1/{username}/{password}", method = RequestMethod.GET)
	public void findUserByName1(@PathVariable String username, @PathVariable String password)
	{
		System.out.println("username " + username);
		System.out.println("password " + password);
	}

	/**
	 * 3.通过HttpServletRequest 获取参数
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "finduser2", method = RequestMethod.GET)
	public void findUserByName2(HttpServletRequest request, HttpServletResponse response)
	{
		String username = request.getParameter("username");
		System.out.println("username is:" + username);
	}

	/**
	 * 4.通过RequestParam 获取参数
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "finduser3", method = RequestMethod.GET)
	public void findUserByName3(@RequestParam(value = "username", required = false) String username, HttpServletRequest
		request, HttpServletResponse response)
	{
		System.out.println("username is:" + username);
	}

	/**
	 * 5.通过转化为对象 获取参数
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "finduser4", method = RequestMethod.GET)
	public void findUserByName4(User user, HttpServletRequest
		request, HttpServletResponse response)
	{
		System.out.println("username is:" + user.getUser_name());
	}

}
