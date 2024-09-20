package com.example.unieventos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.unieventos.R
import com.example.unieventos.enums.EmailError
import com.example.unieventos.enums.PasswordError
import com.example.unieventos.ui.components.AppLogo
import com.example.unieventos.ui.components.EmailField
import com.example.unieventos.ui.components.PasswordField
import com.example.unieventos.ui.components.PrimaryButton
import com.example.unieventos.ui.components.SecondaryButton
import com.example.unieventos.utils.validateEmailFormat
import com.example.unieventos.utils.validateFields

@Composable
fun LoginScreen(
    onNavigateToSignup: () -> Unit,
    onNavigateToRecoverPassword: () -> Unit
) {
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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(50.dp))

                AppLogo()

                Spacer(modifier = Modifier.height(30.dp))

                EmailField(
                    email = email,
                    onEmailChange = {
                        email = it
                        emailError = validateEmailFormat(it)
                    },
                    emailError = emailError,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                PasswordField(
                    password = password,
                    onPasswordChange = { password = it },
                    passwordError = passwordError,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextButton(
                    onClick = { onNavigateToRecoverPassword() },
                ) {
                    Text(text = stringResource(id = R.string.forgot_password_btn))
                }

                Spacer(modifier = Modifier.height(10.dp))

                PrimaryButton(
                    text = stringResource(id = R.string.login_btn),
                    enabled = emailError == EmailError.NONE && passwordError == PasswordError.NONE && validateFields(
                        listOf(email, password)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                SecondaryButton(
                    text = stringResource(id = R.string.register_btn),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        onNavigateToSignup()
                    }
                )
            }
        }
    }
}

