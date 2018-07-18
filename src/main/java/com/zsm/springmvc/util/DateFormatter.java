package com.zsm.springmvc.util;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * @Author: zengsm.
 * @Description:
 * @Date:Created in 2018/7/18 19:31.
 * @Modified By:
 */
public class DateFormatter implements Formatter<Date>
{
    private String datePattern;

    private SimpleDateFormat dateFormat;

    public DateFormatter(String datePattern)
    {
        this.datePattern = datePattern;
        dateFormat = new SimpleDateFormat(datePattern);
        dateFormat.setLenient(false);
    }

    @Override
    public String print(Date date, Locale locale)
    {
        return dateFormat.format(date);
    }

    @Override
    public Date parse(String s, Locale locale)
        throws ParseException
    {
        return dateFormat.parse(s);
    }
}
