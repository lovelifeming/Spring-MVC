package com.zsm.springmvc.model;

import java.io.Serializable;
import java.util.Date;


/**
 * @Author: zsm.
 * @Description:
 * @Date:Created in 2017/11/5 23:29.
 * @Modified By:
 */
public class User implements Serializable
{
    private int id;

    private String user_no;

    private String user_name;

    private String user_sex;

    private Date user_birthday;

    private String user_class;

    private Date createtime;

    private Date updatetime;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUser_no()
    {
        return user_no;
    }

    public void setUser_no(String user_no)
    {
        this.user_no = user_no;
    }

    public String getUser_name()
    {
        return user_name;
    }

    public void setUser_name(String user_name)
    {
        this.user_name = user_name;
    }

    public String getUser_sex()
    {
        return user_sex;
    }

    public void setUser_sex(String user_sex)
    {
        this.user_sex = user_sex;
    }

    public Date getUser_birthday()
    {
        return user_birthday;
    }

    public void setUser_birthday(Date user_birthday)
    {
        this.user_birthday = user_birthday;
    }

    public String getUser_class()
    {
        return user_class;
    }

    public void setUser_class(String user_class)
    {
        this.user_class = user_class;
    }

    public Date getCreatetime()
    {
        return createtime;
    }

    public void setCreatetime(Date createtime)
    {
        this.createtime = createtime;
    }

    public Date getUpdatetime()
    {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime)
    {
        this.updatetime = updatetime;
    }

    @Override
    public String toString()
    {
        return "User{" +
               "id=" + id +
               ", user_no='" + user_no + '\'' +
               ", user_name='" + user_name + '\'' +
               ", user_sex='" + user_sex + '\'' +
               ", user_birthday=" + user_birthday +
               ", user_class='" + user_class + '\'' +
               ", createtime=" + createtime +
               ", updatetime=" + updatetime +
               '}';
    }
}
