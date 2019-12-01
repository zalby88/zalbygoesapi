package net.zalby.services.origamis.controller.cache;

import java.time.Clock;
import java.util.List;

import net.zalby.services.origamis.model.Origami;

/**
 * A very basic cache implementation
 * 
 * @author Alberto Lazzarin
 *
 */
public class OrigamiCacheSimpleImpl implements OrigamiCache {
	
	private final Clock _clock;
	private final long _timeToLiveMs;
	
	private long _lastTimeMillis;
	private List<Origami> storedOrigami;
	
	OrigamiCacheSimpleImpl(Clock clock, long timeToLiveMs) {
		this._clock = clock;
		this._timeToLiveMs = timeToLiveMs;
		this._lastTimeMillis = 0L;
	}

	@Override
	public List<Origami> retrieveOrigamis() {
		if (_clock.millis() - _lastTimeMillis > _timeToLiveMs) {
			storedOrigami = null;
		}
		
		return storedOrigami;
	}

	@Override
	public void storeOrigami(List<Origami> origamiToStore) {
		_lastTimeMillis = _clock.millis();
		
		this.storedOrigami = origamiToStore;
	}

}
