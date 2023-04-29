package com.juanroig.repository

import com.juanroig.model.ApiTaskResponse
import com.juanroig.model.Task

interface TaskRepository {

    val tasks: List<Task>

    suspend fun getAllTasks(): ApiTaskResponse<List<Task>>

    fun getTaskById(id: Int): ApiTaskResponse<Task?>

    fun createTask(task: Task): ApiTaskResponse<Task>

    fun updateTask(task: Task): ApiTaskResponse<Task?>

    fun deleteTaskById(id: Int): Boolean

}