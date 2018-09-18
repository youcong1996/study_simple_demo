package com.beck.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.pdf.BaseFont;

public class PDFUtils {
	private static String fontBasePath=PDFUtils.class.getResource("/").getPath()+"font/";
	public static void htmlFileToPDFStream(File htmlFile, OutputStream output,
			File imageBaseURL) throws Throwable {
		if (htmlFile == null) {
			throw new RuntimeException("htmlFile is null");
		}
		if (output == null) {
			throw new RuntimeException("output is null");
		}

		// 生成ITextRenderer实例
		ITextRenderer renderer = new ITextRenderer();
		// 关联html页面
		renderer.setDocument(htmlFile.toURI().toURL().toString());
		// 设置获取图片的基本路径
		renderer.getSharedContext().setBaseURL(
				imageBaseURL.toURI().toURL().toString());
		// 设置字体路径，必须和html设置一致
		ITextFontResolver fontResolver = renderer.getFontResolver();
		
		
		fontResolver.addFont(fontBasePath+"msyh.ttc", BaseFont.IDENTITY_H,
				BaseFont.NOT_EMBEDDED);
		fontResolver.addFont(fontBasePath+"msyhl.ttc", BaseFont.IDENTITY_H,
				BaseFont.NOT_EMBEDDED);
		fontResolver.addFont(fontBasePath+"msyhbd.ttc", BaseFont.IDENTITY_H,
				BaseFont.NOT_EMBEDDED);
		renderer.layout();
		renderer.createPDF(output);
	}

	public static void htmlFileToPDFFile(File htmlFile, File pdfFile,
			File imageBaseURL) throws Throwable {
		OutputStream output = new FileOutputStream(pdfFile);
		try {
			htmlFileToPDFStream(htmlFile, output, imageBaseURL);
		} finally {
			if (output != null) {
				output.close();
			}
		}
	}

}
