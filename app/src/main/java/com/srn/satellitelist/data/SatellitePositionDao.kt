package com.srn.satellitelist.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.srn.satellitelist.entity.MySatellitePosition
import com.srn.satellitelist.entity.SatellitePosition
import kotlinx.coroutines.flow.Flow

/**
 * Created by SoheilR .
 */
@Dao
interface SatellitePositionDao {
    @Query("SELECT * FROM lite_satellite_position ")
    fun getSatellitePosition(): Flow<MySatellitePosition>

    @Query("SELECT * FROM lite_satellite_position ")
    fun getSatellitePositionById(): Flow<MySatellitePosition>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(satList: List<MySatellitePosition>)
}