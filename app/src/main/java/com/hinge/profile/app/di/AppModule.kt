package com.hinge.profile.app.di

import android.content.Context
import com.hinge.profile.app.HingeProfileApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): HingeProfileApp {
        return app as HingeProfileApp
    }
}