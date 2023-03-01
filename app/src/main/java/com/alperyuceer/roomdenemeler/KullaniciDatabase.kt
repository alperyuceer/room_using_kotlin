package com.alperyuceer.roomdenemeler

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Kullanici::class], version = 1)
abstract class KullaniciDatabase: RoomDatabase() {
    abstract fun kullaniciDao(): KullaniciDao
}