package com.zsm.springmvc.util;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;


/**
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
