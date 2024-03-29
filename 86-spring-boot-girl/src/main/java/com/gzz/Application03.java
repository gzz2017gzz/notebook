package com.gzz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gzz.utils.Utils;

//http://www.quantuwang.co
public class Application03 {
	private static Log logger = LogFactory.getLog(Application03.class);
	private static String root = "D:/mitao/";
	private static String base = "http://www.quantuwang.co";

	public static void main(String[] args) throws IOException {
		String baseUrl = "http://www.quantuwang.co/meinv/miitao/";
		List<String> pagesList = new ArrayList<>();
		pagesList.add(baseUrl);
		for (int j = 2; j < 5; j++) {
			pagesList.add(baseUrl + "index_" + j + ".html");
		}
		for (String string : pagesList) {
			Document document = Jsoup.connect(string).get();
			Elements select = document.select(".ul960c a");
			for (Element element : select) {
				String path = element.attr("title").replace(" ", "");
				logger.info(path);
				Document document2 = Jsoup.connect(base + element.attr("href")).get();
				List<String> pages = new ArrayList<>();
				String attr = document2.select(".c_img a img").get(0).attr("src").replace("1.jpg", "");
				Elements select2 = document2.select(".c_page a");
				int pageCount = Integer.parseInt(select2.get(select2.size() - 2).text());
				for (int i = 1; i < pageCount + 1; i++)
					pages.add(attr + i + ".jpg");
				for (String url : pages) {
					Utils.down(url, root + path + "/" + url.split("/")[7], base);
				}
			}
		}
	}
}
