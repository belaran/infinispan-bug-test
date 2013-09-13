package org.jboss.infinispan.server;

import java.io.IOException;
import java.io.InputStream;

import org.infinispan.AdvancedCache;
import org.infinispan.Cache;
import org.infinispan.manager.CacheContainer;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.server.core.transport.Transport;
import org.infinispan.server.hotrod.HotRodServer;

public class ServerLauncher {
	static Transport transport;
	public static void main(String[] args) {
		CacheContainer cacheContainer = createCacheContainer();
		Cache cache = createCache(cacheContainer, "perfcache");

		HotRodServer hotRodServer = new HotRodServer();
		hotRodServer.start("server.properties",(EmbeddedCacheManager) cacheContainer);

//		cache.get("MissingKey");
//		cache.put("MyKey", "MyValue");

//		cache.stop();
//		cacheContainer.stop();
	}

	public static CacheContainer createCacheContainer() {
		try (InputStream configInputStream = loadFromClasspath("infinispan.xml")) {
			return new DefaultCacheManager(configInputStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static Cache createCache(CacheContainer cacheContainer, String cacheName) {
		AdvancedCache cache = cacheContainer.getCache(cacheName).getAdvancedCache();
		cache.addListener(new CacheLoggerListener());
		return cache;
	}

	public static InputStream loadFromClasspath(String filename) {
		return ServerLauncher.class.getClassLoader().getResourceAsStream(filename);
	}

}
