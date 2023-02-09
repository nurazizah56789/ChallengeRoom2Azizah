package com.example.challengeroom2azizah.room

import androidx.room.*
@Dao

interface TbBukuDao {

    @Insert
    fun addTbBuku(tbBuku: TbBuku)

    @Update
    fun updateTbBuku(tbBuku: TbBuku)

    @Delete
    fun deleteTbBuku(tbBuku: TbBuku)

    @Query("SELECT * FROM tbbuku")
    fun getTbBuku():List<TbBuku>

    @Query("SELECT * FROM TbBuku Where id_buku =:tbbuku_id")
    fun tampilbuku(tbbuku_id: Int):List<TbBuku>


}