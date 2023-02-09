package com.example.challengeroom2azizah

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challengeroom2azizah.room.TbBuku
import kotlinx.android.synthetic.main.activity_buku_adapter.view.*

class BukuAdapter (private val buku: ArrayList<TbBuku>,private val listener: OnAdapterListener)
    : RecyclerView.Adapter<BukuAdapter.BukuViewHolder>() {

    class BukuViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuViewHolder {
        return BukuViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_buku_adapter,parent,false)
        )
    }

    override fun onBindViewHolder(holder: BukuViewHolder, position: Int) {
        val Tbbuku=buku[position]
        holder.view.T_nama.text = Tbbuku.kategori
        holder.view.T_title.text = Tbbuku.judul
        holder.view.T_title.setOnClickListener {
            listener.onClick(Tbbuku)
        }

        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(Tbbuku)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(Tbbuku)
        }

    }

    override fun getItemCount()= buku.size

    fun setData(list: List<TbBuku>){
        buku.clear()
        buku.addAll(list)
        notifyDataSetChanged()
    }
    interface OnAdapterListener{
        fun onClick(Tbbuku: TbBuku)
        fun onUpdate(Tbbuku: TbBuku)
        fun onDelete(Tbbuku: TbBuku)
    }

}






