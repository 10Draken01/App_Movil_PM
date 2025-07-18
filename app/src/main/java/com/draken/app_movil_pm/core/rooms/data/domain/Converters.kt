package com.draken.app_movil_pm.core.rooms.data.domain

import androidx.room.TypeConverter
import com.draken.app_movil_pm.core.domain.model.CharacterIcon
import com.draken.app_movil_pm.core.domain.model.QueryType
import com.google.gson.Gson
import java.util.Date

class Converters {

    // TypeConverters para Date
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    // TypeConverters para CharacterIcon
    @TypeConverter
    fun fromCharacterIcon(characterIcon: CharacterIcon): String {
        return Gson().toJson(characterIcon)
    }

    @TypeConverter
    fun toCharacterIcon(characterIconString: String): CharacterIcon {
        return Gson().fromJson(characterIconString, CharacterIcon::class.java)
    }

    // TypeConverters para QueryType
    @TypeConverter
    fun fromQueryType(queryType: QueryType): String {
        return queryType.name
    }

    @TypeConverter
    fun toQueryType(queryTypeString: String): QueryType {
        return QueryType.valueOf(queryTypeString)
    }
}