package com.slyrand.domain.user.model

import java.util.*

data class User(
    val id: String,
    val title: Title,
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val email: String,
    val dateOfBirth: Date?,
    val registerDate: Date?,
    val phone: String,
    val picture: String
) {
    sealed interface Gender {
        companion object {
            private const val MALE = "male"
            private const val FEMALE = "female"

            fun parse(string: String): Gender = when(string) {
                MALE -> Male
                FEMALE -> Female
                else -> Other
            }
        }
        object Male: Gender
        object Female: Gender
        object Other: Gender
    }

    sealed interface Title {
        companion object {
            private const val MR = "mr"
            private const val MS = "ms"
            private const val MRS = "mrs"
            private const val MISS = "miss"
            private const val DR = "dr"

            fun parse(string: String): Title = when(string) {
                MR -> Mr
                MS -> Ms
                MRS -> Mrs
                MISS -> Miss
                DR -> Dr
                else -> Other
            }
        }

        object Mr: Title
        object Ms: Title
        object Mrs: Title
        object Miss: Title
        object Dr: Title
        object Other: Title
    }
}