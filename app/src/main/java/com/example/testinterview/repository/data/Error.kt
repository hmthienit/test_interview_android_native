package com.example.testinterview.repository.data

import java.io.Serializable

data class Error(
    val error: Logout?
): Serializable

data class Logout(
    val force_logout: Boolean?,
    val invalid_code: Boolean?,
)