package com.srn.satellitelist.di

import android.content.Context
import com.srn.satellitelist.data.AppDatabaseDetail
import com.srn.satellitelist.data.AppDatabasePosition
import com.srn.satellitelist.data.SatelliteDetailDao
import com.srn.satellitelist.data.SatellitePositionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by SoheilR .
 */
@InstallIn(SingletonComponent::class)
@Module
class PositionDatabaseModule {

    @Singleton
    @Provides
    fun provideAppPositionDatabase(@ApplicationContext context: Context): AppDatabasePosition {
        return AppDatabasePosition.getInstance(context)
    }

    @Provides
    fun provideSatDetailDao(appDatabasePosition: AppDatabasePosition): SatellitePositionDao {
        return appDatabasePosition.satellitePositionDao()
    }
}