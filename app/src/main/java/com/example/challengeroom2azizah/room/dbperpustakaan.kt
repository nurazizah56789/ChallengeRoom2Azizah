package com.example.challengeroom2azizah.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [TbBuku::class, TbPeminjam::class], version = 1)
abstract class dbperpustakaan : RoomDatabase(){
    abstract fun tbBukuDao():TbBukuDao
    abstract fun tbPinjamDao():TbPeminjamDao

    companion object{

        @Volatile private var instance : dbperpustakaan? = null
        private val Lock = Any()
        operator fun invoke(context: Context) = instance?: synchronized(Lock){
            instance?: buildDatabase(context).also{
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder (
            context.applicationContext,
            dbperpustakaan::class.java,
            "perpustakaan.db"
        ).build()

    }
}
