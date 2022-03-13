package com.srn.satellitelist.di

import android.content.Context
import com.srn.satellitelist.data.AppDatabase
import com.srn.satellitelist.data.SatelliteDao
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
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideSatelliteDao(appDatabase: AppDatabase): SatelliteDao {
        return appDatabase.satelliteDao()
    }

}
