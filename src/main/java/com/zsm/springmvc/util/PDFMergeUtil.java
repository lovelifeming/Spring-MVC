package com.zsm.springmvc.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.*;
import org.apache.log4j.LogManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * PDF文件合并操作
 * http://www.cnblogs.com/liudongdong666666/p/7811635.html
 *
 * @Author: zsm.
 * @Description:
 * @Date:Created in 2017/11/10 21:58.
 * @Modified By:
 */
public class PDFMergeUtil
{
    /**
     * Log4j2日志记录器
     */
    private static final org.apache.log4j.Logger logger = LogManager.getLogger(PDFMergeUtil.class);

    /**
     * @param sourceFilePath
     * @param targetFilePath
     * @param paginate
     */
    public static void mergePDF(List<String> sourceFilePath, String targetFilePath, boolean paginate)
    {
        logger.info("pdf文件合并 start......");
        //创建新的pdf文档
        Document document = new Document();
        PdfWriter writer = null;
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try
        {
            //创建读取Pdf 文件对象
            List<PdfReader> readers = new ArrayList<PdfReader>();
            for (String filePath : sourceFilePath)
            {
                fis = new FileInputStream(filePath);

                PdfReader pdfReader = new PdfReader(fis);
                readers.add(pdfReader);
            }
            //读取pdf文件
            fos = new FileOutputStream(targetFilePath);
            //生成新的文档
            writer = PdfWriter.getInstance(document, fos);
            //打开文档
            document.open();
            //设置字体
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            PdfContentByte cb = writer.getDirectContent();

            PdfImportedPage page = null;
            int currentPageNumber = 0;
            int pageOfCurrentReaderPDF = 0;
            Iterator<PdfReader> iteratorPDFReader = readers.iterator();

            //依次读取pdf
            while (iteratorPDFReader.hasNext())
            {
                PdfReader pdfReader = iteratorPDFReader.next();
                // Create a new page in the target for each source page.
                while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages())
                {
                    document.newPage();
                    pageOfCurrentReaderPDF++;
                    currentPageNumber++; //创建当前页码
                    page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
                    cb.addTemplate(page, 0, 0);

                    // Code for pagination.
                    if (paginate)
                    {
                        cb.beginText();
                        cb.setFontAndSize(bf, 9);//字体
                        cb.showTextAligned(PdfContentByte.ALIGN_CENTER, String.valueOf(currentPageNumber), 290, 50, 0);
                        cb.endText();
                    }
                }
                pageOfCurrentReaderPDF = 0;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (document.isOpen())
                {
                    document.close();
                }
                if (null != fis)
                {
                    fis.close();
                }
                if (null != fos)
                {
                    fos.close();
                }
                if (null != writer)
                {
                    writer.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
