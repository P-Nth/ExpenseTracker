package com.nthlabs.esmes.feature_authentication.presentation.login.component

import android.content.Context
import android.util.Patterns
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.nthlabs.esmes.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nthlabs.esmes.ui.theme.*
import androidx.compose.ui.text.input.KeyboardType
import com.nthlabs.esmes.core.components.buttons.OtherSignUpMethods
import com.nthlabs.esmes.core.components.buttons.LoginsActionButtons
import com.nthlabs.esmes.feature_authentication.domain.model.Login
import com.nthlabs.esmes.feature_authentication.domain.model.LoginUiStates
import com.nthlabs.esmes.feature_authentication.presentation.register.component.LogoImage
import com.nthlabs.esmes.feature_authentication.presentation.register.component.LegalLinks

@Composable
fun LoginScreen(
    login: Login? = null,
    onNavToHomeScreen:() -> Unit,
    onNavToRegister:() -> Unit,
) {

    val loginUiState = login?.loginUiState
    val isError = loginUiState?.loginError != null
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 30.dp)
    ) {
        LoginLayout(
            login = login,
            isError = isError,
            context = context,
            loginUiState = loginUiState,
            onNavToRegister = onNavToRegister,
        )
        LaunchedEffect(key1 = login?.hasUser) {
            if (login?.hasUser == true) {
                onNavToHomeScreen.invoke()
            }
        }
    }
}

@Composable
fun LoginLayout(
    login: Login?,
    context: Context,
    isError: Boolean,
    onNavToRegister: () -> Unit,
    loginUiState: LoginUiStates?,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.2f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LogoImage(height = .3f)
        }
        Text(
            text = "Login",
            fontSize = 32.sp,
            style = MaterialTheme.typography.h3,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .clip(RoundedCornerShape(5.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            LoginUserInputs(
                login = login,
                isError = isError,
                loginUiState = loginUiState,
            )
            LoginsActionButtons(
                login = login,
                context = context,
                loginAction = true,
                actionLogin = true,
                loginUiState = loginUiState,
                action = "Login",
            )
            Spacer(modifier = Modifier.padding(15.dp))
            OtherSignUpMethods(
                screen = "Login",
                destination = "Sign Up",
                onNavToLogin = onNavToRegister,
                quiz = "Don't Have an Account? "
            )
            Spacer(modifier = Modifier.padding(25.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                LegalLinks()
            }
        }
    }
}

@Composable
fun LoginUserInputs(
    login: Login?,
    isError: Boolean,
    loginUiState: LoginUiStates?,
) {

    val emailValue = remember { mutableStateOf("") }
    val passValue = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val dataEntry = remember { mutableStateOf(false) }
    val passVisibility = remember { mutableStateOf(false) }
    val isPasswordValid by derivedStateOf { passValue.value.length > 7 }
    val isEmailValid by derivedStateOf { Patterns.EMAIL_ADDRESS.matcher(emailValue.value).matches() }

    Spacer(modifier = Modifier.padding(10.dp))
    if (isError) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(alert)
                .padding(5.dp),
            text = loginUiState?.loginError ?: "Please try again later!",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                letterSpacing = 1.sp,
                color = Color.Red,
            ),
            fontSize = 15.sp
        )
    }
    Spacer(modifier = Modifier.padding(5.dp))
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
        Spacer(modifier = Modifier.padding(5.dp))
        OutlinedTextField(
            value = loginUiState?.email ?: emailValue.value,
            onValueChange = {
                login?.onEmailChange(it)
                emailValue.value = it
                dataEntry.value = true
            },
            textStyle = MaterialTheme.typography.subtitle1,
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = black,
                unfocusedBorderColor = if (emailValue.value.isEmpty()) border_blue else {
                    if(isEmailValid) border_blue else red
                },
                focusedBorderColor = if (emailValue.value.isEmpty()) border_blue else {
                    if(isEmailValid) border_blue else red
                },
            ),
            trailingIcon = {
                if(dataEntry.value) {
                    Icon(
                        imageVector = if (emailValue.value.isEmpty()) return@OutlinedTextField else {
                            if(isEmailValid) Icons.Filled.CheckCircle else Icons.Filled.Close
                        },
                        contentDescription = "email validity",
                        tint = if (isEmailValid) border_blue else red
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                    dataEntry.value = false
                }
            ),
            placeholder = {
                Text(
                    text = "Email Address",
                    style = MaterialTheme.typography.subtitle1
                )
            },
            shape = Shapes.medium,

            //check value validity
            isError = isError
        )
        Spacer(modifier = Modifier.padding(5.dp))
        OutlinedTextField(
            value = loginUiState?.password ?: passValue.value,
            onValueChange = {
                login?.onPasswordChange(it)
                passValue.value = it
                dataEntry.value = true
            },
            textStyle = MaterialTheme.typography.subtitle1,
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = black,
                unfocusedBorderColor = if (passValue.value.isEmpty()) border_blue else {
                    if(isPasswordValid) border_blue else red
                },
                focusedBorderColor = if (passValue.value.isEmpty()) border_blue else {
                    if(isPasswordValid) border_blue else red
                },
            ),
            trailingIcon = {
                IconButton(onClick = { passVisibility.value = !passVisibility.value }) {
                    Icon(
                        painter = painterResource(if(passVisibility.value)R.drawable.password_eye else R.drawable.pass_eye_cross),
                        contentDescription = "password validity",
                        tint = black,
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    dataEntry.value = false
                }
            ),
            placeholder = {
                Text(
                    text = "Password",
                    style = MaterialTheme.typography.subtitle1
                )
            },
            shape = Shapes.medium,
            visualTransformation = if (passVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),

            //check password validity
            isError = isError
        )
    }
}

@Preview
@Composable
fun Prev1() {
    LoginScreen(onNavToHomeScreen = { /*TODO*/ }) {

    }
}