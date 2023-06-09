package com.nthlabs.esmes.feature_authentication.data.repository

import androidx.compose.ui.graphics.painter.Painter

data class User(
    val email: String,
    val username: String,
    val location: String,
    val profilePic: Painter,
)

data class homeState(
    val date: String,
    val notification: Boolean,
)