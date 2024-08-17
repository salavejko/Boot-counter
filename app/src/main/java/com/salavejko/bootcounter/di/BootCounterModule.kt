package com.salavejko.bootcounter.di

import android.content.Context
import androidx.room.Room
import com.salavejko.bootcounter.database.BootCounterDatabase
import com.salavejko.bootcounter.repositories.BootCounterRepository
import com.salavejko.bootcounter.repositories.BootCounterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BootCounterModule {

    @Binds
    abstract fun bootCounterRepository(bootCounterRepositoryImpl: BootCounterRepositoryImpl): BootCounterRepository
}


@Module
@InstallIn(SingletonComponent::class)
object BootCounterProvidersModule {

    @Singleton
    @Provides
    fun provideBootCounterDatabase(@ApplicationContext context: Context): BootCounterDatabase {
        return Room.databaseBuilder(
            context,
            BootCounterDatabase::class.java,
            "boot_counter_database"
        ).build()
    }
}


