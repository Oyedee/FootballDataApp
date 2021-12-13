package com.example.footballdataapp.util.typeconverters

import androidx.room.TypeConverter
import com.example.footballdataapp.data.competitions.CurrentSeason
import org.json.JSONObject

class CurrentTypeConverter {
    @TypeConverter
    fun fromSource(currentSeason: CurrentSeason): String {
        return JSONObject().apply {
            put("currentMatchday", currentSeason.currentMatchday)
            put("endDate", currentSeason.endDate)
            put("id", currentSeason.id)
            put("startDate", currentSeason.startDate)
            put("winner", currentSeason.winner)
        }.toString()
    }

    @TypeConverter
    fun toSource(source: String): CurrentSeason {
        val json = JSONObject(source)
        return CurrentSeason(json.getInt("currentMatchday"),
            json.getString("endDate"),
            json.getInt("id"),
            json.getString("startDate"),
        json.get("winner"))
    }
}