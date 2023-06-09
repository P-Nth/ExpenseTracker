package com.nthlabs.esmes.core.components.buttons

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.painter.Painter
import com.nthlabs.esmes.R
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nthlabs.esmes.ui.theme.*
import com.google.relay.compose.relayDropShadow
import com.nthlabs.esmes.core.components.extras.HyperlinkText
import com.nthlabs.esmes.core.components.extras.LoadingBubbles
import androidx.compose.foundation.shape.RoundedCornerShape
import com.nthlabs.esmes.feature_authentication.domain.model.Login
import com.nthlabs.esmes.feature_authentication.domain.model.LoginUiStates

//logins action buttons
@Composable
fun LoginsActionButtons(
    login: Login?,
    action: String,
    context: Context,
    loginAction: Boolean,
    actionLogin: Boolean,
    loginUiState: LoginUiStates?
) {
    var acceptTermsOfUse by remember { mutableStateOf(false) }

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(0.dp, 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(!actionLogin) {
            Box(modifier = Modifier
                .size(20.dp)
                .clickable { acceptTermsOfUse = !acceptTermsOfUse }
                .border(
                    if (acceptTermsOfUse) BorderStroke(0.dp, color = Color.Transparent)
                    else BorderStroke(1.dp, color = black),
                    shape = Shapes.small
                )
            ) {
                if (acceptTermsOfUse)
                    Icon(
                        tint = black,
                        contentDescription = "accept terms of use",
                        painter = painterResource(R.drawable.checkbox),
                        modifier = Modifier.border(
                            BorderStroke(0.dp, color = white),
                            shape = Shapes.medium
                        ),
                    )
            }
            HyperlinkText(
                Modifier.padding(10.dp, 0.dp),
                fontSize = 14.sp,
                linkTextColor = link_blue,
                linkText = listOf("terms of use"),
                fullText = "I agree with the terms of use",
                hyperlinks = listOf("https://www.google.com"),
            )
        } else {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Text(
                    style = MaterialTheme.typography.subtitle1,
                    text = "Forgot your password!",
                    fontWeight = FontWeight.W600,
                    fontSize = 13.sp,
                    color = link_blue,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.padding(0.dp, 20.dp))
            }
        }
    }
    if (!loginAction) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .aspectRatio(6.5f)
                .shadow(
                    elevation = 1.dp,
                    shape = Shapes.medium,
                )
                .clip(RoundedCornerShape(10.dp))
                .relayDropShadow(
                    color = black,
                    borderRadius = 5.0.dp,
                    blur = 2.0.dp,
                    offsetX = 0.0.dp,
                    offsetY = 3.0.dp,
                    spread = 0.0.dp
                ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = primary_blue
            ),
            contentPadding = PaddingValues(20.dp, 12.dp),
            shape = Shapes.small,
            onClick = { login?.createUser(context) }
        ) {
            if (loginUiState?.isLoading == true) LoadingBubbles(
                modifier = Modifier.fillMaxWidth(),
                bgColor = white
            ) else {
                Text(
                    text = action,
                    style = MaterialTheme.typography.button
                )
            }
        }
    } else {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .aspectRatio(6.5f)
                .shadow(
                    elevation = 1.dp,
                    shape = Shapes.medium,
                )
                .clip(RoundedCornerShape(10.dp))
                .relayDropShadow(
                    color = black,
                    borderRadius = 5.0.dp,
                    blur = 2.0.dp,
                    offsetX = 0.0.dp,
                    offsetY = 3.0.dp,
                    spread = 0.0.dp
                ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = primary_blue
            ),
            contentPadding = PaddingValues(20.dp, 12.dp),
            shape = Shapes.small,
            onClick = { login?.loginUser(context) }
        ) {
            if (loginUiState?.isLoading == true) LoadingBubbles(
                modifier = Modifier.fillMaxWidth(),
                bgColor = white
            ) else {
                Text(
                    text = action,
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}

@Composable
fun OtherSignUpMethods(
    quiz: String,
    screen: String,
    destination: String,
    onNavToLogin: () -> Unit
) {

    // social icons
    val appleIcon = painterResource(R.drawable.apple_logo)
    val googleIcon = painterResource(R.drawable.google_logo)
    val facebookIcon = painterResource(R.drawable.facebook_logo)

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier,
            text = "-- Or $screen With --",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.W600,
        )
        Spacer(modifier = Modifier.padding(15.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LoginIcons(name = "Google", icon = googleIcon, width = 110.dp, description = "google icon")
            LoginIcons(name = "Apple", icon = appleIcon, width = 100.dp, description = "Apple icon")
            LoginIcons(name = "F.book", icon = facebookIcon, width = 100.dp, description = "Facebook icon")
        }
        Spacer(modifier = Modifier.padding(15.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.W600,
                text = quiz,
                modifier = Modifier,
            )
            Text(
                modifier = Modifier.clickable(onClick = { onNavToLogin.invoke() }),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.W600,
                color = link_blue,
                text = destination
            )
        }
    }
}

@Composable
fun LoginIcons(
    name: String,
    icon: Painter,
    width: Dp,
    description: String
) {
    Row(
        modifier = Modifier
            .border(BorderStroke(1.dp, color = black), shape = Shapes.small)
            .padding(0.dp)
            .height(40.dp)
            .width(width),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                tint = black,
                painter = icon,
                contentDescription = description,
                modifier = Modifier
                    .padding(9.dp, 0.dp)
                    .fillMaxWidth(.2f)
                    .fillMaxHeight(),
            )
            Text(
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                    .padding(5.dp, 0.dp)
                    .fillMaxWidth(1f),
                fontWeight = FontWeight.W600,
                text = name,
            )
        }
    }
}