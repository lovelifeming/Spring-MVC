package com.zsm.springmvc.service.impl;

import com.zsm.springmvc.model.User;
import com.zsm.springmvc.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @Author: zsm.
 * @Description:
 * @Date:Created in 2017/11/9 21:17.
 * @Modified By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 加载spring配置文件
@ContextConfiguration("classpath:spring/applicationContext-dao.xml")
public class UserServiceImplTest
{
    @Autowired
    private UserService userService;

    @Test
    public void findUserByNo() throws Exception
    {
        int no = 101;
        User user = userService.findUserByNo(no);
        Assert.assertEquals(user.getUser_name(), "李军");
    }

    @Test
    public void findUserByName() throws Exception
    {
        String name = "曾华";
        User user = userService.findUserByName(name);
        Assert.assertEquals("108", user.getUser_no());
    }
}
