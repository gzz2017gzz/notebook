package com.gzz;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import com.gzz.utils.Utils;

import lombok.extern.slf4j.Slf4j;

//https://www.lesmao.co/
@Slf4j
public class Application10 {
 
	private static String rootpath = "e:/meiyan/";
	private static String host = "https://www.ku137.net/";
	private static int num;
	private static DecimalFormat df = new DecimalFormat("00");

	public static void main(String[] args) throws Exception {
		for (int j = 2; j < 13; j++) {
		
			Document document = Jsoup.connect("https://www.ku137.net/b/57/list_57_" + j + ".html").get();
//			log.info(document);
			Elements select = document.select(".l-pub").select(".cl a");
//			log.info(select);
			for (Element element : select) {
				log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>j={}",j);
//				log.info(element.attr("href"));
				List<String> set = new ArrayList<>();
				set.add(element.attr("href"));
				Document doc = Jsoup.connect(element.attr("href")).get();
				doc.select(".page a").forEach(i -> {
					if (!StringUtils.isEmpty(i.attr("href")))
						set.add("https://www.ku137.net/b/57/" + i.attr("href"));
				});
				set.remove(set.size()-1);
//				log.info(set);
				String setName = doc.select("head title").text().replace("[MiStar美女] ", "").replace(" - 美女写真网", "");
				log.info(setName);
				num = 1;
				for (String page : set) {
//					log.info(page);
					Elements images = Jsoup.connect(page).get().select(".content img");
					for (Element image : images) {
//						log.info(image.attr("src"));

						String path = rootpath + setName + "/" + df.format(num) + ".jpg";
						Utils.down(image.attr("src"), path, host);
						num++;
					}
 				}
 			}
		}
	}
}