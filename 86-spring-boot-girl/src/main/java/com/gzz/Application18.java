package com.gzz;

import java.text.DecimalFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gzz.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application18 {
	private static String rootpath = "D:/aaa/";
	private static int num;
	private static DecimalFormat df = new DecimalFormat("00");
	private static String baseUrl = "https://www.wxytw.com/jgmntp/yuhuajie/page/";

	public static void main(String[] args) throws Exception {
		for (int i = 1; i < 34; i++) {
			Document document = Jsoup.connect(baseUrl + i).get();
//log.info(document);
			Elements selects = document.select("a.thumbnail");
			for (Element element : selects) {
				String href = element.attr("href");
				String setPath = element.select("img").first().attr("alt");
				log.info(setPath);
				Document pages = Jsoup.connect(href).get();
//log.info(document);
				Elements pics = pages.select("a.post-page-numbers");
				String pageNum = pics.last().select("span").first().text();
//log.info(pageNum);
				num = 0;
				for (int j = 1; j <= Integer.parseInt(pageNum); j++) {
					pages = Jsoup.connect(href + "/" + j).get();
					Elements images = pages.select(".article-content img");
					for (Element image : images) {
//log.info(image.attr("src"));
						String path = rootpath + setPath + "/" + df.format(num) + ".jpg";
						Utils.down(image.attr("src"), path, href);
						num++;
					}
				}
			}
		}
	}
}
