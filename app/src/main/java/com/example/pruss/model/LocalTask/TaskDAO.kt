package com.example.pruss.model.LocalTask

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDAO {

    @Insert
    fun insertTask(task: Task)

    @Query("SELECT * FROM tabla_task")
    fun getTasks() : List<Task>
}