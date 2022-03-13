package com.srn.satellitelist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.srn.satellitelist.entity.SatelliteDetail
import com.srn.satellitelist.utils.Constants
import com.srn.satellitelist.worker.SatDetailDatabaseWorker

/**
 * Created by SoheilR .
 */

@Database(entities = [SatelliteDetail::class], version = 1, exportSchema = false)
abstract class AppDatabaseDetail : RoomDatabase() {
    abstract fun satelliteDetailDao(): SatelliteDetailDao

    companion object {

        @Volatile
        private var instance: AppDatabaseDetail? = null

        fun getInstance(context: Context): AppDatabaseDetail {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabaseDetail {
            return Room.databaseBuilder(context,
                AppDatabaseDetail::class.java,
                Constants.DETAIL_DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<SatDetailDatabaseWorker>()
                                .setInputData(workDataOf(SatDetailDatabaseWorker.KEY_FILENAME_DETAIL to Constants.SATELLITE_DETAIL_JSON_FILE_NAME))
                                .build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }
    }
}