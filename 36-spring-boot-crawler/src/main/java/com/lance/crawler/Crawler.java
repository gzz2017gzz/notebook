package com.lance.crawler;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Crawler {
	private static Logger logger = LoggerFactory.getLogger(Crawler.class);
	private OkHttpClient client = new OkHttpClient.Builder().build();
	private final Set<HttpUrl> fetchedUrls = Collections.synchronizedSet(new LinkedHashSet<HttpUrl>());
	private final BlockingQueue<HttpUrl> queue = new LinkedBlockingQueue<>();
	private final ConcurrentMap<String, AtomicInteger> hostnames = new ConcurrentHashMap<>();

	public void setUrls(String[] urls) {
		for (String url : urls) {
			queue.add(HttpUrl.parse(url));
		}
	}

	public void parallelDrainQueue(int threadCount) {
		ExecutorService executor = Executors.newFixedThreadPool(threadCount);
		for (int i = 0; i < threadCount; i++) {
			executor.submit(() -> {
				try {
					for (HttpUrl url; (url = queue.take()) != null;) {
						try {
							if (fetchedUrls.add(url)) {
								fetch(url);
							}
						} catch (IOException e) {
							logger.info("Error: {} {}", url, e);
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
	}

	private void fetch(HttpUrl url) throws IOException {
		AtomicInteger hostnameCount = new AtomicInteger();
		AtomicInteger previous = hostnames.putIfAbsent(url.host(), hostnameCount);
		if (previous != null) {
			hostnameCount = previous;
		}
		if (hostnameCount.incrementAndGet() > 10) {
			logger.info("hostnames={}", hostnames);
			return;
		}
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		String responseSource = response.networkResponse() != null ? ("(network: " + response.networkResponse().code() + " over " + response.protocol() + ")") : "(cache)";
		int responseCode = response.code();
		// 打印log
		logger.info("ResponseCode:{},URL:{},ResponseSource:{}", responseCode, url, responseSource);

		String contentType = response.header("Content-Type");
		if (responseCode != 200 || contentType == null) {
			response.body().close();
			return;
		}
		MediaType mediaType = MediaType.parse(contentType);
		if (mediaType == null || !mediaType.subtype().equalsIgnoreCase("html")) {
			response.body().close();
			return;
		}
		// 获取页面的a[href], 加入LinkedBlockingQueue
		Document document = Jsoup.parse(response.body().string(), url.toString());
		for (Element element : document.select("a[href]")) {
//			System.out.println("+++++++++++++++++++");
			HttpUrl link = response.request().url().resolve(element.attr("href"));
			if (link != null) {
				queue.add(link);
			}
		}
	}
}
