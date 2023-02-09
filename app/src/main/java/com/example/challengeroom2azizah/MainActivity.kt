package com.example.challengeroom2azizah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengeroom2azizah.room.Constant
import com.example.challengeroom2azizah.room.TbBuku
import com.example.challengeroom2azizah.room.dbperpustakaan
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    val db by lazy { dbperpustakaan(this) }
    lateinit var bukuAdapter: BukuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        halEdit()
        halEdit2()
        onStart2()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    fun onStart2(){
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val peminjam = db.tbPinjamDao().getTbPinjam()
            Log.d("MainActivity","dbResponse: $peminjam")
        }
    }

    fun loadData(){
        CoroutineScope(Dispatchers.IO).launch {
            val buku = db.tbBukuDao().getTbBuku()
            Log.d("MainActivity","dbResponse: $buku")
            withContext(Dispatchers.Main){
                bukuAdapter.setData(buku)

            }
        }
    }

    private fun halEdit(){
        btnInput.setOnClickListener{
            intentEdit(0,Constant.TYPE_CREATE)
            startActivity(Intent(this,EditActivity::class.java))
        }
    }

    private fun halEdit2(){
        btnInput2.setOnClickListener{
            startActivity(Intent(this,EditActivity::class.java))
        }
    }

    fun intentEdit(tbBuku:Int, intentType:Int){
        startActivity(
            Intent(applicationContext, EditActivity::class.java)
                .putExtra("intent_Id",tbBuku)
                .putExtra("intent_type",intentType)
        )
    }

    fun setupRecyclerView(){
        bukuAdapter = BukuAdapter(arrayListOf(), object : BukuAdapter.OnAdapterListener {
            override fun onClick(tbBuku: TbBuku) {
                intentEdit(tbBuku.id_buku,Constant.TYPE_READ)
            }

            override fun onUpdate(tbBuku: TbBuku) {
                intentEdit(tbBuku.id_buku,Constant.TYPE_UPDATE)

            }

            override fun onDelete(tbBuku:TbBuku) {
                hapusbuku(tbBuku)

            }
        })

        //id recyclerview
        list_dataperpus.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = bukuAdapter
        }
    }

    private fun hapusbuku (tbBuku: TbBuku){
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi Hapus")
            setMessage("Yakin hapus ${tbBuku.judul}?")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i ->
                CoroutineScope(Dispatchers.IO).launch {
                    db.tbBukuDao().deleteTbBuku(tbBuku)
                    dialogInterface.dismiss()
                    loadData()
                }
            }
        }

        dialog.show()
    }

}