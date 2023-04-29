package com.juanroig.plugins

import com.juanroig.route.getAllHeroes
import com.juanroig.route.root
import com.juanroig.route.searchHeroes
import com.juanroig.route.task
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        root()
        getAllHeroes()
        searchHeroes()
        task()

        static("/images") {
            resources("images")
        }
    }
}