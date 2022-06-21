package com.slyrand.data.user.network.model

import com.slyrand.domain.user.model.User
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

data class ApiUser(
    val id: String?,
    val title: String?,
    val firstName: String?,
    val lastName: String?,
    val gender: String?,
    val email: String?,
    val dateOfBirth: String?,
    val registerDate: String?,
    val phone: String?,
    val picture: String?,
) {

    fun asUser(): User = User(
        id = id ?: "",
        title = User.Title.parse(title ?: ""),
        firstName = firstName ?: "",
        lastName = lastName ?: "",
        gender = User.Gender.parse(gender ?: ""),
        email = email ?: "",
        dateOfBirth = parseDate(dateOfBirth ?: ""),
        registerDate = parseDate(registerDate ?: ""),
        phone = phone ?: "",
        picture = picture ?: "",
    )

    private fun parseDate(isoDate: String): Date? {
        return try {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).parse(isoDate)
        } catch (e: ParseException) {
            e.printStackTrace()
            Date()
        }
    }
}