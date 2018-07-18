package com.zsm.springmvc.validation;

import com.zsm.springmvc.mdel.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;


/**
 * @Author: zengsm.
 * @Description:
 * @Date:Created in 2018/7/18 23:26.
 * @Modified By:
 */
public class UserValidator implements Validator
{
    @Override
    public boolean supports(Class<?> aClass)
    {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors)
    {
        User user= (User)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"user_name","username.required");
        ValidationUtils.rejectIfEmpty(errors,"user_no","userno.required");
        Date date=user.getUser_birthday();
        if(!date.after(new Date()))
        {
            errors.rejectValue("birthday","birthday.incorrect");
        }

    }
}
