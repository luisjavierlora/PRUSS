package com.example.pruss

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.pruss.model.LocalTask.Task
import com.example.pruss.model.LocalTask.TaskDataBase
import com.example.pruss.model.ProyectoDataBase

class SesionRoom : Application() {

    companion object {
        lateinit var databaseR: ProyectoDataBase
        lateinit var databaseTask: TaskDataBase

}


    override fun onCreate() {
        super.onCreate()
        databaseR = Room.databaseBuilder(
            this,
            ProyectoDataBase::class.java,"proyecto_DB"
        ).allowMainThreadQueries()
            .build()


        databaseTask = Room.databaseBuilder(
            this,
            TaskDataBase::class.java,"task_DB"
        ).allowMainThreadQueries()
            .build()
    }


}
