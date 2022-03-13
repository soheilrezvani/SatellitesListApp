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
import com.srn.satellitelist.entity.MySatellitePosition
import com.srn.satellitelist.entity.SatellitePosition
import com.srn.satellitelist.utils.Constants
import com.srn.satellitelist.worker.SatPositionDatabaseWorker

/**
 * Created by SoheilR .
 */
@Database(entities = [MySatellitePosition::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabasePosition : RoomDatabase() {
    abstract fun satellitePositionDao(): SatellitePositionDao

    companion object {

        @Volatile
        private var instance: AppDatabasePosition? = null

        fun getInstance(context: Context): AppDatabasePosition {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabasePosition {
            return Room.databaseBuilder(context,
                AppDatabasePosition::class.java,
                Constants.POSITION_DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<SatPositionDatabaseWorker>()
                                .setInputData(workDataOf(SatPositionDatabaseWorker.KEY_FILENAME_POSITION to Constants.SATELLITE_POSITION_JSON_FILE_NAME))
                                .build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }
    }
}