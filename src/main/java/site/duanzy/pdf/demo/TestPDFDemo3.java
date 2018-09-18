package site.duanzy.pdf.demo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 
 * @ClassName: TestPDFDemo3
 * @Description: PDF中添加图片
 * @author leo
 * @date 2018年4月4日下午5:50:31
 * @version v1.0
 */
public class TestPDFDemo3 {
	public static void main(String[] args) throws DocumentException,
			IOException {

		// 创建文件
		Document document = new Document();
		// 建立一个书写器
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("E://Pdf//test3.pdf"));
		// 打开文件
		document.open();
		// 添加内容
		document.add(new Paragraph("HD content here"));

		// 图片1
		Image image1 = Image.getInstance("E://Demo//workspace//java_pdf//src//main//webapp//images//test.png");
		// 设置图片位置的x轴和y周
		image1.setAbsolutePosition(100f, 550f);
		// 设置图片的宽度和高度
		image1.scaleAbsolute(200, 200);
		// 将图片1添加到pdf文件中
		document.add(image1);

		// 图片2
		Image image2 = Image.getInstance(new URL("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=bda2ad09277f9e2f74351a0a2f31e962/0b46f21fbe096b63ea0d41bf0c338744eaf8accc.jpg"));
		// 将图片2添加到pdf文件中
		document.add(image2);

		// 关闭文档
		document.close();
		// 关闭书写器
		writer.close();
	}
}
