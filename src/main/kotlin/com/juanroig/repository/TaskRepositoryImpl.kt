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
            completed = false,
            images = listOf("https://www.bing.com/images/search?view=detailV2&ccid=WnCPaZLe&id=799B17D10303544689BCAB6D901E5835A154A061&thid=OIP.WnCPaZLe4JJoN92_NM_c-AEsDb&mediaurl=https%3a%2f%2fimages.medicaldaily.com%2fsites%2fmedicaldaily.com%2ffiles%2fstyles%2fheadline%2fpublic%2f2015%2f03%2f26%2fmilk.jpg&cdnurl=https%3a%2f%2fth.bing.com%2fth%2fid%2fR.5a708f6992dee0926837ddbf34cfdcf8%3frik%3dYaBUoTVYHpBtqw%26pid%3dImgRaw%26r%3d0&exph=615&expw=840&q=milk&simid=607996395544252942&FORM=IRPRST&ck=0E98D84C5E57DECA665F919B81356F45&selectedIndex=1")
        ),
        Task(
            id = 2,
            name = "Sacar al perro",
            description = "Sacar al perro a pasear por el parque",
            completed = false,
            images = listOf("https://example.com/images/dog.jpg")
        ),
        Task(
            id = 3,
            name = "Estudiar para el examen",
            description = "Repasar los apuntes para el examen de matemáticas",
            completed = true,
            images = listOf("https://example.com/images/study1.jpg", "https://example.com/images/study2.jpg")
        ),
        Task(
            id = 4,
            name = "Cocinar la cena",
            description = "Preparar una cena saludable con pollo y verduras",
            completed = false,
            images = listOf("https://example.com/images/dinner1.jpg", "https://example.com/images/dinner2.jpg")
        ),
        Task(
            id = 5,
            name = "Limpiar la casa",
            description = "Limpiar la casa a fondo, aspirar y quitar el polvo",
            completed = true,
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

    override fun getTaskById(id: Int): ApiTaskResponse<Task?> {
        val task = tasks.find { it.id == id }

        return ApiTaskResponse(
            success = true,
            message = "ok",
            data = task,
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

