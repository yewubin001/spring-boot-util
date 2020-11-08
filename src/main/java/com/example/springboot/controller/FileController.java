package com.example.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * author:Ye wubin
 * Date:2018/8/29
 * Time:14:34
 * 文件上传，可以用postman请求。
 *
 */
@RestController
@Slf4j
public class FileController {

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IllegalStateException, IOException {
        // 判断是否为空文件
        if (file.isEmpty()) {
            return "上传文件不能为空";
        }
        // 文件类型
        String contentType = file.getContentType();
        // springmvc处理后的文件名
        String fileName = file.getName();
        //log.info("服务器文件名：{}", fileName);
        // 原文件名即上传的文件名
        String origFileName = file.getOriginalFilename();
        // 文件大小
        Long fileSize = file.getSize();

        // 保存文件
        // 可以使用二进制流直接保存
        // 这里直接使用transferTo
        file.transferTo(new File("C:\\sign"+File.separator + origFileName));

        return String.format(file.getClass().getName() + "方式文件上传成功！\n文件名:%s,文件类型:%s,文件大小:%s", origFileName, contentType,fileSize);

    }


//    @ApiOperation("影像件压缩文件下载")
//    @ApiImplicitParam(name = "applyId", value = "申请id")
//    @GetMapping(value = "/audit/cust-info/downLoadZipFile")
//    public void downLoadZipFile(String applyId, HttpServletResponse response) throws IOException {
//        log.info("request to download a zip file:{}", applyId);
//        String filename = new String("影像件.zip".getBytes("UTF-8"), "ISO8859-1");
//        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
//             ZipOutputStream zos = new ZipOutputStream(bos)) {
//            List<InfoCustImagesDTO> images = infoCustImagesService.getImages(applyId);
//            if (CollectionUtils.isNotEmpty(images)) {
//                for (InfoCustImagesDTO infoCustImagesDTO : images) {
//                    String imageId = infoCustImagesDTO.getImageId();
//                    String fileUrl = fileUploadOSSIntegrationService.getUrlByOSSKey(imageId);
//                    String fileBaseUrl = fileUrl.substring(0, fileUrl.indexOf("?"));
//                    String fileName = fileBaseUrl.substring(fileBaseUrl.lastIndexOf("/") + 1);
//                    zos.putNextEntry(new ZipEntry(fileName));
//                    byte[] bytes = fileService.getImageFromURL(fileUrl);
//                    zos.write(bytes, 0, bytes.length);
//                    zos.closeEntry();
//                }
//                response.setContentType("application/octet-stream;charset=utf-8");
//                response.setHeader("Content-Disposition", "attachment;filename=" + filename);
//                try (OutputStream out = response.getOutputStream()) {
//                    out.write(bos.toByteArray());
//                }
//            }
//        } catch (Exception e) {
//            log.error("下载全部影像件异常：{}", e.getMessage());
//        }
//
//    }

    /**
     * 根据文件链接把文件下载下来并且转成字节码
     *
     * @param fileUrl
     * @return
     */
//    public byte[] getImageFromURL(String fileUrl) {
//        URLConnection conn = null;
//        byte[] data = null;
//        try {
//            URL url = new URL(fileUrl);
//            conn = url.openConnection();
//        } catch (IOException e) {
//            log.error("根据文件链接转成字节码错误：" + e.getMessage(), e);
//        }
//        try (InputStream inStream = conn.getInputStream()) {
//            data = IOUtils.toByteArray(inStream);
//        } catch (IOException e) {
//            log.error("根据文件链接转成字节码错误：" + e.getMessage(), e);
//        }
//        return data;
//    }

    /**
     * 读取文件获得字节数组
     *
     * @param is
     * @return
     */
//    private byte[] readInputStream(InputStream is) {
//        byte[] buffer = new byte[1024];
//        byte[] fileByte = null;
//        int length;
//        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
//            while ((length = is.read(buffer)) != -1) {
//                baos.write(buffer, 0, length);
//            }
//            baos.flush();
//            fileByte = baos.toByteArray();
//        } catch (IOException e) {
//            log.error("读取文件流异常", e);
//        }
//        return fileByte;
//    }
}