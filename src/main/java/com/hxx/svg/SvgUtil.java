package com.hxx.svg;


import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * <ul>
 * <li>功能说明：图片动态填充文字工具</li>
 * <li>作者：hou_x on 2023/1/20 14:34 </li>
 * </ul>
 */

public class SvgUtil {

    /**
     * 转换 svg 至 png 格式
     *
     * @param svgCode
     * @param pngFilePath
     * @param targetWidth
     * @param targetHeight
     * @throws IOException
     * @throws TranscoderException
     */
    public static void convertSvgToPng(String svgCode, String pngFilePath, float targetWidth, float targetHeight) throws IOException, TranscoderException {
        File file = new File(pngFilePath);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            convertToPng(svgCode, outputStream, targetWidth, targetHeight);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将svgCode转换成png文件，直接输出到流中
     *
     * @param svgCode      svg代码
     * @param outputStream 输出流
     * @throws TranscoderException 异常
     * @throws IOException         io异常
     */
    public static void convertToPng(String svgCode, OutputStream outputStream, float targetWidth, float targetHeight) throws TranscoderException, IOException {
        try {
            byte[] bytes = svgCode.getBytes(StandardCharsets.UTF_8);
            // 同理可转换为 JPG、TIFF格式
            PNGTranscoder t = new PNGTranscoder();
            TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(bytes));
            TranscoderOutput output = new TranscoderOutput(outputStream);
            // 增加图片的属性设置(单位是像素)
            t.addTranscodingHint(ImageTranscoder.KEY_WIDTH, targetWidth);
            t.addTranscodingHint(ImageTranscoder.KEY_HEIGHT, targetHeight);
            t.transcode(input, output);
            outputStream.flush();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
