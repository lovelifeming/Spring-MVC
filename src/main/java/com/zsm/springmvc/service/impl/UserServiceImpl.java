package com.zsm.springmvc.service.impl;

import com.zsm.springmvc.dao.IUserDao;
import com.zsm.springmvc.mdel.User;
import com.zsm.springmvc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @Author: zsm.
 * @Description:
 * @Date:Created in 2017/11/5 23:41.
 * @Modified By:
 */
@Service
public class UserServiceImpl implements IUserService
{
    @Autowired
    private IUserDao userDao;

    @Override
    public User selectUserByNo(int no)
    {
        User user = userDao.selectUserByNo(no);
        return user;
    }

    @Override
    public User selectUserByName(String name)
    {
        return userDao.selectUserByName(name);
    }
}
