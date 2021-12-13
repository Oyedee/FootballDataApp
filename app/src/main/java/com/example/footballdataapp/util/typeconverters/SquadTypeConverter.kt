package com.example.footballdataapp.util.typeconverters

import androidx.room.TypeConverter
import com.example.footballdataapp.data.teamdetails.Squad
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class SquadTypeConverter {
    private var gson = Gson()
    @TypeConverter
    fun storedStringToDataList(value: String?): List<Squad?>? {
        if (value == null) {
            return emptyList<Squad>()
        }
        val listType = object : TypeToken<List<Squad?>?>() {}.type
        return gson.fromJson<List<Squad?>>(value, listType)
    }

    @TypeConverter
    fun dataListToStoredString(dataList: List<Squad?>?): String? {
        return gson.toJson(dataList)
    }
}