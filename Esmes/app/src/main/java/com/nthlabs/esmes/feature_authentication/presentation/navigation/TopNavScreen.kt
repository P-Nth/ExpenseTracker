package com.nthlabs.esmes.feature_authentication.presentation.navigation

import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import  androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.nthlabs.esmes.R
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import com.nthlabs.esmes.ui.theme.black
import com.nthlabs.esmes.ui.theme.white
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TopNavBar(navBarName: String, modifier: Modifier, bgColor: Color) {
    Box() {
        TNavComponents(navBarName = navBarName, modifier = modifier.align(Alignment.TopCenter), bgColor = bgColor)
    }
}

@Composable
fun TNavComponents(navBarName: String, modifier: Modifier, bgColor: Color) {
    TopAppBar(
        backgroundColor = bgColor,
        modifier = modifier,
        elevation = 0.dp
    ) {
            Row(
                modifier = modifier.padding(start = 1.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.menu_64),
                        contentDescription = "Menu Icon",
                        modifier = Modifier.size(40.dp),
                        tint = black
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.notification_64),
                            contentDescription = "Notifications Icon",
                            modifier = Modifier.size(30.dp),
                            tint = black
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.date_64),
                                contentDescription = "Date & Time Icon",
                                modifier = Modifier.size(30.dp),
                                tint = black
                            )
                        }
                        Text(
                            style = MaterialTheme.typography.subtitle1,
                            text = "Today, 30th October",
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                        )
                    }
                }
            }
    }
}

@Preview
@Composable
fun Prev4() {
    TopNavBar(navBarName = "Home", modifier = Modifier.fillMaxWidth(), bgColor = white)
}