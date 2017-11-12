package com.zsm.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;


/**
 * @Author: zsm.
 * @Description:
 * @Date:Created in 2017/11/12 9:39.
 * @Modified By:
 */
@Controller
@RequestMapping("/upload/")
public class UploadFiles
{
    @RequestMapping(value = "file")
    public void uploadFile(HttpServletRequest request, HttpServletResponse response)
    {
        //转换成多部分的request对象
        MultipartHttpServletRequest mreq = (MultipartHttpServletRequest)request;
        //对应前台表单与name=filename的文件
        MultipartFile multipartFile = mreq.getFile("filename");
        if (multipartFile != null)
        {
            //获取真实文件名
            String fileName = multipartFile.getOriginalFilename();
            //设置一个日期格式的变量
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // 新文件名=upload/+文件名+当前时间+文件以.为开始点截取至尾部的名字
            String filePath = "upload/" + fileName.substring(0, fileName.lastIndexOf(".")) + sdf.format(new Date())
                              + fileName.substring(fileName.lastIndexOf("."));
            //定义一个文件输入输出流
            try
            {
                FileOutputStream fos = new FileOutputStream(
                    request.getSession().getServletContext().getRealPath("/") + filePath);
                fos.write(multipartFile.getBytes());
                fos.flush();
                fos.close();
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
