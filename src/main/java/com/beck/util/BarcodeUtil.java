package com.beck.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import sun.misc.BASE64Encoder;

public class BarcodeUtil {
    
	public static final String IMG_TYPE_PNG="image/png";
	
	public static final String IMG_TYPE_GIF="image/gif";
	
	public static final String IMG_TYPE_JPEG="image/jpeg";
	
	public static String generateToBase64(String msg,String imgType) throws IOException {
		ByteArrayOutputStream ous = new ByteArrayOutputStream();
		generateToStream(msg,imgType, ous);
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(ous.toByteArray());
	}

	public static File generateToFile(String msg,String imgType, File file)
			throws IOException {
		FileOutputStream out = new FileOutputStream(file);
		try {
			generateToStream(msg,imgType, out);
		} finally {
			if (out != null) {
				out.close();
			}
		}
		return file;
	}

	public static byte[] generateToByte(String msg,String imgType) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			generateToStream(msg,imgType, out);
			return out.toByteArray();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	public static void generateToStream(String msg, String imgType,OutputStream out)
			throws IOException {
		if (msg == null || out == null) {
			return;
		}
		Code39Bean bean = new Code39Bean();
		// 精细度
		int dpi = 150;
		// module宽度
		double moduleWidth = UnitConv.in2mm(1.0f / dpi);
		bean.setModuleWidth(moduleWidth);
		// 不显示文字
		bean.setMsgPosition(HumanReadablePlacement.HRP_NONE);
		bean.setHeight(5);
		bean.setWideFactor(3);
		bean.doQuietZone(false);
	
		// 输出到流
		BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, imgType,
				dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
		// 生成条形码
		bean.generateBarcode(canvas, msg);
		// 结束绘制
		canvas.finish();
	}


}
