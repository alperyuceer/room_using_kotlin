package com.alperyuceer.roomdenemeler

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable


@Dao
interface KullaniciDao {


    @Query("SELECT * FROM kullanicilar")
    fun getAll(): Flowable<List<Kullanici>>

    @Insert
    fun insert(kullanici: Kullanici): Completable

    @Delete
    fun delete(kullanici: Kullanici): Completable
}