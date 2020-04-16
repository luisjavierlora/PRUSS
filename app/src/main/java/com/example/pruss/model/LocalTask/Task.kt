package com.example.pruss.model.LocalTask

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_task")
class Task (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name ="titulo") val titulo: String,
    @ColumnInfo(name ="fecha_entrega") val fecha_en:String,
    @ColumnInfo(name ="hora_entrega") val hora_en:String,
    @ColumnInfo(name ="estado") val est:Int
    )
