package com.chslcompany.desafioserasa.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @JvmSuppressWildcards
    @Provides
    @Named("main")
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @JvmSuppressWildcards
    @Provides
    @Named("io")
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @JvmSuppressWildcards
    @Provides
    @Named("unconfined")
    fun provideUnconfinedDispatcher(): CoroutineDispatcher = Dispatchers.Unconfined

    @JvmSuppressWildcards
    @Provides
    @Named("default")
    fun provideMainImmediateDispatcher(): CoroutineDispatcher = Dispatchers.Default
}





