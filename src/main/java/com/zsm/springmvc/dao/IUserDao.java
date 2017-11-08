package com.zsm.springmvc.dao;

import com.zsm.springmvc.mdel.User;
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
    User selectUserByNo(int no);

    User selectUserByName(String name);
}
