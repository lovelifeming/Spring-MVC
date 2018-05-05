package com.zsm.springmvc.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;


/**
 * 控制层继承HttpServlet，自定义实现doGet、doPost,实现文件上传
 *
 * @Author: zengsm.
 * @Description:
 * @Date:Created in 2018/5/3.
 * @Modified By:
 */
@WebServlet("/servlet/")
public class HttpServletExt extends HttpServlet
{
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServletExt.class);

    public HttpServletExt()
    {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        PrintWriter pw = null;
        try
        {
            //设置编码
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            pw = response.getWriter();
            //设置系统环境
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //文件存储的路径
            String storePath = getServletContext().getRealPath("/WEB-INF/files");
            LOGGER.info("文件存储的路径：" + storePath);
            //判断传输方式  form  enctype=multipart/form-data
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (!isMultipart)
            {
                pw.write("传输方式有错误！");
                return;
            }
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(4 * 1024 * 1024);//设置单个文件大小不能超过4M
            upload.setSizeMax(4 * 1024 * 1024);//设置总文件上传大小不能超过6M
            //监听上传进度
            upload.setProgressListener(new ProgressListener()
            {
                /**
                 * @param pBytesRead    当前以读取到的字节数
                 * @param pContentLength    文件的长度
                 * @param pItems    当前读取文件的索引
                 */
                @Override
                public void update(long pBytesRead, long pContentLength, int pItems)
                {
                    System.out.println(String.format("已读文件字节数 :%s 文件总长度：%s  文件索引：%s",
                        pBytesRead, pContentLength, pItems));
                }
            });
            //解析获取文件内容
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items)
            {
                if (item.isFormField())
                {
                    //普通字段，表单提交过来的
                    String name = item.getFieldName();
                    String value = item.getString("UTF-8");
                    System.out.println(name + "=" + value);
                }
                else
                {
                    InputStream in = item.getInputStream();
                    String fileName = item.getName();
                    if (fileName == null || "".equals(fileName.trim()))
                    {
                        continue;
                    }
                    fileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1);
                    //按日期来建文件夹
                    String newStorePath = makeStorePath(storePath);
                    String storeFile = newStorePath + File.separator + fileName;
                    OutputStream out = new FileOutputStream(storeFile);
                    byte[] bytes = new byte[1024];
                    int len = -1;
                    while ((len = in.read(bytes)) != -1)
                    {
                        out.write(bytes, 0, len);
                        out.flush();
                    }
                    in.close();
                    out.close();
                    //删除临时文件
                    item.delete();
                }
            }
        }
        catch (FileUploadException e)
        {
            e.printStackTrace();
            pw.write("file upload exception:" + e.getMessage());
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            pw.write("file upload unsupported encoding exception:" + e.getMessage());
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            pw.write("file upload not found  exception:" + e.getMessage());
        }
        catch (IOException e)
        {
            e.printStackTrace();
            pw.write("file upload io exception:" + e.getMessage());
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        doGet(request, response);
    }

    /**
     * 根据日期生成文件夹
     *
     * @param storePath
     * @return
     */
    private String makeStorePath(String storePath)
    {
        Date date = new Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String str = df.format(date);
        String path = storePath + File.separator + str;
        File file = new File(path);
        if (!file.exists())
        {
            //mkdirs创建多级目录，mkdir只创建一级目录
            file.mkdirs();
        }
        return path;
    }

    /**
     * 根据文件名的hashcode生成文件夹
     *
     * @param storePath
     * @param fileName
     * @return
     */
    private String makeStorePath2(String storePath, String fileName)
    {
        int hashCode = fileName.hashCode();
        int dir1 = hashCode & 0xf;              // 0000~1111：整数0~15共16个
        int dir2 = (hashCode & 0xf0) >> 4;      // 0000~1111：整数0~15共16个

        String path = storePath + File.separator + dir1 + File.separator + dir2; // WEB-INF/files/12/8
        File file = new File(path);
        if (!file.exists())
        {
            file.mkdirs();
        }
        return path;
    }
}
