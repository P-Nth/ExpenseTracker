package com.nthlabs.esmes.feature_authentication.presentation.register.component

import android.util.Patterns
import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nthlabs.esmes.R
import com.nthlabs.esmes.ui.theme.*
import com.nthlabs.esmes.core.components.buttons.LoginsActionButtons
import com.nthlabs.esmes.core.components.buttons.OtherSignUpMethods
import com.nthlabs.esmes.core.components.extras.HyperlinkText
import com.nthlabs.esmes.feature_authentication.domain.model.Login
import com.nthlabs.esmes.feature_authentication.domain.model.LoginUiStates

@Composable
fun RegisterScreen(
    login: Login? = null,
    onNavToHomeScreen:() -> Unit,
    onNavToLogin:() -> Unit,
) {

    val loginUiState = login?.loginUiState
    val isError = loginUiState?.signupError != null
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 30.dp)
    ) {
        RegisterLayout(
            login = login,
            isError = isError,
            context = context,
            loginUiState = loginUiState,
            onNavToLogin = onNavToLogin,
        )
        LaunchedEffect(key1 = login?.hasUser) {
            if (login?.hasUser == true) {
                onNavToHomeScreen.invoke()
            }
        }
    }
}

@Composable
fun RegisterLayout(
    login: Login?,
    isError: Boolean,
    context: Context,
    onNavToLogin: () -> Unit,
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
                .fillMaxHeight(.15f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LogoImage(height = .4f)
        }
        Text(
            text = "Sign Up",
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
            UserInputs(
                login = login,
                isError = isError,
                loginUiState = loginUiState
            )
            Spacer(modifier = Modifier.padding(5.dp))
            LoginsActionButtons(
                login = login,
                context = context,
                loginAction = false,
                actionLogin = false,
                loginUiState = loginUiState,
                action = "Create An Account",
            )
            Spacer(modifier = Modifier.padding(15.dp))
            OtherSignUpMethods(
                quiz = "Already Have an Account? ",
                screen = "SignUp",
                destination = "Login",
                onNavToLogin = onNavToLogin
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
fun LogoImage(
    height: Float
) {

    // logo
    val esmesLogo = painterResource(R.drawable.logo_transparent_128)

    Image(
        painter = esmesLogo,
        contentDescription = "logo",
        modifier = Modifier
            .fillMaxHeight(height)
            .fillMaxWidth(.5f)
            .background(Color.White)
    )
}

@Composable
fun UserInputs(
    login: Login?,
    isError: Boolean,
    loginUiState: LoginUiStates?
) {

    val usernameValue = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }
    val passValue = remember { mutableStateOf("") }
    val repassValue = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val dataEntry = remember { mutableStateOf(false) }
    val passVisibility = remember { mutableStateOf(false) }
    val repassVisibility = remember { mutableStateOf(false) }
    val isPasswordValid by derivedStateOf { passValue.value.length > 7 }
    val isUsernameValid by derivedStateOf { usernameValue.value.length > 7 }
    val isConfirmPassValid by derivedStateOf { repassValue.value.length > 7 }
    val isEmailValid by derivedStateOf { Patterns.EMAIL_ADDRESS.matcher(emailValue.value).matches() }

    Spacer(modifier = Modifier.padding(10.dp))
    if (isError) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(alert)
                .padding(5.dp),
            text = loginUiState?.signupError ?: "Please try again later!",
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
        OutlinedTextField(
            value = loginUiState?.usernameSignup ?: usernameValue.value,
            onValueChange = {
                login?.onUsernameSignup(it)
                usernameValue.value = it
                dataEntry.value = true
            },
            textStyle = MaterialTheme.typography.subtitle1,
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = black,
                unfocusedBorderColor = if (usernameValue.value.isEmpty()) border_blue else {
                    if(isUsernameValid) border_blue else red
                },
                focusedBorderColor = if (usernameValue.value.isEmpty()) border_blue else {
                    if(isUsernameValid) border_blue else red
                },
            ),
            trailingIcon = {
                if(dataEntry.value) {
                    Icon(
                        imageVector = if (usernameValue.value.isEmpty()) return@OutlinedTextField else {
                            if(isUsernameValid) Icons.Filled.CheckCircle else Icons.Filled.Close
                        },
                        contentDescription = "username validity",
                        tint = if (isUsernameValid) border_blue else red
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
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
                    text = "Enter Your Name",
                    style = MaterialTheme.typography.subtitle1
                )
            },
            shape = Shapes.medium,

            //check username validity
            isError = isError
        )
        Spacer(modifier = Modifier.padding(5.dp))
        OutlinedTextField(
            value = loginUiState?.emailSignup ?: emailValue.value,
            onValueChange = {
                login?.onEmailSignup(it)
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

            //check email validity
            isError = isError
        )
        Spacer(modifier = Modifier.padding(5.dp))
        OutlinedTextField(
            value = loginUiState?.passwordSignup ?: passValue.value,
            onValueChange = {
                login?.onPasswordSignup(it)
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
                    text = "Confirm Password",
                    style = MaterialTheme.typography.subtitle1
                )
            },
            shape = Shapes.medium,
            visualTransformation = if (passVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),

            //check password validity
            isError = isError
        )
        Spacer(modifier = Modifier.padding(5.dp))
        OutlinedTextField(
            value = loginUiState?.confirmPass ?: repassValue.value,
            onValueChange = {
                login?.onConfirmPass(it)
                repassValue.value = it
                dataEntry.value = true
            },
            textStyle = MaterialTheme.typography.subtitle1,
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = black,
                unfocusedBorderColor = if (repassValue.value.isEmpty()) border_blue else {
                    if(isConfirmPassValid) border_blue else red
                },
                focusedBorderColor = if (repassValue.value.isEmpty()) border_blue else {
                    if(isConfirmPassValid) border_blue else red
                },
            ),
            trailingIcon = {
                IconButton(onClick = { repassVisibility.value = !repassVisibility.value }) {
                    Icon(
                        painter = painterResource(if(repassVisibility.value)R.drawable.password_eye else R.drawable.pass_eye_cross),
                        contentDescription = "password validity",
                        tint = black
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
                    text = "Confirm Password",
                    style = MaterialTheme.typography.subtitle1
                )
            },
            shape = Shapes.medium,
            visualTransformation = if (repassVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),

            //check repassword validity
            isError = isError
        )
    }
}


@Composable
fun LegalLinks() {
    HyperlinkText(
        Modifier.padding(10.dp, 0.dp),
        fontSize = 14.sp,
        linkTextColor = legals_blue,
        fullText = "Privacy Policy",
        linkText = listOf("Privacy Policy"),
        hyperlinks = listOf("https://www.google.com"),
    )
    HyperlinkText(
        Modifier.padding(10.dp, 0.dp),
        fontSize = 14.sp,
        linkTextColor = legals_blue,
        fullText = "Terms of Use",
        linkText = listOf("Terms of Use"),
        hyperlinks = listOf("https://www.google.com"),
    )
    HyperlinkText(
        Modifier.padding(10.dp, 0.dp),
        fontSize = 14.sp,
        linkTextColor = legals_blue,
        fullText = "Don't sell my data",
        linkText = listOf("Don't sell my data"),
        hyperlinks = listOf("https://www.google.com"),
    )
}

@Preview
@Composable
fun Prev2() {
    RegisterScreen(onNavToHomeScreen = { /*TODO*/ }) {

    }
}