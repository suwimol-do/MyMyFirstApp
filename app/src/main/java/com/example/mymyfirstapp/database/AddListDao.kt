package com.example.mymyfirstapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AddListDao {
    @Insert
    fun insert(contact: AddList)
    @Query("SELECT * from contact_table")
    fun get(): LiveData<List<AddList>>
}