package com.srn.satellitelist.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.srn.satellitelist.data.AppDatabaseDetail
import com.srn.satellitelist.entity.SatelliteDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by SoheilR .
 */
class SatDetailDatabaseWorker (
    context: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val filename = inputData.getString(KEY_FILENAME_DETAIL)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val satType = object : TypeToken<List<SatelliteDetail>>() {}.type
                        val satList: List<SatelliteDetail> = Gson().fromJson(jsonReader, satType)

                        val database = AppDatabaseDetail.getInstance(applicationContext)
                        database.satelliteDetailDao().insertAll(satList)

                        Result.success()
                    }
                }
            } else {
                Log.e(TAG, "Error satDetail database - no valid filename")
                Result.failure()
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error satDetail database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "DetailDatabaseWorker"
        const val KEY_FILENAME_DETAIL = "SAT_DETAIL_DATA_FILENAME"
    }
}