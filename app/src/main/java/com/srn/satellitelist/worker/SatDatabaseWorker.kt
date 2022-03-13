package com.srn.satellitelist.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.srn.satellitelist.data.AppDatabase
import com.srn.satellitelist.entity.Satellite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by SoheilR .
 */

class SatDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val filename = inputData.getString(KEY_FILENAME)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val satType = object : TypeToken<List<Satellite>>() {}.type
                        val satList: List<Satellite> = Gson().fromJson(jsonReader, satType)

                        val database = AppDatabase.getInstance(applicationContext)
                        database.satelliteDao().insertAll(satList)

                        Result.success()
                    }
                }
            } else {
                Log.e(TAG, "Error SatDatabase - no valid filename")
                Result.failure()
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error SatDatabase", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SatDatabaseWorker"
        const val KEY_FILENAME = "SAT_DATA_FILENAME"
    }
}