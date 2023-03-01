package com.alperyuceer.roomdenemeler

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alperyuceer.roomdenemeler.databinding.RecyclerviewRowBinding

class KullaniciAdapter(val kullaniciListesi: List<Kullanici>): RecyclerView.Adapter<KullaniciAdapter.KullaniciHolder>() {
    class KullaniciHolder(val binding: RecyclerviewRowBinding) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KullaniciHolder {
        val binding = RecyclerviewRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return KullaniciHolder(binding)
    }

    override fun getItemCount(): Int {
        return kullaniciListesi.size
    }

    override fun onBindViewHolder(holder: KullaniciHolder, position: Int) {
        holder.binding.rvKullaniciAdi.text = kullaniciListesi.get(position).isim
        if (position%2==0){
            holder.binding.rvKullaniciAdi.setBackgroundColor(Color.parseColor("#5F8F2E"))
        }else{
            holder.binding.rvKullaniciAdi.setBackgroundColor(Color.parseColor("#80FF00"))
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, SecilenKullanici::class.java)
            intent.putExtra("kullanici", kullaniciListesi.get(position))
            holder.itemView.context.startActivity(intent)

        }
    }
}