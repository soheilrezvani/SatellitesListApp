package com.srn.satellitelist.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.srn.satellitelist.entity.SatelliteDetail
import kotlinx.coroutines.flow.Flow

/**
 * Created by SoheilR .
 */
@Dao
interface SatelliteDetailDao {
    @Query("SELECT * FROM satellite_detail ")
    fun getSatelliteDetail(): Flow<List<SatelliteDetail>>

    @Query("SELECT * FROM satellite_detail WHERE id = :satId")
    fun getSatelliteDetailById(satId: Int): Flow<SatelliteDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(satList: List<SatelliteDetail>)
}