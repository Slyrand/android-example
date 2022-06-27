package com.slyrand.data.user.network

import com.slyrand.data.NetworkDataSourceTest
import com.slyrand.data.user.network.model.UserObjectMother
import com.slyrand.data.user.network.model.asUser
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class UserNetworkDataSourceTest : NetworkDataSourceTest() {

    @Test
    fun getUsers() = runTest {
        val service = mockRetrofit.create(UserService::class.java)
        val mockedAuthService = MockUserService(service)
        val user = UserObjectMother.getGenericUser()
        val apiUser = mockedAuthService.getUsers(0, 20).data.first().asUser()

        Assert.assertEquals(user, apiUser)
    }

    @Test
    fun getUserDetail() = runTest {
        val service = mockRetrofit.create(UserService::class.java)
        val mockedAuthService = MockUserService(service)
        val user = UserObjectMother.getGenericUser()
        val apiUser = mockedAuthService.getUserDetail("userId").asUser()

        Assert.assertEquals(user, apiUser)
    }
}