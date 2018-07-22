package com.zsm.springmvc.validation;

import com.zsm.springmvc.pojo.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.math.BigDecimal;


/**
 * @Author: zengsm.
 * @Description:
 * @Date:Created in 2018/7/19 23:28.
 * @Modified By:
 */
public class ProductValidator implements Validator
{
    @Override
    public boolean supports(Class<?> klass)
    {
        return Product.class.isAssignableFrom(klass);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        Product product = (Product)target;
        ValidationUtils.rejectIfEmpty(errors, "name", "productname.required");
        BigDecimal price = product.getPrice();
        if (price != null && price.compareTo(BigDecimal.ZERO) < 0)
        {
            errors.rejectValue("price", "price.negative");
        }
    }
}
