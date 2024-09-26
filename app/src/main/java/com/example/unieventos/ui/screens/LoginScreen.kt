package com.example.unieventos.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import com.example.unieventos.ui.components.LoginForm
import com.example.unieventos.utils.validateEmailFormat
import com.example.unieventos.viewmodel.UsersViewModel

/**
 * login form composable is a screen that displays the login form.
 * @param usersViewModel The view model to handle the users data.
 * @param onNavigateToSignup The callback to navigate to the signup screen.
 * @param onNavigateToRecoverPassword The callback to navigate to the recover password screen.
 * @param onNavigateToAdminHome The callback to navigate to  the admin home screen.
 */
@Composable
fun LoginScreen(
    usersViewModel: UsersViewModel,
    onNavigateToSignup: () -> Unit,
    onNavigateToRecoverPassword: () -> Unit,
    onNavigateToAdminHome: () -> Unit,
    onNavigateToCustomerHome: () -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }
    var emailError by rememberSaveable { mutableStateOf(EmailError.NONE) }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordError by rememberSaveable { mutableStateOf(PasswordError.NONE) }

    val context = LocalContext.current
    val validationMessage = stringResource(id = R.string.invalid_credentials)

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
                    emailError = validateEmailFormat(it)
                },
                emailError = emailError,
                password = password,
                onPasswordChange = { password = it },
                passwordError = passwordError,
                onForgotPassword = onNavigateToRecoverPassword,
                onSignup = onNavigateToSignup,
                onLogin = {
                    val user = usersViewModel.login(email, password)

                    if (user != null) {
                        if (user.role == Role.ADMIN) {
                            onNavigateToAdminHome()
                        } else {
                            onNavigateToCustomerHome()
                        }
                    } else {
                        Toast.makeText(context, validationMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }
}

