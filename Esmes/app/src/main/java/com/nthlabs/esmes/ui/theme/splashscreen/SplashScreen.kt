package com.nthlabs.esmes.ui.theme.splashscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import com.nthlabs.esmes.R
import androidx.compose.ui.unit.*
import com.nthlabs.esmes.ui.theme.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SplashScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 0.dp)
            .background(primary_light_blue.copy(1f)),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(.4f)
                .fillMaxHeight(.5f)
                .clip(RoundedCornerShape(15.dp))
                .background(primary_light_blue.copy(1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                tint = white,
                contentDescription = "Logo",
                modifier = Modifier.width(100.dp).height(100.dp),
                painter = painterResource(R.drawable.logo_transparent_128),
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().fillMaxHeight(.1f)
        ) {
            Text(
                text = "Powered by Nth-Labs",
                fontSize = 14.sp,
                color = legals_blue,
                letterSpacing = 1.sp,
                fontWeight = FontWeight.W600,
                style = MaterialTheme.typography.h5
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    SplashScreen()
}