package com.hxx;

import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：tal on 2018/4/4 0004 13:44 </li>
 * <li>邮箱：hou_xiangxiang@126.com</li>
 * </ul>
 */

public class Demo {
    private static final String TEMPLATE_PATH = "template/template.pdf";
    private static final String IMAGE_PATH = "template/signet.png";
    private static final String IMAGE_FIELD_NAME = "gender";

    private static final String TEXT_RESULT_PATH = "target/text_result.pdf";
    private static final String IMAGE_RESULT_PATH = "target/image_result.pdf";

    public static void main(String[] args) {
        //文字填充样例
        fillTextDemo();
        //图像填充样例
        fillImageDemo();
    }

    /**
     * 图像填充示例
     */
    private static void fillImageDemo() {
        InputStream is = null;
        OutputStream os = null;
        PdfStamper stamper = null;
        PdfReader pdfReader = null;
        int pageNo;
        float x, y, width, height;
        try {
            is = new FileInputStream(TEXT_RESULT_PATH);
            os = new FileOutputStream(IMAGE_RESULT_PATH);
            pdfReader = new PdfReader(is);
            // 读取模板文件
            stamper = new PdfStamper(pdfReader, os);
            /******* 使用表单划定位置、宽高 start  *****/
            // 提取pdf中的表单
//            AcroFields form = stamper.getAcroFields();
//            form.addSubstitutionFont(BaseFont.createFont("STSong-Light",
//                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));
//            // 获取指定控件（域名）所在页面及位置（左下角为起点）
//            pageNo = form.getFieldPositions(IMAGE_FIELD_NAME).get(0).page;
//            Rectangle signRect = form.getFieldPositions(IMAGE_FIELD_NAME).get(0).position;
//            x = signRect.getLeft();
//            y = signRect.getBottom();
//            width=signRect.getWidth();
//            height=signRect.getHeight();
            /******* 使用表单划定位置、宽高 end *****/

            //固定页码、图像大小及图像位置（左下角为 0 ,0）
            pageNo = 1;
            x = 100;
            y = 700;
            width = 50;
            height = 50;

            //设置图像
            PdfContentByte pdfContentByte = stamper.getOverContent(pageNo);
            Image img = Image.getInstance(IMAGE_PATH);
            // 图像宽高 (可根据预设控件大小)
            img.scaleAbsolute(width, height);
            //设置图片位置（以左下角为起点
            img.setAbsolutePosition(x, y);
            pdfContentByte.addImage(img);
            stamper.close();
            pdfReader.close();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != stamper) {
                try {
                    stamper.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (null != pdfReader) {
                try {
                    pdfReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("图像填充执行完毕");
    }

    /**
     * 文字填充示例
     */
    private static void fillTextDemo() {
        Map map = new HashMap<String, String>();
        map.put("name", "张三");
        map.put("man1", "√");
        map.put("gender", "dog");
        try {
            new Demo().convertTransData(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("文字填充执行完毕");
    }

    /**
     * 将数据转换为输入字节流
     */
    protected InputStream convertTransData(Map map)
            throws Exception {
        if (map == null || map.isEmpty()) {
            return null;
        }
        try {
            InputStream in = new FileInputStream(TEMPLATE_PATH);
            ByteArrayOutputStream out =
                    (ByteArrayOutputStream) generate(
                            new PdfReader(in),
                            map);

            ByteArrayInputStream ret =
                    new ByteArrayInputStream(out.toByteArray());
            //将pdf字节流输出到文件流
            OutputStream fos = new FileOutputStream(TEXT_RESULT_PATH);
            fos.write(out.toByteArray());
            fos.close();
            out.close();
            return ret;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 将数据，填入pdf表单域
     */
    public static OutputStream generate(PdfReader template, Map data)
            throws Exception {
        // 使用 itext-asian 包里的中文字体（中文仅有STSong-Light，华文宋体 一个字体）
        BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        // 可以选择使用其他字体文件，避免应用程序运行在 linux 环境时缺少字体文件
        // 微软雅黑仅用于示例，注意字体商业版权
//        BaseFont  bfChinese = BaseFont.createFont("template/microsoft-yahei.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);


        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfStamper stamp = new PdfStamper(template, out);
            AcroFields form = stamp.getAcroFields();

            // set the field values in the pdf form
            for (Iterator it = data.keySet().iterator(); it.hasNext(); ) {
                String key = (String) it.next();
                String value = (String) data.get(key);
                form.setFieldProperty(key, "textfont", bfChinese, null);
                form.setField(key, value);

            }

            stamp.setFormFlattening(true);
            stamp.close();
            template.close();

            return out;

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }

    }


}
