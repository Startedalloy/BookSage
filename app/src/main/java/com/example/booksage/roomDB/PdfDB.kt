package com.example.booksage.roomDB

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Pdf::class], version = 1
)
@TypeConverters(Converters::class)
abstract class PdfDB : RoomDatabase() {
    abstract fun dao(): PdfDao
}

class BookApp : Application() {

    val database: PdfDB by lazy {
        Room.databaseBuilder(applicationContext, PdfDB::class.java, "pdf_database").build()

    }
}