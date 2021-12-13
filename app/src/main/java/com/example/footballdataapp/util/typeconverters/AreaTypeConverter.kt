package com.example.footballdataapp.util.typeconverters

import androidx.room.TypeConverter
import com.example.footballdataapp.data.competitions.Area
import org.json.JSONObject

class AreaTypeConverter {
    @TypeConverter
    fun fromSource(area: Area): String {
        return JSONObject().apply {
            put("countryCode", area.countryCode)
            put("ensignUrl", area.ensignUrl)
            put("id", area.id)
            put("name", area.name)
        }.toString()
    }

    @TypeConverter
    fun toSource(source: String): Area {
        val json = JSONObject(source)
        return Area(json.getString("countryCode"),
        json.get("ensignUrl"),
        json.getInt("id"),
        json.getString("name"))
    }
}