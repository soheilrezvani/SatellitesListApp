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
import com.srn.satellitelist.entity.Satellite
import com.srn.satellitelist.utils.Constants.DATABASE_NAME
import com.srn.satellitelist.utils.Constants.SATELLITE_LIST_JSON_FILE_NAME
import com.srn.satellitelist.worker.SatDatabaseWorker
import com.srn.satellitelist.worker.SatDatabaseWorker.Companion.KEY_FILENAME

/**
 * Created by SoheilR .
 */
@Database(entities = [Satellite::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun satelliteDao(): SatelliteDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<SatDatabaseWorker>()
                                .setInputData(workDataOf(KEY_FILENAME to SATELLITE_LIST_JSON_FILE_NAME))
                                .build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }
    }
}