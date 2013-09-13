package org.jboss.infinispan.client;

import org.infinispan.Cache;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;

/**
 * @author GDA
 */
public class ClientLauncher {

	public static void main(String[] args) {
		RemoteCacheManager cacheManager = new RemoteCacheManager("localhost:11222");
		RemoteCache<String, String> cache = cacheManager.getCache("perfcache");

		cache.get("MissingKey");
		cache.put("MyKey", "MyValue");

		cacheManager.stop();
	}

}
