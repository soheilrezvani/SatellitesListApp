package com.srn.satellitelist.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.srn.satellitelist.entity.Satellite
import kotlinx.coroutines.flow.Flow

/**
 * Created by SoheilR .
 */
@Dao
interface SatelliteDao {
    @Query("SELECT * FROM satellite ")
    fun getSatellite(): Flow<List<Satellite>>

    @Query("SELECT * FROM satellite WHERE id = :id ")
    fun getSatelliteById(id: Int): Flow<List<Satellite>>

    @Query("SELECT * FROM satellite WHERE  satellite_name LIKE  '%' || :name || '%'")
    fun getSatelliteByName(name: String): Flow<List<Satellite>>

    @Query("SELECT * FROM satellite WHERE active LIKE  '%' || :status || '%'")
    fun getSatelliteStatus(status: String): Flow<List<Satellite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(satList: List<Satellite>)
}
