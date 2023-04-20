package com.example.todoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@Suppress("UNCHECKED_CAST")
class LivedataUtil {

    // Extension to allow unit tests on LiveData
// discussion on: https://stackoverflow.com/questions/44270688/unit-testing-room-and-livedata

    fun <T> LiveData<T>.getorAwaitValue(
        time:Long = 2,
        timeUnit : TimeUnit = TimeUnit.SECONDS,
        afterObserve:()->Unit = {}
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)

        val observer = object :Observer<T> {
            override fun onChanged(t: T?) {
                data= t
                latch.countDown()
                this@getorAwaitValue.removeObserver(this)
            }
        }
        this.observeForever(observer)

        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set
        if (!latch.await(time,timeUnit)){
            this.removeObserver(observer)
            throw TimeoutException("Livedata Value was never set")
        }

        return data as T
    }

}