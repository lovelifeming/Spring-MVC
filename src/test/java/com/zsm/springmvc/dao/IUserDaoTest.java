package com.zsm.springmvc.dao;

import com.zsm.springmvc.mdel.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @Author: zsm.
 * @Description:
 * @Date:Created in 2017/11/7 23:54.
 * @Modified By:
 */

@RunWith(SpringJUnit4ClassRunner.class)
// 加载spring配置文件
@ContextConfiguration({"classpath:spring/applicationContext-dao.xml"})
public class IUserDaoTest
{
    @Autowired
    private IUserDao userDao;

    @Test
    public void testSelectUserByNo()
    {
        int no = 101;
        User user = userDao.selectUserByNo(no);
        Assert.assertEquals(user.getUser_name(), "李军");
    }

}