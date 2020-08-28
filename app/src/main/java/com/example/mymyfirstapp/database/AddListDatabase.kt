package com.example.mymyfirstapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AddList::class], version = 1, exportSchema = false)
abstract class AddListDatabase : RoomDatabase(){
    abstract val addListDao: AddListDao
    companion object{
        @Volatile
        private var INSTANCE: com.example.mymyfirstapp.database.AddListDatabase? = null
        fun getInstance(context: Context): com.example.mymyfirstapp.database.AddListDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        com.example.mymyfirstapp.database.AddListDatabase::class.java,
                        "database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}