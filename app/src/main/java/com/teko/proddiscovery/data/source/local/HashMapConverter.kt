package com.teko.proddiscovery.data.source.local

import androidx.room.TypeConverter
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.net.URLEncoder
import java.util.LinkedHashMap

class HashMapConverter {
    @TypeConverter
    fun mapToString(map: LinkedHashMap<String, String>): String {
        val stringBuilder = StringBuilder()

        for (key in map.keys) {
            if (stringBuilder.length > 0) {
                stringBuilder.append("&")
            }
            val value = map[key]
            try {
                stringBuilder.append(if (key != null) URLEncoder.encode(key, "UTF-8") else "")
                stringBuilder.append("=")
                stringBuilder.append(if (value != null) URLEncoder.encode(value, "UTF-8") else "")
            } catch (e: UnsupportedEncodingException) {
                throw RuntimeException("This method requires UTF-8 encoding support", e)
            }

        }

        return stringBuilder.toString()
    }

    @TypeConverter
    fun stringToMap(input: String): LinkedHashMap<String, String> {
        val map = LinkedHashMap<String, String>()

        val nameValuePairs = input.split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (nameValuePair in nameValuePairs) {
            val nameValue = nameValuePair.split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            try {
                map[URLDecoder.decode(nameValue[0], "UTF-8")] = if (nameValue.size > 1)
                    URLDecoder.decode(
                            nameValue[1], "UTF-8")
                else
                    ""
            } catch (e: UnsupportedEncodingException) {
                throw RuntimeException("This method requires UTF-8 encoding support", e)
            }

        }

        return map
    }
}