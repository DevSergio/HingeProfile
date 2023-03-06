package com.hinge.profile.app.repository

import androidx.annotation.WorkerThread
import com.hinge.profile.app.domain.model.User
import com.hinge.profile.app.domain.data.DataState
import com.hinge.profile.app.network.ProfileService
import com.hinge.profile.app.network.model.UserDtoMapper
import com.hinge.profile.app.persistence.UserDao
import com.hinge.profile.app.persistence.model.UserEntityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileListRepository @Inject constructor(
    private val profileService: ProfileService,
    private val userDao: UserDao,
    private val userDtoMapper: UserDtoMapper,
    private val userEntityMapper: UserEntityMapper
) {

    @WorkerThread
    fun loadConfig(): Flow<DataState<List<String>>> = flow {
        try {
            emit(DataState.loading())

            val config = profileService.config().profile

            emit(DataState.success(config))

        } catch (e: Exception) {
            emit(DataState.error(e.message ?: "Unknown Error"))
        }
    }

    @WorkerThread
    fun loadUsers(): Flow<DataState<List<User>>> = flow {
        try {
            emit(DataState.loading())

            val users = getUsersFromCache()

            if(users?.isNotEmpty()== true) {
                emit(DataState.success(users))
            } else {
                val usersFromNetwork = getUsersFromNetwork()
                userDao.insertUsers(userEntityMapper.toEntityList(usersFromNetwork))
                emit(DataState.success(usersFromNetwork))
            }

        } catch (e: Exception) {
            emit(DataState.error(e.message ?: "Unknown Error"))
        }
    }

    suspend fun getUsersFromCache(): List<User>? {
        return userDao.getUsers().let { userEntity ->
            userEntityMapper.fromEntityList(userEntity)
        }
    }

    suspend fun getUsersFromNetwork(): List<User> {
        return userDtoMapper.toDomainList(profileService.getUsers().users)
    }

}