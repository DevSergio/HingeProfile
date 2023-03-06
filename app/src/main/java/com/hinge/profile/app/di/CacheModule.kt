package com.hinge.profile.app.di

import androidx.room.Room
import com.hinge.profile.app.HingeProfileApp
import com.hinge.profile.app.persistence.AppDatabase
import com.hinge.profile.app.persistence.UserDao
import com.hinge.profile.app.persistence.model.UserEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideDb(app: HingeProfileApp): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.userDao()
    }

    @Singleton
    @Provides
    fun provideCacheUserMapper(): UserEntityMapper {
        return UserEntityMapper()
    }
}