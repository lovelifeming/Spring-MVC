package com.zsm.springmvc.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import net.sf.json.JSONObject;

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
     * 返回文本内容，使用UTF-8编码
     *
     * @param response
     * @param content
     */
    public static void renderToResponse(HttpServletResponse response, String content)
    {
        renderToResponse(response, "text/plain;charset=UTF-8", content);
    }

    /**
     * 返回对象
     *
     * @param response
     * @param contentType
     * @param obj
     */
    public static void renderToResponse(HttpServletResponse response, String contentType, Object obj)
    {
        String s = JSONObject.fromObject(obj).toString();
        response.setContentType(contentType);
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        try
        {
            response.getWriter().write(s);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

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

    /**
     * 将url参数部分字符串转换为Map集合
     *
     * @param params
     * @return
     */
    public static Map<String, Object> convertURLParamToMap(String params)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        String[] arrSplit = params.split("&");
        for (String s : arrSplit)
        {
            String[] temp = s.split("[=]");
            if (temp.length >= 2 && !"".equals(temp[0]))
            {
                result.put(temp[0], convertStringArrayToString(temp, 1, temp.length - 1));
            }
        }
        return result;
    }

    /**
     * 把String[]数组转换成字符串
     *
     * @param value 数组
     * @param start 数组index起始位置，0~value.length-1
     * @param end   数组index结束位置，0~value.length-1
     * @return
     */
    public static String convertStringArrayToString(String[] value, int start, int end)
    {
        boolean flag = start > 0 && start <= end && end <= value.length - 1;
        if (!flag)
        {
            new RuntimeException("parameter has error,please check!");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++)
        {
            sb.append(value[i]);
        }
        return sb.toString();
    }
}
