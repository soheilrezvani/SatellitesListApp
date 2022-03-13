package com.srn.satellitelist.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by SoheilR .
 */

@Entity(tableName = "satellite")
@Parcelize
data class Satellite(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "satellite_name") val name: String,
    val active: Boolean,

    ) : Parcelable {
    // for use active caption in DataBinding
    @Ignore
    public var activeCaption: String = "Active"

    @Ignore
    var passiveCaption: String = "Passive"

    override fun toString() = name
}