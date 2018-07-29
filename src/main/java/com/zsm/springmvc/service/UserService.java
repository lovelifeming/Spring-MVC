package com.zsm.springmvc.service;

import com.zsm.springmvc.model.User;


/**
 * @Author: zsm.
 * @Description:
 * @Date:Created in 2017/11/5 23:41.
 * @Modified By:
 */
public interface UserService
{
    User findUserByNo(int no);

    User findUserByName(String name);
}
