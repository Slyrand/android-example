package com.slyrand.mvvmapp.user.list

import arrow.core.left
import arrow.core.right
import com.slyrand.domain.core.model.DataError
import com.slyrand.domain.core.model.PaginationState
import com.slyrand.domain.user.usecase.GetUsers
import com.slyrand.mvvmapp.user.model.UserObjectMother
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.rules.TestWatcher
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class UsersListViewModelTest : TestWatcher() {

    @MockK
    private lateinit var getUsers: GetUsers
    private lateinit var viewModel: UsersListViewModel


    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        MockKAnnotations.init(this, relaxed = true)
        viewModel = UsersListViewModel(getUsers)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun stateIsCorrectAfterSuccessfulRequest() = runTest {
        val users = listOf(UserObjectMother.getGenericUser())
        coEvery { getUsers.execute(PaginationState()) } returns users.right()

        launch {
            viewModel.requestData()
            assertEquals(viewModel.state.value.users.first(), users.first())
            assertEquals(viewModel.state.value.loading, false)
            assertEquals(viewModel.state.value.error, null)
        }
    }

    @Test
    fun stateIsCorrectAfterFailedRequest() = runTest {
        coEvery { getUsers.execute(PaginationState()) } returns DataError.ConnectionError.left()

        launch {
            viewModel.requestData()
            assertEquals(viewModel.state.value.error, DataError.ConnectionError)
        }
    }

    @Test
    fun onRefreshShouldResetListState() = runTest {
        launch {
            viewModel.refresh()
            assertEquals(viewModel.state.value.paginationState.page, 0)
            assertEquals(viewModel.state.value.users.size, 0)
        }
    }

    @Test
    fun onQueryChangedShouldUpdateState() = runTest {
        val query = "query"
        launch {
            viewModel.onQueryChanged(query)
            assertEquals(viewModel.state.value.query, query)
            assertEquals(viewModel.state.value.users.size, 0)
            assertEquals(viewModel.state.value.paginationState.page, 0)
        }
    }
}