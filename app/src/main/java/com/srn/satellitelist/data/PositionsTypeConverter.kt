package com.srn.satellitelist.data

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.srn.satellitelist.entity.PositionItem
import com.srn.satellitelist.entity.Positions
import com.srn.satellitelist.entity.SatellitePosition
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by SoheilR .
 */
@ProvidedTypeConverter
class PositionsTypeConverter {
    var gson = Gson()

    @TypeConverter
    fun fromArrayListOfPositions(value: String?): ArrayList<Positions?>? {
        val listType = object : TypeToken<ArrayList<Positions?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toArrayListOfPositions(list: ArrayList<Positions?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
    @TypeConverter
    fun fromArrayListOfPositionItems(value: String?): ArrayList<PositionItem?>? {
        val listType = object : TypeToken<ArrayList<PositionItem?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toArrayListOfPositionItems(list: ArrayList<PositionItem?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromArrayListOfFloats(list: ArrayList<Float>?): String {
        return list?.joinToString(separator = ";") { it.toString() } ?: ""
    }

    @TypeConverter
    fun toArrayListOfFloats(string: String?): ArrayList<Float> {
        return ArrayList(string?.split(";")?.mapNotNull { it.toFloatOrNull() } ?: emptyList())
    }
    @TypeConverter
    fun fromString(stringListString: String): List<String> {
        return stringListString.split(",").map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = ",")
    }

}