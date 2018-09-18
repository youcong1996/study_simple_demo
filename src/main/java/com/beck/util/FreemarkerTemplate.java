package com.beck.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerTemplate {

	private final Configuration configuration = new Configuration(
			Configuration.VERSION_2_3_23);
	private String charset;

	public FreemarkerTemplate(String charset) {
		this.charset = charset;
		configuration.setEncoding(Locale.CHINA, charset);
		configuration.setClassicCompatible(true);//处理空值为空字符串  
	}

	public void setTemplateClassPath(Class resourceLoaderClass,
			String basePackagePath) {
		configuration.setClassForTemplateLoading(resourceLoaderClass,
				basePackagePath);
	}

	public void setTemplateDirectoryPath(String templatePath)
			throws IOException {
		configuration.setDirectoryForTemplateLoading(new File(templatePath));
	}

	public void processToStream(String templateFileName,
			Map<String, Object> dataMap, Writer writer) throws Throwable {
		Template template = configuration.getTemplate(templateFileName);
		template.process(dataMap, writer);
	}

	public void processToFile(String templateFileName,
			Map<String, Object> dataMap, File outFile) throws Throwable {
		Writer writer = new OutputStreamWriter(new FileOutputStream(outFile),
				charset);
		try {
			processToStream(templateFileName, dataMap, writer);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	public String processToString(String templateFileName,
			Map<String, Object> dataMap) throws Throwable {
		Writer writer = new StringWriter(2048);
		try {
			processToStream(templateFileName, dataMap, writer);
			return writer.toString();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}


}
