package com.zsm.springmvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;


/**
 * @Author: zsm.
 * @Description:
 * @Date:Created in 2017/11/9 23:59.
 * @Modified By:
 */
public class BaseController
{
    /**
     * 将返回值写入到HttpServletResponse中
     *
     * @param response
     * @param object
     */
    public static void wirteValueToResponse(HttpServletResponse response, Object object)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            String result = mapper.writeValueAsString(object);
            response.getWriter().write(result);
            response.getWriter().close();
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 获取请求Request里面的参数
     *
     * @param request
     * @return
     */
    public static JSONObject readValueFromRequest(HttpServletRequest request)
    {
        JSONObject json = new JSONObject();
        Map<String, String[]> map = request.getParameterMap();
        Iterator<Map.Entry<String, String[]>> iterator = map.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry<String, String[]> m = iterator.next();
            json.put(m.getKey(), m.getValue());
        }
        return json;
    }
}
