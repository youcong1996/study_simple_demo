package site.duanzy.pdf.demo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 
 * @ClassName: TestPDFDemo4
 * @Description: PDF中创建表格
 * @author leo
 * @date 2018年4月4日下午5:53:17
 * @version v1.0
 */
public class TestPDFDemo4 {
	public static void main(String[] args) throws DocumentException, FileNotFoundException {
        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("E:\\Pdf\\test4.pdf"));
        //打开文件
        document.open();
        //添加内容
        document.add(new Paragraph("HD content here"));
        
        /*********************************/
        //定义2列的表格
        PdfPTable table2 = new PdfPTable(2); 
        List<PdfPRow> listRow2 = table2.getRows();
        
        //发货人信息
        PdfPCell cells3[]= new PdfPCell[2];
        PdfPRow row3 = new PdfPRow(cells3);
        cells3[0] = new PdfPCell(new Paragraph("111111111111"));
        cells3[1] = new PdfPCell(new Paragraph("CI"));
        
        //收货人信息
        PdfPCell cells4[]= new PdfPCell[2];
        PdfPRow row4 = new PdfPRow(cells4);
        cells4[0] = new PdfPCell(new Paragraph("222222222"));
        cells4[1] = new PdfPCell(new Paragraph("CI2"));
        
        listRow2.add(row3);
        listRow2.add(row4);
        
        /*********************************/
     
        // 3列的表.
        PdfPTable table = new PdfPTable(3); 
        table.setWidthPercentage(100); // 宽度100%填充
        table.setSpacingBefore(10f); // 前间距
        table.setSpacingAfter(10f); // 后间距

        List<PdfPRow> listRow = table.getRows();
        //设置列宽
        float[] columnWidths = { 1f, 2f, 3f};
        table.setWidths(columnWidths);
        
        //生成列表数据
        for(int i = 0; i < 10 ; i ++){
        	 //行1
            PdfPCell cells1[]= new PdfPCell[3];
            PdfPRow row1 = new PdfPRow(cells1);
           
            //单元格
            cells1[0] = new PdfPCell(new Paragraph("row: "+i+" ,cols:1"));//单元格内容
//            cells1[0].setBorderColor(BaseColor.BLUE);//边框验证
//            cells1[0].setPaddingLeft(20);//左填充20
//            cells1[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
//            cells1[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中

            cells1[1] = new PdfPCell(new Paragraph("row: "+i+" ,cols:2"));
            cells1[2] = new PdfPCell(new Paragraph("row: "+i+" ,cols:3"));
            
            listRow.add(row1);
        }
        
       
        
        //行2
//        PdfPCell cells2[]= new PdfPCell[3];
//        PdfPRow row2 = new PdfPRow(cells2);
//        cells2[0] = new PdfPCell(new Paragraph("444"));
//        cells2[1] = new PdfPCell(new Paragraph("555"));
//        cells2[2] = new PdfPCell(new Paragraph("666"));
        
       

        //把第一行添加到集合
       
//        listRow.add(row2);
        //把表格添加到文件中
        document.add(table2);
        document.add(table);
        
        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
    }
}
