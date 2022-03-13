package com.srn.satellitelist.data

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.srn.satellitelist.entity.PositionItem
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by SoheilR .
 */
@ProvidedTypeConverter
class PositionItemTypeConverter {
    var gson = Gson()

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
    fun fromString(value: String?): List<String> {
        val listType = object :
            TypeToken<java.util.ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }

}