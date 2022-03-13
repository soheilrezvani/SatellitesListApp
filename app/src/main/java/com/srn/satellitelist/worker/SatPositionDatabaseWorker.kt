package com.srn.satellitelist.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.srn.satellitelist.data.AppDatabasePosition
import com.srn.satellitelist.entity.MySatellitePosition
import com.srn.satellitelist.entity.SatellitePosition
import com.srn.satellitelist.utils.extension.TAG
import kotlinx.coroutines.*

/**
 * Created by SoheilR .
 */

class SatPositionDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val filename = inputData.getString(KEY_FILENAME_POSITION)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val satType = object : TypeToken<List<MySatellitePosition>>() {}.type
                        val satList: List<MySatellitePosition> = Gson().fromJson(jsonReader, satType)

                        val database = AppDatabasePosition.getInstance(applicationContext)
                        database.satellitePositionDao().insertAll(satList)

                        Result.success()
                    }
                }
            } else {
                Log.e(TAG, "Error satPosition database - no valid filename")
                Result.failure()
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error satPosition database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "DetailDatabaseWorker"
        const val KEY_FILENAME_POSITION = "SAT_POSITION_DATA_FILENAME"
    }
}