package com.example.unieventos.ui.screens.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.unieventos.enums.CodeError
import com.example.unieventos.enums.PasswordError
import com.example.unieventos.ui.components.CodeField
import com.example.unieventos.ui.components.utils.CustomTopAppBar
import com.example.unieventos.ui.components.PasswordField
import com.example.unieventos.ui.components.utils.PrimaryButton
import com.example.unieventos.utils.validateCode
import com.example.unieventos.utils.validatePasswordFormat
import com.example.unieventos.utils.validatePasswordsMatch

/**
 * Change password screen composable function.
 * This composable function displays the change password screen.
 */
@Composable
fun ChangePasswordScreen(
    onBack: () -> Unit
) {
    var code by rememberSaveable { mutableStateOf("") }
    var codeError by rememberSaveable { mutableStateOf(CodeError.NONE) }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordError by rememberSaveable { mutableStateOf(PasswordError.NONE) }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var confirmPasswordError by rememberSaveable { mutableStateOf(PasswordError.NONE) }

    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Cambiar Contraseña", onBack = onBack )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(70.dp))

                Text(text = stringResource(id = R.string.code_description))

                Spacer(modifier = Modifier.height(20.dp))

                CodeField(
                    code = code,
                    onCodeChange = {
                        code = it
                        codeError = validateCode(it)
                    },
                    codeError = codeError,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                )

                Spacer(modifier = Modifier.height(10.dp))

                PasswordField(
                    password = password,
                    onPasswordChange = {
                        password = it
                        passwordError = validatePasswordFormat(it)
                    },
                    passwordError = passwordError,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                )

                Spacer(modifier = Modifier.height(10.dp))

                PasswordField(
                    label = stringResource(id = R.string.pass_confirm_lbl),
                    password = confirmPassword,
                    onPasswordChange = {
                        confirmPassword = it
                        confirmPasswordError = validatePasswordsMatch(password, it)
                    },
                    passwordError = confirmPasswordError,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                )

                Spacer(modifier = Modifier.height(50.dp))

                PrimaryButton(
                    text = stringResource(id = R.string.confirm_btn),
                    onClick = { },
                    enabled = codeError == CodeError.NONE &&
                            passwordError == PasswordError.NONE &&
                            confirmPasswordError == PasswordError.NONE &&
                            password.isNotEmpty(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}