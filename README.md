# PDF / 图片动态填充
## 借助 itext 实现 java 代码填充 PDF 表单 
> 适用于目标输出文件格式为 PDF 的场景，比如签名文档等

### 引用 jar 包
- itextpdf
> maven 引用
```aidl
<dependency>
    <groupId>com.itextpdf</groupId>
    <artifactId>itextpdf</artifactId>
    <version>5.4.3</version>
</dependency>
```
- itext-asian （中文时需要，仅包含华文宋体一种中文字体，选择其他字体可不引入）
> maven 引用
```
<dependency>
    <groupId>com.itextpdf</groupId>
    <artifactId>itext-asian</artifactId>
    <version>5.2.0</version>
</dependency>
```
### 示例代码
#### 文字填充 PDF 表单
Demo.fillTextDemo()
### PDF 表单添加图片（可通过表单定位或者绝对位置定位）
Demo.fillImageDemo()

### PDF模板制作
使用 Adobe Acrobat 或 福昕高级PDF编辑器 制作即可

ps:附 福昕高级PDF编辑器 9.0.1 企业版安装包及破解文件       
福昕高级PDF编辑器 9.0.1 企业版安装包     
链接：https://pan.baidu.com/s/1QzQUgWrHQt_c6HMEy-y3mA 密码：qcxc      
破解文件        
链接：https://pan.baidu.com/s/1KmeWWCDemvVTzQwe2dM4aA 密码：oytl      

## 借助  batik 实现 java 代码填充图片
> 适用于目标输出文件格式为图片（png/jpg/tiff）的场景，比如各类回单。图片能输出比 PDF 更加绚丽的效果。
> SVG 是一种开放标准的矢量图形语言，是基于 XML、可缩放的矢量图形。可将其当作 XML 进行编辑，以实现动态填充的效果。
> UI 设计软件可直接导出 SVG 格式效果图 

### 引用 jar 包
- batik
> maven 引用
```
<dependency>
    <groupId>org.apache.xmlgraphics</groupId>
    <artifactId>batik-all</artifactId>
    <version>${batik.version}</version>
    <type>pom</type>
</dependency>
<dependency>
    <groupId>org.apache.xmlgraphics</groupId>
    <artifactId>batik-transcoder</artifactId>
    <version>${batik.version}</version>
</dependency>
<!-- 转码实现，可替代 -->
<dependency>
    <groupId>org.apache.xmlgraphics</groupId>
    <artifactId>batik-codec</artifactId>
    <version>${batik.version}</version>
</dependency>
```
### 示例代码
#### 文字动态填充 SVG 模板并转码为常见图片格式（png/jpg）
SvgDemo.main()
