package com.example.springboot;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

public class SpringbootApplicationTests {

    public static void main(String[] args) {
        // 定义要生成二维码的基本参数
        int width = 300;
        int height = 300;
        String type = "png";
        String content = "贵凯莉，宇宙无敌超级大漂亮";


        // 定义二维码的配置，使用HashMap
        HashMap hints = new HashMap();
        // 字符集，内容使用的编码
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 容错等级，H、L、M、Q
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        // 边距，二维码距离边的空白宽度
        hints.put(EncodeHintType.MARGIN, 2);
        try {
            // 生成二维码对象，传入参数：内容、码的类型、宽高、配置
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            // 定义一个路径对象
            Path file = new File("D:/code.png").toPath();
            // 生成二维码，传入二维码对象、生成图片的格式、生成的路径
            MatrixToImageWriter.writeToPath(bitMatrix, type, file);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
