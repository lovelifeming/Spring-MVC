package com.zsm.springmvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
}
