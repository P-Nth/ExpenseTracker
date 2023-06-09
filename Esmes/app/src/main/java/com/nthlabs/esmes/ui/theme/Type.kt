package com.nthlabs.esmes.ui.theme

import com.nthlabs.esmes.R
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.*
import androidx.compose.material.Typography
import androidx.compose.ui.text.style.TextAlign

private val Roboto = FontFamily(
    Font(R.font.roboto_thin, weight = FontWeight.W300),
    Font(R.font.roboto_light, weight = FontWeight.W400),
    Font(R.font.roboto_medium, weight = FontWeight.W500),
    Font(R.font.roboto_regular, weight = FontWeight.W600),
    Font(R.font.roboto_black, weight = FontWeight.W800),
)

//custom typography
val Typography = Typography(
    h1 = TextStyle(
        fontSize = 30.sp,
        fontFamily = Roboto,
        fontWeight = FontWeight.W800,
    ),
    h2 = TextStyle(
        color = black,
        fontSize = 25.sp,
        fontFamily = Roboto,
        fontWeight = FontWeight.W600,
    ),
    h3 = TextStyle(
        color = black,
        fontSize = 20.sp,
        fontFamily = Roboto,
        letterSpacing = 1.sp,
        fontWeight = FontWeight.W600,
    ),
    h4 = TextStyle(
        fontSize = 18.sp,
        fontFamily = Roboto,
        letterSpacing = 1.sp,
        fontWeight = FontWeight.W400,
    ),
    h5 = TextStyle(
        fontSize = 16.sp,
        fontFamily = Roboto,
        fontWeight = FontWeight.W300,
    ),
    button = TextStyle(
        color = white,
        fontSize = 24.sp,
        fontFamily = Roboto,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.W600,
    ),
    caption = TextStyle(
        fontSize = 12.sp,
        fontFamily = Roboto,
        fontWeight = FontWeight.W400,
    ),
    body1 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.W800,
    ),
    subtitle1 = TextStyle(
        color = black,
        fontSize = 18.sp,
        fontFamily = Roboto,
        fontWeight = FontWeight.W600,
    ),
    overline = TextStyle(
        fontSize = 18.sp,
        color = link_blue,
        fontFamily = Roboto,
        fontWeight = FontWeight.W600,
    ),
)