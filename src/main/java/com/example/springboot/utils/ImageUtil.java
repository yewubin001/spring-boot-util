package com.example.springboot.utils;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;

public class ImageUtil {

  private static final Logger log = LoggerFactory.getLogger(ImageUtil.class);

  /**
   * save eEchart base64 content to png
   *
   * @param base64Info
   * @param path
   */
  public static void saveEchartToPng(String base64Info, String path) {
    base64Info = base64Info.replaceAll(" ", "+");
    String[] arr = base64Info.split("base64,");
    try (OutputStream out = new FileOutputStream(path)) {
      byte[] buffer = Base64.decodeBase64(arr[1]);
      out.write(buffer);
    } catch (IOException e) {
      log.error("解析Base64图片信息并保存到某目录下出错!", e);
    }
  }

  /**
   * @param base64Content
   * @param path
   */
  public static void saveSvgToPng(String base64Content, String path) {
    try (InputStream svgFileStream = new ByteArrayInputStream(base64Content.getBytes(Charset.forName("UTF-8")));
         FileOutputStream pngFileStream = new FileOutputStream(path)) {
      TranscoderInput inputSvgImage = new TranscoderInput(svgFileStream);
      PNGTranscoder converter = new PNGTranscoder();
      TranscoderOutput outputPngImage = new TranscoderOutput(pngFileStream);
      converter.transcode(inputSvgImage, outputPngImage);
    } catch (IOException e) {
      log.error("read {} io exception", path, e);
    } catch (TranscoderException e) {
      log.error("transcode error with {}", e.getMessage(), e);
    }
  }

  /**
   *
   * http://tool.chinaz.com/tools/imgtobase/
   *
   * 等比例压缩算法：
   * 算法思想：根据压缩基数和压缩比来压缩原图，生产一张图片效果最接近原图的缩略图
   *
   * @param srcURL  原图地址
   * @param deskURL 缩略图地址
   * @param comBase 压缩基数
   * @param scale   压缩限制(宽/高)比例  一般用1：
   *                当scale>=1,缩略图height=comBase,width按原图宽高比例;若scale<1,缩略图width=comBase,height按原图宽高比例
   */
  public static void thinImage(String srcURL, String deskURL, double comBase, double scale) throws IOException {
    File srcFile = new File(srcURL);
    Image img = ImageIO.read(srcFile);
    int srcHeight = img.getHeight(null);
    int srcWidth = img.getWidth(null);
    // 缩略图高
    int deskHeight;
    // 缩略图宽
    int deskWidth;
    double srcScale = (double) srcHeight / srcWidth;
    //缩略图宽高算法高度或者宽度超过规定才进行压缩
    if ((double) srcHeight > comBase || (double) srcWidth > comBase) {
      if (srcScale >= scale || 1 / srcScale > scale) {
        if (srcScale >= scale) {
          deskHeight = (int) comBase;
          deskWidth = srcWidth * deskHeight / srcHeight;
        } else {
          deskWidth = (int) comBase;
          deskHeight = srcHeight * deskWidth / srcWidth;
        }
      } else {
        if ((double) srcHeight > comBase) {
          deskHeight = (int) comBase;
          deskWidth = srcWidth * deskHeight / srcHeight;
        } else {
          deskWidth = (int) comBase;
          deskHeight = srcHeight * deskWidth / srcWidth;
        }
      }
      Thumbnails.of(new File(srcURL)).size(deskWidth, deskHeight).toFile(deskURL);
    }

  }
}
