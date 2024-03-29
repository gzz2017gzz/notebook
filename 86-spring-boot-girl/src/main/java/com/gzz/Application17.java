package com.gzz;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gzz.utils.Utils;
//https://www.ku137.net/b/64/
public class Application17 {
	private static Log logger = LogFactory.getLog(Application17.class);
	private static String rootpath = "E:/my/";
	private static String host = "https://www.yuleba.org/";
	private static int num;
	private static DecimalFormat df = new DecimalFormat("00");
	public static void main(String[] args) throws Exception {
		String base = "https://www.ku137.net/b/64/";
		for (int j = 20; j < 21; j++) {
			Elements elements = Jsoup.connect("https://www.ku137.net/b/64/list_64_" + j + ".html").get().select(".m-list.ml1 a");
			for (Element element : elements) {
 				Document document = Jsoup.connect(element.attr("href")).get();
				String setName = document.select(".position div.w1200").text().replace("当前位置 ： 主页 > 标签分类 > MYGIRL美媛馆 > [美媛馆MYGIRL] ", "");
				Elements selects = document.select(".page a[href]");
 				logger.info( setName);
 				if(setName.equals("2014.10.13 Vol.065 于大小姐AYU")) {
 					selects.remove(selects.size() - 1);
 					List<String> pages = new ArrayList<>();
 					pages.add(element.attr("href"));
 					for (Element a_href : selects) {
 						pages.add(base + a_href.attr("href"));
 					}
 					num = 1;
 					for (String page : pages) {
 						Elements images = Jsoup.connect(page).get().select(".content img");
 						for (Element img : images) {
 							String path = rootpath + setName + "/" + df.format(num) + ".jpg";
 							Utils.down(img.attr("src"), path, host);
 							logger.info(img.attr("src"));
 							num++;
 						}
 					}
 				}
				
			}

		}
	}
}