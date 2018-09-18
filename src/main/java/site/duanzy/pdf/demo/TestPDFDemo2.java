package site.duanzy.pdf.demo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 
 * @ClassName: TestPDFDemo2
 * @Description: 给PDF文件设置文件属性
 * @author leo
 * @date 2018年4月4日下午5:49:23
 * @version v1.0
 */
public class TestPDFDemo2 {
	public static void main(String[] args) throws FileNotFoundException,
			DocumentException {

		// 创建文件
		Document document = new Document();
		// 建立一个书写器
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("E://Pdf//test2.pdf"));
		// 打开文件
		document.open();
		// 添加内容
		document.add(new Paragraph("Some content here"));

		// 设置属性
		// 标题
		document.addTitle("this is a title");
		// 作者
		document.addAuthor("Mr You");
		// 主题
		document.addSubject("this is subject");
		// 关键字
		document.addKeywords("Keywords");
		// 创建时间
		document.addCreationDate();
		// 应用程序
		document.addCreator("hd.com");

		// 关闭文档
		document.close();
		// 关闭书写器
		writer.close();
	}
}
