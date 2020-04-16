package com.example.pruss.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

class proyecto(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val id: String = "",
    @ColumnInfo(name ="nombre") val nombre: String = "",
    @ColumnInfo(name ="color_p") val color_p: String = "#FFFFFF",
    @ColumnInfo(name ="color_s") val color_s: String="#216621"
    )