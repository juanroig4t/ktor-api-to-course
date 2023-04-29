package com.juanroig.route

import com.juanroig.model.Task
import com.juanroig.repository.TaskRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.task() {
    val taskRepository: TaskRepository by inject()

    get("/tasks") {
        try {
            val apiResponse = taskRepository.getAllTasks()
            call.respond(apiResponse)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError)
        }
    }

    get("/tasks/{id}") {
        try {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }

            val apiResponse = taskRepository.getTaskById(id)
            call.respond(apiResponse)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError)
        }
    }

    post("/tasks") {
        try {
            val task = call.receive<Task>()
            val apiResponse = taskRepository.createTask(task)
            call.respond(apiResponse)
        } catch (e: Exception) {
            println(e.message)
            call.respond(HttpStatusCode.InternalServerError)
        }
    }

    put("/tasks/{id}") {
        try {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@put
            }

            val task = call.receive<Task>()
            task.copy(id = id)
            val apiResponse = taskRepository.updateTask(task)
            call.respond(apiResponse)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError)
        }
    }

    delete("/tasks/{id}") {
        try {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@delete
            }

            val success = taskRepository.deleteTaskById(id)
            if (success) {
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError)
        }
    }
}
