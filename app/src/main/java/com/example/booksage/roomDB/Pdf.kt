package com.example.booksage.roomDB

import android.net.Uri
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(indices = [Index(value = ["uri"], unique = true)])
data class Pdf(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val uri: Uri


)

class Converters {
    @TypeConverter
    fun fromUri(uri: Uri): String = uri.toString()

    @TypeConverter
    fun toUri(uriString: String): Uri = Uri.parse(uriString)
}