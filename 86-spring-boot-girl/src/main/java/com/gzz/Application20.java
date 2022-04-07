package com.gzz;

import java.io.IOException;
import java.text.DecimalFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gzz.utils.Utils;

public class Application20 {
//	private static Log logger = LogFactory.getLog(Application20.class);
	private static String root = "E:/aaa/";
	private static DecimalFormat df = new DecimalFormat("00");
	public static void main(String[] args) throws IOException {
	 
		String url = "http://www.ncjlh.com/view-1557-1.html";
		
		Elements elements = Jsoup.connect(url).get().select(".post-content img");
		int i=1;
		for (Element element : elements) {
			String path = root   + df.format(i)+".jpg";
			Utils.down("http://www.ncjlh.com"+element.attr("src"), path, "http://www.ncjlh.com");
			i++;
		}
	}
}