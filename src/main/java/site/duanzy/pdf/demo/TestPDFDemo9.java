package site.duanzy.pdf.demo;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;


public class TestPDFDemo9 {
	
public static void main(String[] args) throws DocumentException, IOException {
        
        //读取pdf文件
        PdfReader pdfReader = new PdfReader("E:\\Pdf\\test.pdf");
     
        //修改器
        PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("E:\\Pdf\\test9.pdf"));
     
        Image image = Image.getInstance("E:\\Demo\\workspace\\java_pdf\\src\\main\\webapp\\images\\test.png");
        image.scaleAbsolute(50, 50);
        image.setAbsolutePosition(0, 700);
     
        for(int i=1; i<= pdfReader.getNumberOfPages(); i++)
        {
            PdfContentByte content = pdfStamper.getUnderContent(i);
            content.addImage(image);
        }
     
        pdfStamper.close();
    }
	
}
