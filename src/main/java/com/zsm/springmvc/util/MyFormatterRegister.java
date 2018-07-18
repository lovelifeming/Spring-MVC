package com.zsm.springmvc.util;

import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;


/**
 * Formatter 可以有两种添加方式：注册Register；添加Bean
 *
 * @Author: zengsm.
 * @Description:
 * @Date:Created in 2018/7/18 19:42.
 * @Modified By:
 */
public class MyFormatterRegister implements FormatterRegistrar
{
    private String datePattern;

    public MyFormatterRegister(String datePattern)
    {
        this.datePattern = datePattern;
    }

    @Override
    public void registerFormatters(FormatterRegistry formatterRegistry)
    {
        formatterRegistry.addFormatter(new DateFormatter(datePattern));
    }
}
