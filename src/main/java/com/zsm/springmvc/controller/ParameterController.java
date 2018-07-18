package com.zsm.springmvc.controller;

import com.zsm.springmvc.mdel.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @Author: zengsm.
 * @Description:
 * @Date:Created in 2018/7/18 19:18.
 * @Modified By:
 */
@Controller
@RequestMapping("/param/")
public class ParameterController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterController.class);

    /**
     * @param user
     * @param bindingResult 绑定返回结果
     * @param model
     * @return
     */
    @RequestMapping("bindingresult")
    public String bindingResult(@ModelAttribute User user, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            LOGGER.error(bindingResult.getFieldError().toString());
            return "error";
        }
        return user.toString();
    }
}
