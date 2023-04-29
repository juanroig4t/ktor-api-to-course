package com.juanroig.di

import com.juanroig.repository.HeroRepository
import com.juanroig.repository.HeroRepositoryImpl
import com.juanroig.repository.TaskRepository
import com.juanroig.repository.TaskRepositoryImpl
import org.koin.dsl.module

val koinModule = module {
    single<HeroRepository> {
        HeroRepositoryImpl()
    }
    single<TaskRepository> {
        TaskRepositoryImpl()
    }
}