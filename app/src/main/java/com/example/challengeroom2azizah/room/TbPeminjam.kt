package com.example.challengeroom2azizah.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TbPeminjam (
    @PrimaryKey(autoGenerate = true)
    val id_pinjam:Int,
    val nama_peminjam:String,
    val tanggal_pinjam:String,
    val tanggal_kembali:String,
    val  judul_buku:String,
    val jml_pinjambuku:String
)
