package com.example.booksage.roomDB

import android.net.Uri
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PdfDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUri(pdf: Pdf)

    @Query("Select * From Pdf")
    fun getPdfs(): Flow<List<Pdf>>
}