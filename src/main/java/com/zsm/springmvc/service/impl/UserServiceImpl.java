package com.zsm.springmvc.service.impl;

import com.zsm.springmvc.dao.IUserDao;
import com.zsm.springmvc.model.User;
import com.zsm.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * @Author: zsm.
 * @Description:
 * @Date:Created in 2017/11/5 23:41.
 * @Modified By:
 */
@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private IUserDao userDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public User findUserByNo(int no)
    {
        User user = userDao.findUserByNo(no);
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public User findUserByName(String name)
    {
        return userDao.findUserByName(name);
    }
}
