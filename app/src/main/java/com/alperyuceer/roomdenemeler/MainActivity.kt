package com.alperyuceer.roomdenemeler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.alperyuceer.roomdenemeler.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = Room.databaseBuilder(applicationContext,KullaniciDatabase::class.java,"kullanici_database").build()
        val kullaniciDao = db.kullaniciDao()

        compositeDisposable.add(
            kullaniciDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        )

    }
    private fun handleResponse(kullaniciListesi: List<Kullanici>){
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = KullaniciAdapter(kullaniciListesi)
        binding.recyclerView.adapter= adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_user_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_new_user){
            val intent = Intent(this,NewUserActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}