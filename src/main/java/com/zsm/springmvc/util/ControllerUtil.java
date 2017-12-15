package com.zsm.springmvc.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author: zengsm.
 * @Description:
 * @Date:Created in 2017/12/15 16:11.
 * @Modified By:
 */
public class ControllerUtil
{
    /**
     * 将对象转化成Map对象集合
     *
     * @param json
     * @param keyClass
     * @param valueClass
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> jsonToMap(String json, Class<?> keyClass, Class<?> valueClass)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        MapType type = TypeFactory.defaultInstance().constructMapType(HashMap.class, keyClass, valueClass);
        Map<K, V> map = null;
        try
        {
            map = objectMapper.readValue(json, type);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw new RuntimeException("json数据转化为Map对象失败，可能是对象类型不匹配");
        }
        return map;
    }

    /**
     * 将参数写入到Response里面，响应数据写入
     *
     * @param response
     * @param param
     */
    public static void writeValueToResponse(HttpServletResponse response, Object param)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            String map = mapper.writeValueAsString(param);
            response.getWriter().write(map);
            response.getWriter().close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 获取请求里面的参数字符串
     *
     * @param request
     * @return
     */
    public static String readValueFromRequest(HttpServletRequest request)
    {
        StringBuilder sb = new StringBuilder();
        Enumeration<String> en = request.getParameterNames();
        while (en.hasMoreElements())
        {
            sb.append(en.nextElement());
        }
        return sb.toString();
    }

    /**
     * 获取请求体里面的参数，返回Map集合，value集合以,分割
     *
     * @param request
     * @return
     */
    public static Map<String, String> readMapFromRequest(HttpServletRequest request)
    {
        HashMap<String, String> result = new HashMap<String, String>();
        Map<String, String[]> maps = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : maps.entrySet())
        {
            String key = entry.getKey();
            String[] valueArr = entry.getValue();
            String value = null;
            for (int i = 0; valueArr != null && i < valueArr.length; i++)
            {
                if (i == valueArr.length - 1)
                {
                    value += valueArr[i];
                }
                else
                {
                    value += valueArr[i] + ",";
                }
            }
            result.put(key, value);
        }
        return result;
    }
}
