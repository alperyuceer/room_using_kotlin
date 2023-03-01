package com.alperyuceer.roomdenemeler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.room.Room
import com.alperyuceer.roomdenemeler.databinding.ActivitySecilenKullaniciBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class SecilenKullanici : AppCompatActivity() {
    private lateinit var binding: ActivitySecilenKullaniciBinding
    private lateinit var db: KullaniciDatabase
    private lateinit var kullaniciDao: KullaniciDao
    private lateinit var secilenKullanici: Kullanici
    private var compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecilenKullaniciBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        secilenKullanici = intent.getSerializableExtra("kullanici") as Kullanici
        binding.secilenKullaniciAdi.text = secilenKullanici.isim
        binding.secilenKullaniciMemleketi.text = secilenKullanici.memleket

        db = Room.databaseBuilder(applicationContext,KullaniciDatabase::class.java,"kullanici_database").build()
        kullaniciDao = db.kullaniciDao()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.delete_user_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.trash){

            compositeDisposable.add(
                kullaniciDao.delete(secilenKullanici)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponse)
            )


        }
        return super.onOptionsItemSelected(item)
    }
    private fun handleResponse(){
        val intent = Intent(this@SecilenKullanici,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)

    }

}