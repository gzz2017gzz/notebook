package com.gzz;

import java.text.DecimalFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gzz.utils.Utils;

//https://www.yuleba.org
public class Application16 {
	private static Log logger = LogFactory.getLog(Application16.class);
	private static String rootpath = "E:/xiaoyu/";
	private static String host = "https://www.yuleba.org/";
	private static int num;
	private static DecimalFormat df = new DecimalFormat("00");

	public static void main(String[] args) throws Exception {
		for (int j = 1; j < 3; j++) {
			Elements select = Jsoup.connect("https://www.yuleba.org/b/262-" + j + ".html").get().select(".b_img li h3 a");
			for (Element href : select) {
				String page0 = href.attr("href");
				String curSet = href.text().substring(23, 27);

				int parseInt = Integer.parseInt(curSet);
				if (parseInt < 5000 && parseInt > 3070) {
//				if (parseInt < 2594 && parseInt > 2570) {
					logger.info(href.text() + curSet);
					int pageCount = Integer.parseInt(Jsoup.connect(host + page0).get().select(".paging a").get(0).text().replace("共", "").replace("页:", ""));
					num = 0;
					for (int i = 0; i < pageCount; i++) {
						Elements page = Jsoup.connect(host + page0.replace("-0.", "-" + i + ".")).get().select(".picture img");
						page.forEach(img -> {
							String path = rootpath + href.text() + "/" + df.format(num) + ".webp";
							Utils.down(img.attr("src"), path, "https://www.yuleba.org/");
							num++;
						});
					}
				}
			}
		}
	}
}