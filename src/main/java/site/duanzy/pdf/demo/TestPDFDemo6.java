package site.duanzy.pdf.demo;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 
 * @ClassName: TestPDFDemo4
 * @Description: PDF中设置样式/格式化输出，输出中文内容
 * @author leo
 * @date 2018年4月4日下午5:53:17
 * @version v1.0
 */
public class TestPDFDemo6 {
	public static void main(String[] args) throws DocumentException, IOException {
        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("E:\\Pdf\\test6.pdf"));
        //打开文件
        document.open();
        
        //中文字体,解决中文不能显示问题
        BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        
        //蓝色字体
        Font blueFont = new Font(bfChinese);
        blueFont.setColor(BaseColor.BLUE);
        //段落文本
        Paragraph paragraphBlue = new Paragraph("paragraphOne blue front", blueFont);
        document.add(paragraphBlue);
     
        //绿色字体
        Font greenFont = new Font(bfChinese);
        greenFont.setColor(BaseColor.GREEN);
        //创建章节
        Paragraph chapterTitle = new Paragraph("段落标题xxxx", greenFont);
        Chapter chapter1 = new Chapter(chapterTitle, 1);
        chapter1.setNumberDepth(0);
       
        Paragraph sectionTitle = new Paragraph("部分标题", greenFont);
        Section section1 = chapter1.addSection(sectionTitle);
     
        Paragraph sectionContent = new Paragraph("部分内容", blueFont);
        section1.add(sectionContent);
        
        //将章节添加到文章中
        document.add(chapter1);
        
        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
    }
}
