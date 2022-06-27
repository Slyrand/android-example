package com.slyrand.mvvmapp.user.model

import com.slyrand.domain.user.model.User
import java.util.*

object UserObjectMother {
    fun getGenericUser() = User(
        id = "5ecbe4dd99ba65009b1954b2",
        title = User.Title.Miss,
        firstName = "Ashoka",
        lastName = "Tano",
        gender = User.Gender.Female,
        email = "ashoka@rebellion.com",
        dateOfBirth = Date(),
        registerDate = Date(),
        phone = "782634609",
        picture = "",
    )
}