package com.gzz;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;
import com.opensymphony.oscache.web.filter.ExpiresRefreshPolicy;

/**
 * 本地缓存
 */
public class OsCache extends GeneralCacheAdministrator {

	private static Logger log = LoggerFactory.getLogger(OsCache.class);

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = -3814537980571610987L;

	// 缓存命中次数
	private AtomicLong hitCount = new AtomicLong(0L);

	// 总请求次数
	private AtomicLong reqCount = new AtomicLong(0L);

	// 命中率
	private double hitRate = 0D;

	private static final OsCache cache = new OsCache();

	/**
	 * 单例
	 */
	public static OsCache getInstance() {
		return cache;
	}

	/**
	 * 从OSCACHE中获取原始缓存数据
	 * 
	 * @param key         缓存KEY值
	 * @param refreshTime 失效时间，-1时为永久缓存
	 * @return 原始缓存数据
	 */
	public Object get(String key, int refreshTime) {
		reqCount.incrementAndGet();
		Object obj = null;
		try {
			obj = this.getFromCache(key, refreshTime);
			hitCount.incrementAndGet();
		} catch (NeedsRefreshException e) {
			this.cancelUpdate(key);
			log.error(e.getMessage());
		}
		return obj;
	}

	/**
	 * 从OSCACHE中获取原始缓存数据
	 * 
	 * @param key         缓存KEY值
	 * @param value       Object 内容对象
	 * @param refreshTime 失效时间，-1时为永久缓存
	 * @return 原始缓存数据
	 */
	public void put(String key, Object value, int refreshTime) {
		if (refreshTime == -1) {
			this.putInCache(key, value);
		} else {
			this.putInCache(key, value, new ExpiresRefreshPolicy(refreshTime));
		}
	}

	/**
	 * 从OSCACHE中删除缓存数据
	 * 
	 * @param key 缓存KEY值
	 */
	public void remove(String key) {
		this.removeEntry(key);
	}

	/**
	 * 从OSCACHE中删除所有缓存数据
	 * 
	 * @param date 预定删除时间
	 */
	public void removeAll(Date date) {
		if (date == null) {
			this.flushAll();
		} else {
			this.flushAll(date);
		}
	}

	/**
	 * 获取命中率
	 */
	public double getHitRate() {
		if (reqCount.longValue() == 0L) {
			return 0;
		}

		hitRate = (double) hitCount.longValue() / reqCount.longValue();
		BigDecimal bd = new BigDecimal(hitRate);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);

		return bd.doubleValue();
	}
}