package com.example.springboot.utils;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

/**
 * IO/NIO
 *
 * 文件复制并返回文件字节数组
 */
public class FileUtils {
    public static byte[] getContent(String filePath) throws IOException {
        long time = System.currentTimeMillis();
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            System.out.println("file too big...");
            return null;
        }
        FileInputStream fi = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream("C:/sign/002.docx");
        byte[] buffer = new byte[(int) fileSize];

        int len = 0;
        while ((len = fi.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        /*int offset = 0;
        int numRead = 0;
        while (offset < buffer.length
                && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset != buffer.length) {
            throw new IOException("Could not completely read file "
                    + file.getName());
        }*/
        fi.close();
        System.out.println(System.currentTimeMillis() - time);
        return buffer;
    }

    /**
     * the traditional io way
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(String filename) throws IOException {

        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();

        }
    }

    /**
     * NIO way
     * 实现文件的复制    FileChannel +ByteBuffer（非直接缓冲区）
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray2(String filename) throws IOException {
        long time = System.currentTimeMillis();
        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }

        FileChannel channel = null;
        FileChannel outchannel;
        FileInputStream fs = null;
        FileOutputStream fos = new FileOutputStream("C:/sign/001.docx");
        try {
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            outchannel = fos.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) != -1) {
                byteBuffer.flip();//切换为读数据的模式
                //如果这里不切换的话，缓存区会满
                outchannel.write(byteBuffer);
                byteBuffer.clear();//清空，读完当前的缓存区然后再继续
            }
            return byteBuffer.array();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() - time);
        }

    }

    /**
     * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
     * 使用FileChannel + MappedByteBuffer（直接缓冲区）-->物理内存映射文件
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray3(String filename) throws IOException {
        long time = System.currentTimeMillis();
        //1.创建Channel
        FileChannel inChannel = FileChannel.open(Paths.get(filename), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("C:/sign/channel.docx"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        //2.创建得到直接缓冲区
        MappedByteBuffer inMappedBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //3.数据的读写
        byte[] result = new byte[(int) inMappedBuffer.limit()];
        if (inMappedBuffer.remaining() > 0) {
            inMappedBuffer.get(result, 0, inMappedBuffer.remaining());
            outMappedBuffer.put(result);//从dst中将数据取出
        }

        //4.关闭资源
        inChannel.close();
        outChannel.close();
        System.out.println(System.currentTimeMillis() - time);
        return result;
    }

    /**
     * 下载远程文件保存并读取 网络地址
     */

    public static String downAndReadFile(String filePath) throws IOException {
        URL url = new URL(filePath);
        HttpURLConnection uc = (HttpURLConnection) url.openConnection();
        uc.setDoInput(true);//设置是否要从 URL 连接读取数据,默认为true
        uc.connect();
        try(InputStream iputstream = uc.getInputStream()) {
            byte[] res = IOUtils.toByteArray(iputstream);
            uc.disconnect();
            return Base64.getEncoder().encodeToString(res);
        }

    }

    public static String downAndReadFileWithRefer(String filePath,String referer) throws IOException {
        URL url = new URL(filePath);
        HttpURLConnection uc = (HttpURLConnection) url.openConnection();
        uc.setRequestProperty("referer",referer);
        uc.setDoInput(true);//设置是否要从 URL 连接读取数据,默认为true
        uc.connect();
        try(InputStream iputstream = uc.getInputStream()) {
            byte[] res = IOUtils.toByteArray(iputstream);
            uc.disconnect();
            return Base64.getEncoder().encodeToString(res);
        }

    }
}