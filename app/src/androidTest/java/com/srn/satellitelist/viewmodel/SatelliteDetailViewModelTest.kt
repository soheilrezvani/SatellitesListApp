package com.srn.satellitelist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.srn.satellitelist.MainCoroutineRule
import com.srn.satellitelist.data.AppDatabaseDetail
import com.srn.satellitelist.data.SatelliteDetailRepository
import com.srn.satellitelist.runBlockingTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.*
import org.junit.rules.RuleChain
import javax.inject.Inject

/**
 * Created by SoheilR .
 */

@HiltAndroidTest
class SatelliteDetailViewModelTest {

    private lateinit var appDatabase: AppDatabaseDetail
    private lateinit var viewModel: SatelliteDetailViewModel
    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val coroutineRule = MainCoroutineRule()

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)
        .around(coroutineRule)

    @Inject
    lateinit var satelliteDetailRepository: SatelliteDetailRepository

    @Inject
    lateinit var positionRepository: PositionRepository

    @Before
    fun setUp() {
        hiltRule.inject()

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabaseDetail::class.java).build()

        val savedStateHandle: SavedStateHandle = SavedStateHandle().apply {
            set("satId", "2")
        }
        viewModel = SatelliteDetailViewModel(savedStateHandle,
            satelliteDetailRepository,
            positionRepository)
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    @Test
    @Throws(InterruptedException::class)
    fun testDefaultValues() = coroutineRule.runBlockingTest {
//        Assert.assertFalse(getValue(viewModel.satId))
    }
}
