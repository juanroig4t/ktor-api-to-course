package com.juanroig.di

import com.juanroig.repository.HeroRepository
import com.juanroig.repository.HeroRepositoryImpl
import org.koin.dsl.module

val koinModule = module {
    single<HeroRepository> {
        HeroRepositoryImpl()
    }
}