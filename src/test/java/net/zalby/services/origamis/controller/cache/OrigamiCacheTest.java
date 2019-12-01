package net.zalby.services.origamis.controller.cache;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import net.zalby.services.origamis.model.Origami;

/**
 * Test Suite class for the Cache managers.
 * 
 * @author Alberto Lazzarin
 *
 */
public class OrigamiCacheTest {

	private OrigamiCache cacheToTest;
	private Clock mockedClock;

	private static long TEST_TIME_TO_LIVE = 500L;

	/**
	 * 1 - Arrange
	 */
	@Before
	public void init() {
		mockedClock = mock(Clock.class);
		cacheToTest = OrigamiCache.createCache(mockedClock, TEST_TIME_TO_LIVE);
	}

	@Test
	public void retrieveOrigamis_cache_hit() {
		/*
		 * 1 - Arrange (Mock)
		 */
		doReturn(0L).when(mockedClock).millis();

		/*
		 * 2 - Act
		 */
		cacheToTest.storeOrigami(new ArrayList<Origami>());
		List<Origami> cachedOrigami = cacheToTest.retrieveOrigamis();

		/*
		 * 3 - Assert
		 */
		assertNotNull("The stored cache must not be null (not expired)", cachedOrigami);
	}

	@Test
	public void retrieveOrigamis_cache_expired() {
		/*
		 * 1 - Arrange (Mock)
		 */
		doReturn(0L).when(mockedClock).millis();
		cacheToTest.storeOrigami(new ArrayList<Origami>());
		doReturn(TEST_TIME_TO_LIVE + 1).when(mockedClock).millis();

		/*
		 * 2 - Act
		 */
		List<Origami> cachedOrigami = cacheToTest.retrieveOrigamis();

		/*
		 * 3 - Assert
		 */
		assertNull("The stored cache must be null (expired)", cachedOrigami);
	}
}// end class
