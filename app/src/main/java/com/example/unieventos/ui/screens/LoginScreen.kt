package com.example.unieventos.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.unieventos.R
import com.example.unieventos.enums.EmailError
import com.example.unieventos.enums.PasswordError
import com.example.unieventos.enums.Role
import com.example.unieventos.ui.components.AlertMessage
import com.example.unieventos.ui.components.AlertType
import com.example.unieventos.ui.components.LoginForm
import com.example.unieventos.utils.RequestResult
import com.example.unieventos.utils.SharedPreferencesUtils
import com.example.unieventos.viewmodel.UsersViewModel
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    usersViewModel: UsersViewModel,
    onNavigateToSignup: () -> Unit,
    onNavigateToRecoverPassword: () -> Unit,
    onNavigateToHome: () -> Unit,
) {
    val authResult by usersViewModel.authResult.collectAsState()

    var email by rememberSaveable { mutableStateOf("") }
    var emailError by rememberSaveable { mutableStateOf(EmailError.NONE) }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordError by rememberSaveable { mutableStateOf(PasswordError.NONE) }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LoginForm(
                email = email,
                onEmailChange = {
                    email = it
//                    emailError = usersViewModel.validateEmailFormat(it)
                },
                emailError = emailError,
                password = password,
                onPasswordChange = { password = it },
                passwordError = passwordError,
                onForgotPassword = onNavigateToRecoverPassword,
                onSignup = onNavigateToSignup,
                onLogin = {
                    usersViewModel.login(email, password)
                },
            )

            when (authResult) {
                is RequestResult.Loading -> {
                    LinearProgressIndicator()
                }

                is RequestResult.Success -> {
                    LaunchedEffect(Unit) {
                        delay(2000)
                        onNavigateToHome()
                        usersViewModel.resetAuthResult()
                    }
                }

                is RequestResult.Error -> {
                    AlertMessage(
                        type = AlertType.ERROR,
                        message = (authResult as RequestResult.Error).errorMessage
                    )
                    LaunchedEffect(Unit) {
                        delay(2000)
                        usersViewModel.resetAuthResult()
                    }
                }

                null -> {}
            }
        }
    }
}

