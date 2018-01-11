package com.zsm.springmvc.util;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 用于前台数据转换为Javabean对象，自定义时间类型数据转换方式
 *
 * @Author: zengsm.
 * @Description:
 * @Date:Created in 2018/1/10 23:45.
 * @Modified By:
 */
public class StringDateConverter implements Converter<String, Date>
{
    @Override
    public Date convert(String s)
    {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            return sf.parse(s);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            System.out.println("StringDateConverter->convert->" + e.getMessage());
        }
        return null;
    }
}
