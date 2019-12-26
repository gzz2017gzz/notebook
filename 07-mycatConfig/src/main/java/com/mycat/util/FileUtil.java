package com.mycat.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author 高振中
 * @功能描述: 文件读写辅助类
 * @date 2017-10-17
 */
public class FileUtil {
	@SuppressWarnings("unused")
	private static Log logger = LogFactory.getLog(FileUtil.class);

	/**
	 * @功能描述: 以工作空间编码写文本文件
	 */
	public static void writeFile(String path, String context) {
		createDir(path);
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
			writer.write(context);
			writer.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @功能描述: 如果目录, 不存在创建这个目录
	 */
	public static void createDir(String path) {
		File file = new File(path);
		File parent = file.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
		if (parent != null) {
			parent.setWritable(true);
			parent.setReadable(true);
			parent.setExecutable(true);
		}
	}

}
