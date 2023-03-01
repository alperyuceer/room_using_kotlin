package com.alperyuceer.roomdenemeler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.room.Room
import com.alperyuceer.roomdenemeler.databinding.ActivityNewUserBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class NewUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewUserBinding
    private lateinit var db: KullaniciDatabase
    private lateinit var kullaniciDao: KullaniciDao
    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Room.databaseBuilder(applicationContext,KullaniciDatabase::class.java,"kullanici_database").build()
        kullaniciDao =db.kullaniciDao()

    }

    fun kaydet(view: View){

        val isim = binding.isimEditText.text.toString()
        val memleket = binding.memleketEditText.text.toString()
        val kullanici = Kullanici(isim,memleket)

        compositeDisposable.add(
            kullaniciDao.insert(kullanici)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )

        val intent = Intent(this@NewUserActivity,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)


    }
}