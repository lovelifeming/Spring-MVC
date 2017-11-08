package com.zsm.springmvc.service;

import com.zsm.springmvc.mdel.User;
import org.omg.PortableInterceptor.USER_EXCEPTION;


/**
 * @Author: zsm.
 * @Description:
 * @Date:Created in 2017/11/5 23:41.
 * @Modified By:
 */
public interface IUserService
{
    User selectUserByNo(int no);

    User selectUserByName(String name);
}
