package com.zsm.springmvc.controller;

import com.zsm.springmvc.mdel.User;
import com.zsm.springmvc.mdel.UserValidator;
import com.zsm.springmvc.pojo.Product;
import com.zsm.springmvc.validation.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


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

    /**
     * 采用注解方式验证字段
     *
     * @param user
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping("validator")
    public String validator(@Valid @ModelAttribute UserValidator user, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            LOGGER.error(bindingResult.getFieldError().toString());
            return "error";
        }
        return user.toString();
    }

//    @RequestMapping(value = "/validateProduct")
//    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model)
//    {
//        ProductValidator productValidator = new ProductValidator();
//        productValidator.validate(product, bindingResult);
//        if (bindingResult.hasErrors())
//        {
//            FieldError fieldError = bindingResult.getFieldError();
//            LOGGER.debug("Code:" + fieldError.getCode() + ", field:" + fieldError.getField());
//            return "ProductForm";
//        }
//        model.addAttribute("productNew", new Product());
//        return "product";
//    }
}
