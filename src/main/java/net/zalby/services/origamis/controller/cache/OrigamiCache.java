package net.zalby.services.origamis.controller.cache;

import java.time.Clock;
import java.util.List;

import net.zalby.services.origamis.model.Origami;

public interface OrigamiCache {

	/**
	 * returns the list of Origami which have been saved with the store method. If
	 * the store method has never been called or if the stored Origamis have become
	 * stale (out of date), then null will be returned
	 * 
	 * @return the list of Origami stored in cache if not out of date, null otherwise
	 */
	List<Origami> retrieveOrigamis();

	/**
	 * saves the list or Origami in the cache object
	 * 
	 * @param origamiToStore
	 */
	void storeOrigami(List<Origami> origamiToStore);
	
	/**
	 * @param clock the time manager chosen for the cache
	 * @param timeToLive the time in milliseconds after that the stored entries will be discarded
	 * @return a new instance of a OrigamiCache object
	 */
	public static OrigamiCache createCache(Clock clock, long timeToLiveMs) {
		return new OrigamiCacheSimpleImpl(clock, timeToLiveMs);
	}
}
