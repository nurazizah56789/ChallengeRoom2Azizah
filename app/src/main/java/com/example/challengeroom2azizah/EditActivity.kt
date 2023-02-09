package com.example.challengeroom2azizah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.challengeroom2azizah.room.Constant
import com.example.challengeroom2azizah.room.TbBuku
import com.example.challengeroom2azizah.room.dbperpustakaan
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    val db by lazy { dbperpustakaan(this) }
    private var tbBukuID:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        tombolbuku()
        setupView()
        tbBukuID = intent.getIntExtra("intent_id",tbBukuID)
        Toast.makeText(this,tbBukuID.toString(),Toast.LENGTH_SHORT).show()
    }

    fun setupView() {
        val intentType = intent.getIntExtra("intent_type",0)
        when (intentType){
            Constant.TYPE_CREATE ->{
                btnUpdate.visibility = View.GONE

            }

            Constant.TYPE_READ ->{
                btnsave.visibility = View.GONE
                btnUpdate.visibility =View.GONE
                ETid.visibility = View.GONE
                tampilbuku()
            }

            Constant.TYPE_UPDATE ->{
                btnsave.visibility = View.GONE
                ETid.visibility = View.GONE
                tampilbuku()
            }
        }
    }


    fun tombolbuku(){
        btnsave.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                 db.tbBukuDao().addTbBuku(
                    TbBuku(
                        ETid.text.toString().toInt(),
                        ETkategori.text.toString(),
                        ETjudul.text.toString(),
                        ETpengarang.text.toString(),
                        ETpenerbit.text.toString(),
                        ETjmlh_buku.text.toString()

                    )
                )

                finish()
            }
        }

        btnUpdate.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.tbBukuDao().updateTbBuku(
                    TbBuku(tbBukuID,
                        ETkategori.text.toString(),
                        ETjudul.text.toString(),
                        ETpengarang.text.toString(),
                        ETpenerbit.text.toString(),
                        ETjmlh_buku.text.toString()

                    )
                )

                finish()
            }
        }
    }

    fun tampilbuku() {
        tbBukuID= intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val buku = db.tbBukuDao().tampilbuku(tbBukuID)[0]
            val dataId : String = buku.id_buku.toString()
            ETid.setText(dataId)
            ETkategori.setText(buku.kategori)
            ETjudul.setText(buku.judul)
            ETpengarang.setText(buku.pengarang)
            ETpenerbit.setText(buku.penerbit)
            ETjmlh_buku.setText(buku.jml_buku)

        }
    }
}