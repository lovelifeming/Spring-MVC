package com.zsm.springmvc.util;

import java.net.URISyntaxException;


/**
 * @Author: zsm.
 * @Description:
 * @Date:Created in 2017/11/10 21:17.
 * @Modified By:
 */
public class FileOperatorUtils
{
    /**
     * 获取FileOperatorUtils文件相对的根路径
     *
     * @return
     */
    public static String getRelativelyPath()
    {
        String filePath = null;
        try
        {
            filePath = new FileOperatorUtils().getClass().getResource("/").toURI().getPath();
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        return filePath;
    }
}
