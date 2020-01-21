package com.gzz;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application5 {
	private static Log logger = LogFactory.getLog(Application5.class);
	private BufferedImage bi;
	private static List<String> pages = new ArrayList<>();
	private static String setName;

//	int vol=123;

	public static void main(String[] args) {
	
		String url = "https://www.meituri.com/x/44/";
		setName = "动感之星";
		pages.add(url);
		for (int i = 1; i <= 6; i++)
			pages.add(url + "index_" + i + ".html");
		SpringApplication.run(Application5.class, args);
	}

	@PostConstruct
	private void pages() throws Exception {
		InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("no.jpg");
		bi = ImageIO.read(resourceAsStream);
		for (String url : pages) {
			Document page = Jsoup.connect(url).get();
			Elements lis = page.getElementsByClass("hezi").get(0).getElementsByTag("li");
			// logger.info(lis);
			for (Element element : lis) {
				String pic_number = element.child(1).text().split("P")[0];
				String split = element.child(4).child(0).text().replaceAll(" ", "").replaceAll("写真集", "").toUpperCase().replaceAll(setName.toUpperCase(), "");
				String path = split.replaceAll("<", "").replaceAll(">", "").replaceAll("/", "").replaceAll("\\\\", "").replaceAll(":", "")
						.replaceAll("NO.", "VOL.");
				String[] split2 = path.split("VOL.");
				path = "VOL." + split2[1] + "-" + split2[0];
//
				logger.info(path);
				String dir = element.child(0).attr("href").split("/")[4];
				downPicSets(dir, new Integer(pic_number), path);
			}
		}
	}

	private void downPicSets(String 源站目录, int 图片数, String 图集名称) throws IOException {

		for (int i = 1; i <= 图片数; i++) {
			String 文件路径 = "e:/" + setName + "/" + 图集名称 + "/" + i + ".jpg";
			File file = new File(文件路径);
			if (!file.exists()) {
				String attr = "https://ii.hywly.com/a/1/" + 源站目录 + "/" + i + ".jpg";
				try {
					URL imgurl = new URL(attr);
					URLConnection connection = imgurl.openConnection();
					connection.setRequestProperty("Referer", "https://www.meituri.com");
					if (!file.getParentFile().exists())
						file.getParentFile().mkdirs();
					Files.write(Paths.get(文件路径), InputStreamToBytes(connection.getInputStream()));
				} catch (Exception e) {
					logger.error("服务器上没有这个文件！" + attr + ",本机目标路径:" + 文件路径);
					ImageIO.write(bi, "png", file);
				}
			}
		}
	}

	public final byte[] InputStreamToBytes(InputStream inStream) throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = inStream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		byte[] in2b = swapStream.toByteArray();
		return in2b;
	}
}
