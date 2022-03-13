package com.srn.satellitelist.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.srn.satellitelist.data.Converters

/**
 * Created by SoheilR .
 */
@Entity(tableName = "lite_satellite_position")
data class MySatellitePosition(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @TypeConverters(Converters::class)
    val posList: List<PositionItem>,
) {
    override fun toString() = id
}
