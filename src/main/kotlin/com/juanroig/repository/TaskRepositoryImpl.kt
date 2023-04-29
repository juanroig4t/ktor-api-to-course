package com.juanroig.repository

import com.juanroig.model.ApiResponse
import com.juanroig.model.ApiTaskResponse
import com.juanroig.model.Hero
import com.juanroig.model.Task

class TaskRepositoryImpl : TaskRepository {

    override val tasks = mutableListOf(
        Task(
            id = 1,
            name = "Comprar leche",
            description = "Ir al supermercado y comprar una botella de leche",
            images = listOf("https://example.com/images/milk.jpg")
        ),
        Task(
            id = 2,
            name = "Sacar al perro",
            description = "Sacar al perro a pasear por el parque",
            images = listOf("https://example.com/images/dog.jpg")
        ),
        Task(
            id = 3,
            name = "Estudiar para el examen",
            description = "Repasar los apuntes para el examen de matemáticas",
            images = listOf("https://example.com/images/study1.jpg", "https://example.com/images/study2.jpg")
        ),
        Task(
            id = 4,
            name = "Cocinar la cena",
            description = "Preparar una cena saludable con pollo y verduras",
            images = listOf("https://example.com/images/dinner1.jpg", "https://example.com/images/dinner2.jpg")
        ),
        Task(
            id = 5,
            name = "Limpiar la casa",
            description = "Limpiar la casa a fondo, aspirar y quitar el polvo",
            images = listOf("https://example.com/images/cleaning1.jpg", "https://example.com/images/cleaning2.jpg")
        )
    )
    private var nextId = 6

    override suspend fun getAllTasks(): ApiTaskResponse<List<Task>> {
        return ApiTaskResponse(
            success = true,
            message = "ok",
            data = tasks,
            lastUpdated = System.currentTimeMillis()
        )
    }

    override fun getTaskById(id: Int): ApiTaskResponse<List<Task>> {
        val task = tasks.find { it.id == id } ?: emptyList<Task>()

        return ApiTaskResponse(
            success = true,
            message = "ok",
            data = tasks,
            lastUpdated = System.currentTimeMillis()
        )
    }

    override fun createTask(task: Task): ApiTaskResponse<Task> {
        val newTask = task.copy(id = nextId)
        tasks.add(newTask)
        nextId++
        return ApiTaskResponse(
            success = true,
            message = "ok",
            data = newTask,
            lastUpdated = System.currentTimeMillis()
        )
    }

     override fun updateTask(task: Task): ApiTaskResponse<Task?> {
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index == -1) {
            return ApiTaskResponse(
                success = false,
                message = "not found",
                data = null,
                lastUpdated = System.currentTimeMillis()
            )
        }
        tasks[index] = task
        return ApiTaskResponse(
            success = true,
            message = "ok",
            data = task,
            lastUpdated = System.currentTimeMillis()
        )
    }

    override fun deleteTaskById(id: Int): Boolean = tasks.removeIf { it.id == id }
}

