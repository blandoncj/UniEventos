package com.example.unieventos.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.unieventos.R
import com.example.unieventos.enums.EmailError
import com.example.unieventos.enums.PasswordError
import com.example.unieventos.ui.components.utils.AppLogo
import com.example.unieventos.ui.components.utils.PrimaryButton
import com.example.unieventos.ui.components.utils.SecondaryButton
import com.example.unieventos.utils.validateFields

/**
 * LoginForm composable is a reusable component that displays the login form.
 * @param email The email input value.
 * @param emailError The email input error state.
 * @param password The password input value.
 * @param passwordError The password input error state.
 * @param onEmailChange The callback to handle email input changes.
 * @param onPasswordChange The callback to handle password input changes.
 * @param onForgotPassword The callback to handle forgot password button click.
 * @param onLogin The callback to handle login button click.
 * @param onSignup The callback to handle signup button click.
 */
@Composable
fun LoginForm(
    email: String,
    emailError: EmailError,
    password: String,
    passwordError: PasswordError,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onForgotPassword: () -> Unit,
    onLogin: () -> Unit,
    onSignup: () -> Unit
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
            onEmailChange = onEmailChange,
            emailError = emailError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        PasswordField(
            password = password,
            onPasswordChange = onPasswordChange,
            passwordError = passwordError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextButton(
            onClick = onForgotPassword
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
                .padding(horizontal = 16.dp),
            onClick = onLogin
        )

        Spacer(modifier = Modifier.height(10.dp))

        SecondaryButton(
            text = stringResource(id = R.string.register_btn),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = onSignup
        )
    }
}