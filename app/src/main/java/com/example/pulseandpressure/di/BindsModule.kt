package com.example.pulseandpressure.di

import com.example.pulseandpressure.data.Repository
import com.example.pulseandpressure.data.RepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface BindsModule {
    @Binds
    fun bindRepository(implementation: RepositoryImpl) : Repository
}