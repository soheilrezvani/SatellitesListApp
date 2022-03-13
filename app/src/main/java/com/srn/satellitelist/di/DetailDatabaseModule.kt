package com.srn.satellitelist.di

import android.content.Context
import com.srn.satellitelist.data.AppDatabaseDetail
import com.srn.satellitelist.data.SatelliteDetailDao
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
class DetailDatabaseModule {

    @Singleton
    @Provides
    fun provideAppDetailDatabase(@ApplicationContext context: Context): AppDatabaseDetail {
        return AppDatabaseDetail.getInstance(context)
    }

    @Provides
    fun provideSatDetailDao(appDatabaseDetail: AppDatabaseDetail): SatelliteDetailDao {
        return appDatabaseDetail.satelliteDetailDao()
    }
}