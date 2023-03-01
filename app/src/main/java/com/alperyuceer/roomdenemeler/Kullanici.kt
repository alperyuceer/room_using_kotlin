package com.alperyuceer.roomdenemeler

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "kullanicilar")
class Kullanici(

    @ColumnInfo("isim")
    val isim: String,

    @ColumnInfo("memleket")
    val memleket: String
):java.io.Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}