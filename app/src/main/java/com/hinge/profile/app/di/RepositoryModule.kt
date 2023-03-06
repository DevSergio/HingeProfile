package com.hinge.profile.app.di

import com.hinge.profile.app.network.ProfileService
import com.hinge.profile.app.network.model.UserDtoMapper
import com.hinge.profile.app.persistence.UserDao
import com.hinge.profile.app.persistence.model.UserEntityMapper
import com.hinge.profile.app.repository.ProfileListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideProfileListRepository(
        profileService: ProfileService,
        userDao: UserDao,
        userDtoMapper: UserDtoMapper,
        userEntityMapper: UserEntityMapper
    ): ProfileListRepository {
        return ProfileListRepository(profileService, userDao, userDtoMapper, userEntityMapper)
    }
}