package com.hinge.profile.app.viewmodel

import com.hinge.profile.app.domain.model.User
import com.hinge.profile.app.network.ProfileService
import com.hinge.profile.app.persistence.UserDao
import com.hinge.profile.app.presentation.profile.ProfileCardViewModel
import com.hinge.profile.app.repository.ProfileListRepository
import com.hinge.profile.app.rule.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class ProfileCardViewModelTest {

    private var repository: ProfileListRepository = mock(ProfileListRepository::class.java)

    private var api: ProfileService = mock(ProfileService::class.java)

    private var dao: UserDao = mock(UserDao::class.java)

    private lateinit var viewModel: ProfileCardViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun before() {
        repository = mock(ProfileListRepository::class.java)
        api = mock(ProfileService::class.java)
        dao = mock(UserDao::class.java)
        viewModel = ProfileCardViewModel(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test get user`() = runTest {
        whenever(repository.getUsersFromNetwork()) doReturn (profileList())
        val profiles = repository.getUsersFromNetwork()
    }

    fun hobbiestList() = listOf(
        "Sport",
        "Swimming",
        "Reading",
    )

    fun profileList() =
        listOf(
            User(1, "Jack", "", "m", "", "Sweet", hobbiestList()),
            User(2, "Mike", "", "m", "", "Keep", hobbiestList()),
            User(3, "Dane", "", "f", "", "Home", hobbiestList()),
        )
}