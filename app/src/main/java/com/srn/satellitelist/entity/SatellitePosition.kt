package com.srn.satellitelist.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.srn.satellitelist.data.Converters

/**
 * Created by SoheilR .
 */


@Entity(tableName = "satellite_position")
data class SatellitePosition(
    @PrimaryKey()
    @TypeConverters(Converters::class)
    val list: List<Positions>,

    ) {}
data class Positions(
    @ColumnInfo(name = "id") val id: String,
    @TypeConverters(Converters::class)
    val positions: List<PositionItem>,
) {}
data class PositionItem(
    val posX: Float,
    val posY: Float,
) {}

