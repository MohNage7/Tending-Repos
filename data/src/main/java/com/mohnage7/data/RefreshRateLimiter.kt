package com.mohnage7.data

import android.os.SystemClock
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * this class adds the loaded data key to the map and check later
 * to determine if we need to refresh data or not.
 * key in @shouldFetch method is the key of the data that needs to be loaded.
 */
class RefreshRateLimiter @Inject constructor() : RefreshLimiter {

    private val timeUnit = TimeUnit.MINUTES
    private val timeout: Long = timeUnit.toMillis(1)
    private val timestamps: HashMap<String, Long> = HashMap()

    @Synchronized
    override fun shouldFetch(key: String): Boolean {
        val lastFetched = timestamps[key]
        val now = SystemClock.uptimeMillis()
        // if there's no data added before. add it and return true
        if (lastFetched == null) {
            timestamps[key] = now
            return true
        }
        if (now - lastFetched > timeout) {
            timestamps[key] = now
            return true
        }
        return false
    }

    @Synchronized
    override fun remove(key: String) {
        timestamps.remove(key)
    }
}

interface RefreshLimiter {
    fun shouldFetch(key: String): Boolean
    fun remove(key: String)
}
