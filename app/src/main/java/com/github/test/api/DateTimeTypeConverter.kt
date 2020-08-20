package com.github.test.api

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import java.lang.reflect.Type

class DateTimeTypeConverter : JsonSerializer<DateTime>, JsonDeserializer<DateTime> {

    override fun serialize(src: DateTime, srcType: Type, context: JsonSerializationContext
    ): JsonElement = JsonPrimitive(src.toString())

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, type: Type,
        context: JsonDeserializationContext
    ): DateTime = DateTime(json.asString, DateTimeZone.UTC)

}
