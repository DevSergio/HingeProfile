package com.hinge.profile.app.repository

import com.hinge.profile.app.network.ProfileService
import com.hinge.profile.app.network.model.UserDto
import com.hinge.profile.app.network.model.UserDtoMapper
import com.hinge.profile.app.network.response.ConfigResponse
import com.hinge.profile.app.network.response.UserResponse
import com.hinge.profile.app.persistence.UserDao
import com.hinge.profile.app.persistence.model.UserEntityMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever

class ProfileListRepositoryTest {

    private lateinit var profileListRepository: ProfileListRepository

    private val profileService: ProfileService = mock(ProfileService::class.java)

    private val userDao = mock(UserDao::class.java)

    private val userDtoMapper = mock(UserDtoMapper::class.java)

    private val userEntityMapper = mock(UserEntityMapper::class.java)

    @Before
    fun before() {
        profileListRepository =
            ProfileListRepository(profileService, userDao, userDtoMapper, userEntityMapper)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `load config`() = runTest {
        whenever(profileService.config()) doReturn (config())
        val config = profileListRepository.loadConfig()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `load users`() = runTest {
        whenever(profileService.getUsers()) doReturn (profileList())
        val profiles = profileListRepository.getUsersFromNetwork()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get users from cache`() = runTest {
        whenever(userDao.getUsers()) doReturn (listOf())
        val profiles = profileListRepository.getUsersFromCache()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get users from network`() = runTest {
        whenever(profileService.getUsers()) doReturn (profileList())
        val profiles = profileListRepository.getUsersFromNetwork()
    }

    fun hobbiestList() = listOf(
        "Sport",
        "Swimming",
        "Reading",
    )

    fun profileList() = UserResponse(
        listOf(
            UserDto(1, "Jack", "", "m", "", "Sweet", hobbiestList()),
            UserDto(2, "Mike", "", "m", "", "Keep", hobbiestList()),
            UserDto(3, "Dane", "", "f", "", "Home", hobbiestList()),
        )
    )

    fun config() = ConfigResponse(listOf("id", "name", "photo", "about", "hobbies"))
}