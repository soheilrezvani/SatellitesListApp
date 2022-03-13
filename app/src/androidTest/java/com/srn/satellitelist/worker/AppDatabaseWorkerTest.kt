package com.srn.satellitelist.worker

import android.content.Context
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkManager
import androidx.work.impl.utils.SynchronousExecutor
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.testing.WorkManagerTestInitHelper
import androidx.work.workDataOf
import com.srn.satellitelist.utils.Constants.SATELLITE_LIST_JSON_FILE_NAME
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by SoheilR .
 */

@RunWith(JUnit4::class)
class RefreshMainDataWorkTest {
    private lateinit var workManager: WorkManager
    private lateinit var context: Context
    private lateinit var configuration: Configuration

    @Before
    fun setup() {
        // Configure WorkManager
        configuration = Configuration.Builder()
            // Set log level to Log.DEBUG to make it easier to debug
            .setMinimumLoggingLevel(Log.DEBUG)
            // Use a SynchronousExecutor here to make it easier to write tests
            .setExecutor(SynchronousExecutor())
            .build()

        // Initialize WorkManager for instrumentation tests.
        context = InstrumentationRegistry.getInstrumentation().targetContext
        WorkManagerTestInitHelper.initializeTestWorkManager(context, configuration)
        workManager = WorkManager.getInstance(context)
    }

    @Test
    fun testRefreshMainDataWork() {
        // Get the ListenableWorker
        val worker = TestListenableWorkerBuilder<SatDatabaseWorker>(
            context = context,
            inputData = workDataOf(SatDatabaseWorker.KEY_FILENAME to SATELLITE_LIST_JSON_FILE_NAME)
        ).build()

        // Start the work synchronously
        val result = worker.startWork().get()

        Assert.assertThat(result, CoreMatchers.`is`(ListenableWorker.Result.success()))
    }
}
