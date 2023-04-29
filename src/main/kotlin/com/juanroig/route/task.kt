package com.juanroig.route

import com.juanroig.model.ApiResponse
import com.juanroig.repository.HeroRepository
import com.juanroig.repository.TaskRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.task() {
    val taskRepository: TaskRepository by inject()

    get("/tasks") {
        try {

            val apiResponse = taskRepository.getAllTasks()
            call.respond(
                message = apiResponse,
                status = HttpStatusCode.OK
            )
        } catch (e: NumberFormatException) {
            call.respond(
                message = ApiResponse(success = false, message = "Only Numbers Allowed."),
                status = HttpStatusCode.BadRequest
            )
        } catch (e: IllegalArgumentException) {
            call.respond(
                message = ApiResponse(success = false, message = "Task not Found."),
                status = HttpStatusCode.NotFound
            )
        }
    }
}