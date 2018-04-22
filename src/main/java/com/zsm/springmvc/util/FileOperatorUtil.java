package com.zsm.springmvc.util;

import java.net.URISyntaxException;


/**
 * @Author: zsm.
 * @Description:
 * @Date:Created in 2017/11/10 21:17.
 * @Modified By:
 */
public class FileOperatorUtil
{
    /**
     * 获取系统文件分隔符
     */
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * . 文件扩展名分隔符
     */
    public static final String FILE_POINT = ".";

    /**
     * 获取真实路径，用系统分隔符替换
     *
     * @param path
     * @return
     */
    public static String getRealFilePathBySystem(String path)
    {
        return path.replace("/", FILE_SEPARATOR).replace("\\", FILE_SEPARATOR);
    }

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
            filePath = new FileOperatorUtil().getClass().getResource("/").toURI().getPath();
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        return filePath;
    }
}
