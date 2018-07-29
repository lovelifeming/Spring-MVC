package com.zsm.springmvc.model;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


/**
 * @Author: zengsm.
 * @Description:
 * @Date:Created in 2018/7/18 23:49.
 * @Modified By:
 */
public class UserValidator implements Serializable
{
    private static final long serialVersionUID = 519735703068753412L;

    @Size(min = 1000, max = 10000)
    private String user_no;

    @NotBlank
    @Null
    private String user_name;

    @NotEmpty
    private String user_sex;

    @Past
    private Date user_birthday;

    public static long getSerialVersionUID()
    {
        return serialVersionUID;
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
}
