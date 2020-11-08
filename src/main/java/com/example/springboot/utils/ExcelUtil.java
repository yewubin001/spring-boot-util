package com.example.springboot.utils;

import cn.magfin.util.DateTimeUtils;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.hibernate.validator.internal.util.privilegedactions.GetResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.net.URLEncoder;
import java.time.Instant;
import java.util.Date;
import java.util.List;
/**
 * @description: Excel工具类
 * @auther: YEWUBIN
 * @date: 2019/11/12
 */
public class ExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    private static final String EXCEL_TEMPLATE = "templates/template.xlsx";

    /**
     * 输出文件
     *
     * @param file     文件
     * @param fileName 文件名
     * @return ResponseEntity
     */
    private static ResponseEntity<FileSystemResource> outputFile(File file, String fileName) throws UnsupportedEncodingException {
        fileName = fileName + "_" + DateTimeUtils.formatDateTime(new Date(), "yyyyMMddHHmmss") + ".xlsx";
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        fileName = URLEncoder.encode(fileName, "UTF-8");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + fileName);
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", Instant.now().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));

        return ResponseEntity
            .ok()
            .headers(headers)
            .contentLength(file.length())
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(new FileSystemResource(file));
    }

    /***
     * 批量导入excel数据
     * @param inputStream
     * @param t
     * @param <T>
     * @throws IOException
     */
    public static <T extends AnalysisEventListener> void importExcel(InputStream inputStream, T t) throws IOException {
        ExcelReader excelReader = EasyExcelFactory.getReader(inputStream, t);
        List<Sheet> sheets = excelReader.getSheets();
        for (Sheet sheet : sheets) {
            sheet.setHeadLineMun(1);
            sheet.setClazz(getActualClass(t));
            excelReader.read(sheet);
        }
    }

    /***
     * 导出excel
     * @param excelData
     * @param clazz
     * @param sheetName
     * @param fileName
     * @param <T>
     * @return
     */
    public static <T extends BaseRowModel> ResponseEntity<FileSystemResource> excelExport(List<T> excelData, Class<? extends BaseRowModel> clazz, String fileName, String sheetName) {
        ResponseEntity<FileSystemResource> fileSystem = null;
        try (FileOutputStream outputStream = new FileOutputStream(GetResource.class.getClassLoader().getResource(EXCEL_TEMPLATE).getPath())) {
            ExcelWriter excelWriter = EasyExcelFactory.getWriter(outputStream, ExcelTypeEnum.XLSX, true);
            Sheet sheet = new Sheet(1, 0, clazz);
            sheet.setSheetName(sheetName);
            excelWriter.write(excelData, sheet);
            excelWriter.finish();
            File file = new File(GetResource.class.getClassLoader().getResource(EXCEL_TEMPLATE).getPath());
            fileSystem = outputFile(file, fileName);
        } catch (Exception e) {
            logger.error("数据excel导出异常", e);
        }
        return fileSystem;
    }

    private static Class getActualClass(Object t) {
        ParameterizedType genericSuperclass = (ParameterizedType) t.getClass().getGenericSuperclass();
        return (Class) genericSuperclass.getActualTypeArguments()[0];
    }
}
