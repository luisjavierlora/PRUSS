package com.example.pruss.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProyectoRoom::class], version = 1)
abstract class ProyectoDataBase : RoomDatabase() {

    abstract fun proyectoDAO(): proyectoDAO

}