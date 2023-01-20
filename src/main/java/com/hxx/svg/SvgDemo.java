package com.hxx.svg;

import org.apache.batik.transcoder.TranscoderException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

/**
 * <ul>
 * <li>功能说明：图片填充示例</li>
 * <li>作者：hou_x on 2023/1/20 14:15 </li>
 * </ul>
 */
public class SvgDemo {
    public static void main(String[] args) {
        String template = "";
        try {
            //读取模板文件
            template = new String(Files.readAllBytes(Paths.get("template/svg/template.svg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if ("".equals(template)) {
            return;
        }
        // 替换待替换的内容（一般为动态内容）
        String fillSvg = template.replace("${title}", "这是一个示例")
                .replace("${desc}", "现在是：" + new Date());

        try {
            // 宽高根据需要设置
            SvgUtil.convertSvgToPng(fillSvg, "target/svg/resule.png", 1024F, 1024F);
        } catch (IOException | TranscoderException e) {
            e.printStackTrace();
        }
        System.out.println("图片制作完成");
    }
}
