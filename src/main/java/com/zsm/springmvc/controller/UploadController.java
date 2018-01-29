package com.zsm.springmvc.controller;

import com.zsm.springmvc.util.FileOperatorUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


/**
 * 文件上传,spring-mvc.xml配置上传文件大小限制，添加commons-fileupload，commons-io依赖
 *
 * @Author: zsm.
 * @Description:
 * @Date:Created in 2017/11/12 9:39.
 * @Modified By:
 */
@Controller
@RequestMapping("/upload/")
public class UploadController
{
    private static final String UPLOAD_FILE_PATH =
        "WEB-INF" + FileOperatorUtil.FILE_SEPARATOR + "upload" + FileOperatorUtil.FILE_SEPARATOR;

    /**
     * 单文件上传将表单中input标签中的name="upLoadFile"属性在控制器中以MultipartFile对象上传
     *
     * @param number   number 文件个数
     * @param request
     * @param response
     */
    @RequestMapping(value = "file")
    public void uploadFile(String number, HttpServletRequest request, HttpServletResponse response)
    {
        //转换成默认的request对象
        MultipartHttpServletRequest mreq = (MultipartHttpServletRequest)request;
        //对应前台表单与name=filename的文件
        MultipartFile multipartFile = mreq.getFile("upLoadFile");
        if (multipartFile != null)
        {
            String fileName = multipartFile.getOriginalFilename();
            String rootPath = request.getServletContext().getRealPath("/");
            String filePath = rootPath + UPLOAD_FILE_PATH;
            File file = new File(filePath);
            if (!file.exists())
            {
                file.mkdirs();
            }
            try
            {
                FileOutputStream fos = new FileOutputStream(filePath + fileName);
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

    @RequestMapping("file1")
    public void uploadFile1(@RequestParam(value = "upLoadFile", required = false) MultipartFile files, String number,
                            HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        String originalName = files.getOriginalFilename();
        String rootPath = request.getServletContext().getRealPath("/");
        String filePath = rootPath + UPLOAD_FILE_PATH;
        File fileDir = new File(filePath);
        // 当目录不存在时创建
        if (!fileDir.exists())
        {
            fileDir.mkdirs();
        }
        // 写入指定的目录
        FileOutputStream os = new FileOutputStream(filePath + originalName);
        byte[] bytes = files.getBytes();
        BufferedOutputStream stream = new BufferedOutputStream(os);
        stream.write(bytes);
        stream.close();
        os.close();
    }

    @RequestMapping("file2")
    public void uploadFile2(@RequestParam(value = "upLoadFile", required = false) MultipartFile files,
                            String number, HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        String rootPath = request.getServletContext().getRealPath("");
        String filePath = rootPath + UPLOAD_FILE_PATH;
        InputStream inputStream = files.getInputStream();
        File fileDir = new File(filePath);
        if (!fileDir.exists())
        {
            fileDir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(filePath + files.getOriginalFilename());
        byte[] buff = new byte[1024];
        while (inputStream.read(buff) > -1)
        {
            fileOutputStream.write(buff);
        }
        fileOutputStream.close();
        inputStream.close();
    }

    @RequestMapping("file3")
    public void uploadFile3(@RequestParam(value = "upLoadFile", required = false) MultipartFile files,
                            String number, HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        String rootPath = request.getServletContext().getRealPath("");
        String filePath = rootPath + UPLOAD_FILE_PATH;
        File fileDir = new File(filePath);
        if (!fileDir.exists())
        {
            fileDir.mkdirs();
        }
        File file = new File(filePath + files.getOriginalFilename());
        files.transferTo(file);
    }

    /**
     * 多文件上传与单文件上传类似，多文件上传将表单中的
     * input标签中的name="upLoadFile"属性设为相同，在控制器中将MultipartFile定义为一个数组 循环读取，依次上传
     *
     * @param number
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("file4")
    public void uploadFile4(String number, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String filePath = request.getServletContext().getRealPath("/") + UPLOAD_FILE_PATH;
        File file = new File(filePath);
        if (!file.exists())
        {
            file.mkdirs();
        }
        List<MultipartFile> fileMap = ((MultipartHttpServletRequest)request).getMultiFileMap().get("upLoadFile");
        for (MultipartFile item : fileMap)
        {
            if (!item.isEmpty())
            {
                String fileName = item.getOriginalFilename();
                File file1 = new File(filePath + fileName);
                item.transferTo(file1);
            }
        }
    }

    @RequestMapping("file5")
    public void uploadFile5(HttpServletRequest request, HttpServletResponse response)
    {

    }

    @RequestMapping("file6")
    public void uploadFile6(HttpServletRequest request, HttpServletResponse response)
    {

    }
}
