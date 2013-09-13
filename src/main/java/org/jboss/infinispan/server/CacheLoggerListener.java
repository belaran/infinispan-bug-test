package org.jboss.infinispan.server;

import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryRemoved;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryModifiedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryRemovedEvent;

/**
 * JBoss DataGrid Listener.
 *
 * @author <a href="mailto:duncan.doyle@redhat.com">Duncan Doyle</a>
 */
@Listener
public class CacheLoggerListener {

	public void handleEvent(CacheEntryEvent event) {
		if (event == null) {
			throw new IllegalStateException("handle invoked with a 'null' event !");
		}
		System.out.println("Event received: " + event.getType() + " # isPre=" + event.isPre());
		System.out.println("On cache: " + event.getCache());
		System.out.println("Entry key is: " + event.getKey());
	}

	@CacheEntryCreated
	public void handleCreate(CacheEntryCreatedEvent event) {
		handleEvent(event);
	}

	@CacheEntryModified
	public void handleModify(CacheEntryModifiedEvent event) {
		handleEvent(event);
	}

	@CacheEntryRemoved
	public void handleRemove(CacheEntryRemovedEvent event) {
		handleEvent(event);
	}
}