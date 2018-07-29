package com.zsm.springmvc.service;

import com.zsm.springmvc.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @Author: zengsm.
 * @Description:
 * @Date:Created in 2018/4/3.
 * @Modified By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 加载spring配置文件
@ContextConfiguration("classpath:spring/applicationContext-dao.xml")
public class RedisCacheUtilTest
{
    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @Test
    public void getCacheObject()
        throws Exception
    {
        User user = new User();
        user.setUser_no("001");
        redisCacheUtil.setCacheObject("admin", user);
        User user1 = (User)redisCacheUtil.getCacheObject("admin");
        Assert.assertEquals("001", user1.getUser_no());
    }

}
