package com.slyrand.data.user.network.model

import com.slyrand.domain.user.model.User

object UserObjectMother {
    fun getGenericUser() = User(
        id = "5ecbe4dd99ba65009b1954b2",
        title = User.Title.Miss,
        firstName = "Ashoka",
        lastName = "Tano",
        gender = User.Gender.Female,
        email = "ashoka@rebellion.com",
        dateOfBirth = null,
        registerDate = null,
        phone = "782634609",
        picture = "",
    )
}