package de.coldtea.focalapp.shared.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.coldtea.focalapp.parallax.domain.AccelerometerService
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAccelerometerService(@ApplicationContext appContext: Context) = AccelerometerService(appContext)
}