package com.example.challengeroom2azizah.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TbBuku (
  @PrimaryKey(autoGenerate = true)
  val id_buku:Int,
  val kategori:String,
  val  judul:String,
  val pengarang:String,
  val penerbit:String,
  val jml_buku:String


 )

