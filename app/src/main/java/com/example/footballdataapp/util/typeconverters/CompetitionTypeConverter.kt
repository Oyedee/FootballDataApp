package com.example.footballdataapp.util.typeconverters

import androidx.room.TypeConverter
import com.example.footballdataapp.data.competitions.Competition
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CompetitionTypeConverter {
    private var gson = Gson()

    @TypeConverter
    fun storedStringToDataList(value: String?): List<Competition?>? {
        if (value == null) {
            return emptyList<Competition>()
        }
        val listType = object : TypeToken<List<Competition?>?>() {}.type
        return gson.fromJson<List<Competition?>>(value, listType)
    }

    @TypeConverter
    fun dataListToStoredString(dataList: List<Competition?>?): String? {
        return gson.toJson(dataList)
    }
}