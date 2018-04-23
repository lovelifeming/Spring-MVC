package com.zsm.springmvc.controller;

import com.zsm.springmvc.util.FileOperatorUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;


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

    private static final Logger LOGGER = LogManager.getLogger(UploadController.class);

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

    @RequestMapping(value = "file5", produces = "application/json; charset=utf-8")
    public ModelAndView uploadFile5(@RequestParam("upLoadFile") MultipartFile[] multipartFiles,
                                    HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("result");
        if (multipartFiles != null && multipartFiles.length > 0)
        {
            String filePath = request.getServletContext().getRealPath("/") + UPLOAD_FILE_PATH;
            File file = new File(filePath);
            if (!file.exists())
            {
                file.mkdirs();
            }

            for (MultipartFile m : multipartFiles)
            {
                String fileName = filePath + FileOperatorUtil.FILE_SEPARATOR + m.getOriginalFilename();
                m.transferTo(new File(fileName));
            }
            modelAndView.addObject("message", "upload file success");
            modelAndView.addObject("status", "true");
        }
        else
        {
            modelAndView.addObject("message", "upload file fail");
            modelAndView.addObject("status", "false");
        }

        return modelAndView;
    }

    /**
     * base64 字符编码 文件以字符形式上传，主要用于小文件图片LOGO上传,上传文件大小有限制
     *
     * @param base64
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("file6")
    @ResponseBody
    public String uploadFile6(String base64, HttpServletRequest request, HttpServletResponse response)
    {
        //去掉base64数据头部 \"data:image/png;base64, 和尾部的 \"
        String[] arr = base64.split(",");
        String suffix = arr[0].substring(arr[0].indexOf("/") + 1, arr[0].indexOf(";"));
        String str = arr[1];
        String[] split = str.split("\"");
        String content = split[0];
        //图片保存到本地
        String path = this.getClass().getResource("/").getPath() + "images";
        JSONObject result = new JSONObject();
        String name = String.valueOf(UUID.randomUUID()) + FileOperatorUtil.FILE_POINT + suffix;
        try
        {
            //将图片插入数据库
            //userService.base64test(context);
            File file = new File(path);
            if (!file.exists())
            {
                file.mkdirs();
            }
            path = path + FileOperatorUtil.FILE_SEPARATOR + name;
            decoderBase64File(content, path);
        }
        catch (Exception e)
        {
            LOGGER.info(e.getMessage());
            e.printStackTrace();
            result.put("success", false);
            return result.toString();
        }
        result.put("success", true);
        result.put("data", name);
        return result.toString();
    }

    /**
     * 将base64字符解码保存文件
     *
     * @param base64Code
     * @param targetPath
     */

    public static void decoderBase64File(String base64Code, String targetPath)
    {
        byte[] buffer;
        try (FileOutputStream out = new FileOutputStream(targetPath))
        {
            buffer = new BASE64Decoder().decodeBuffer(base64Code);
            out.write(buffer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
        }
    }
}
