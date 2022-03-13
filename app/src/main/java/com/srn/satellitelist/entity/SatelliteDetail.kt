package com.srn.satellitelist.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by SoheilR .
 */

@Entity(tableName = "satellite_detail")
data class SatelliteDetail(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    val cost_per_launch: Int,
    val first_flight: String,
    val height: Int,
    val mass: Int,
) {
    override fun toString() = first_flight
}
