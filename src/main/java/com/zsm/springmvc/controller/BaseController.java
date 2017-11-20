package com.zsm.springmvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.JSONParser;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;


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
        StringBuilder sb = new StringBuilder();
        Enumeration en = request.getParameterNames();
        while (en.hasMoreElements())
        {
            sb.append(en.nextElement());
        }
        JSONObject json = JSONObject.fromObject(sb.toString());
        return json;
    }
}
