package com.zsm.springmvc.controller;

import com.zsm.springmvc.util.FileOperatorUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;


/**
 * 下载文件
 *
 * @Author: zengsm.
 * @Description:
 * @Date:Created in 2018/1/11 11:13.
 * @Modified By:
 */
@Controller
@RequestMapping("/download/")
public class DownLoadController
{
    @RequestMapping(value = "file/{filename}")
    public void downLoadFile(@PathVariable("filename") String filename, HttpServletRequest request,
                             HttpServletResponse response)
        throws IOException
    {
        String rootPath = request.getServletContext().getRealPath("/");
        String realName = URLEncoder.encode(filename, "UTF-8");
        //获取文件的文件系统路径
        String filePath = FileOperatorUtil.getRealFilePathBySystem(rootPath + "WEB-INF/classes/config/" + filename);

        //设置响应头，提示浏览器不要解析响应的文件数据，而是以附件(attachment)的形式解析，即下载功能
        response.addHeader("Content-Disposition", "attachment;filename=" + realName);
        long fileLength = new File(filePath).length();
        //设置文件大小，如果设置了文件大小，接收端会根据其值接收
        response.addHeader("Content-Length", String.valueOf(fileLength));
        //设置文件ContentType类型，这样会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        //response.setContentType("application/octet-stream;charset=UTF-8");
        //response.addHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");

        //读取文件的输入流，以及响应的输出流，并将数据输出给客户端
        InputStream in = new BufferedInputStream(new FileInputStream(new File(filePath)));
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = in.read(bytes)) != -1)
        {
            out.write(bytes, 0, len);
            out.flush();
        }
        in.close();
        out.close();
    }
}
