package com.example.gcashinterviewapp

import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


fun<T> LiveData<T>.getOrAwaitValue(): T? {
    val data = arrayOfNulls<Any>(1)
    val latch = CountDownLatch(1)
    val observer: Observer<T> = object : Observer<T> {
        override fun onChanged(@Nullable o: T) {
            data[0] = o
            this@getOrAwaitValue.removeObserver(this)
            latch.countDown()
        }
    }
    this@getOrAwaitValue.observeForever(observer)
    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(2, TimeUnit.SECONDS)) {
        throw RuntimeException("LiveData value was never set.")
    }
    return data[0] as T?
}
