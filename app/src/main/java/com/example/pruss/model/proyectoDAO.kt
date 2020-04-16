package com.example.pruss.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface proyectoDAO {

    @Insert
    fun insertProyecto(proyectoRoom: ProyectoRoom)

    @Query("SELECT * FROM tabla_proyecto WHERE id LIKE :id")
    fun searchProyecto(id: String): ProyectoRoom


    @Query("SELECT * FROM tabla_proyecto")
    fun getProyectos() : List<ProyectoRoom>

    @Query("DELETE FROM tabla_proyecto")
    fun borrarProyectos()

}