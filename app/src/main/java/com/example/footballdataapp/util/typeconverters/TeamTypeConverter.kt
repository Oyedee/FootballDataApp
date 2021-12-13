package com.example.footballdataapp.util.typeconverters

import androidx.room.TypeConverter
import com.example.footballdataapp.data.competitions.Competition
import com.example.footballdataapp.data.teams.Team
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TeamTypeConverter {
    private var gson = Gson()

    @TypeConverter
    fun storedStringToDataList(value: String?): List<Team?>? {
        if (value == null) {
            return emptyList<Team>()
        }
        val listType = object : TypeToken<List<Team?>?>() {}.type
        return gson.fromJson<List<Team?>>(value, listType)
    }

    @TypeConverter
    fun dataListToStoredString(dataList: List<Team?>?): String? {
        return gson.toJson(dataList)
    }
}