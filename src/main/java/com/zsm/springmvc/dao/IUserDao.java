package com.zsm.springmvc.dao;

import com.zsm.springmvc.model.User;
import org.springframework.stereotype.Repository;


/**
 * @Author: zsm.
 * @Description:
 * @Date:Created in 2017/11/5 23:28.
 * @Modified By:
 */
@Repository
public interface IUserDao
{
    User findUserByNo(int no);

    User findUserByName(String name);
}
